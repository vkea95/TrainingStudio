package jian.java8.methodRef;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Car {


    public static void main(String[] args) {
//        第一种方法引用的类型是构造器引用，语法是Class::new，或者更一般的形式：Class::new。注意：这个构造器没有参数。
        Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);
//第二种方法引用的类型是静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数。
        cars.forEach(Car::kiss);
//        第三种方法引用的类型是某个类的成员方法的引用，语法是Class::method，注意，这个方法没有定义入参：
        cars.forEach(Car::repair);
//第四种方法引用的类型是某个实例对象的成员方法的引用，语法是instance::method。注意：这个方法接受一个Car类型的参数：
//       这个略微有些难理解，但是就要换位思考。
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);

    }

    public static Car create(final Supplier<Car> supplier) {

        return supplier.get();
    }

    //static method
    public static void kiss(final Car car) {
        System.out.println("kiss " + car.toString());
    }

    public void follow(final Car car) {

        System.out.println("who am i " + this.toString());
        System.out.println("follow " + car.toString());

    }

    public void repair() {
        System.out.println("repair " + this.toString());

    }
}
