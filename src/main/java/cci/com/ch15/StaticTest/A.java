package cci.com.ch15.StaticTest;

public class A { //父类
    public static String staticStr = "A静态属性";
    public String nonStaticStr = "A非静态属性";

    public static void staticMethod() {
        System.out.println("A静态方法");
    }

    public void nonStaticMethod() {
        System.out.println("A非静态方法");
    }
}