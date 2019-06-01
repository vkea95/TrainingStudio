package jian.concurrent.chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassTest {
    public static void main(String[] args) throws
            ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NoSuchMethodException,
            InvocationTargetException {


//        Thread classs loader

        ClassLoader loader = MyClassTest.class.getClassLoader();
        ClassLoader loader1 = Thread.currentThread().getContextClassLoader();
//        Thread.currentThread().setContextClassLoader(XX);

        FileSystemClassLoader fsc = new FileSystemClassLoader("/Users/tclresearchamerica/Downloads/stm32f");
        Class<?> aclass1 = fsc.loadClass("com.ha.HelloWorld");

        System.out.println(aclass1.getClassLoader());
        Object helloworld1 = aclass1.newInstance();
        System.out.println(helloworld1);
        Method welcomeMethod1 = aclass1.getMethod("welcome");
        String result1 = (String) welcomeMethod1.invoke(helloworld1);
        System.out.println("result: " + result1);

        MyClassLoader classLoader = new MyClassLoader();
        Class<?> aclass = classLoader.loadClass("jian.concurrent.chapter10.HelloWorld");

        System.out.println(aclass.getClassLoader());
        Object helloworld = aclass.newInstance();
        System.out.println(helloworld);
        Method welcomeMethod = aclass.getMethod("welcome");
        String result = (String) welcomeMethod.invoke(helloworld);
        System.out.println("result: " + result);

    }
}
