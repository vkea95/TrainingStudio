package jian.concurrent.chapter21;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

public class ThreadLocalExample {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        IntStream.range(0, 10).forEach(i -> new Thread(() ->
        {
            try {
                threadLocal.set(i);
                System.out.println(currentThread() + "set i " + threadLocal.get());
                TimeUnit.SECONDS.sleep(1);
                ;
                System.out.println(currentThread() + "set i " + threadLocal.get());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        ).start());
    }
}
