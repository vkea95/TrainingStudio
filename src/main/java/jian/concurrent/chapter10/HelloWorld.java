package jian.concurrent.chapter10;

public class HelloWorld {

    static {
        System.out.println("Initialization...");

    }

    public String welcome() {
        System.out.println("hello");
        return "hello China";
    }
}
