package cci.com.ch15.p05;

public class TestFoo {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread thread1 = new Thread(new FirstThread(foo));
        Thread thread2 = new Thread(new SecondThread(foo));
        Thread thread3 = new Thread(new ThirdThread(foo));
        thread2.start();
        thread3.start();
        thread1.start();

    }
}

class FirstThread implements Runnable {
    Foo foo;

    public FirstThread(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        while (true)
        foo.first();
    }
}

class SecondThread implements Runnable {
    Foo foo;

    public SecondThread(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        while (true)
        foo.second();
    }
}

class ThirdThread implements Runnable {
    Foo foo;

    public ThirdThread(Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run() {
        while (true)
        foo.third();
    }
}