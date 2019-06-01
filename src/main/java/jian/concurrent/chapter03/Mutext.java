package jian.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

public class Mutext {

    private final static Object MUTEX = new Object();

    public static void main(String[] args) {
        final Mutext mutext = new Mutext();
        for (int i = 0; i < 5; i++) {
            new Thread(mutext::test).start();
        }
    }

    public int test() {
        synchronized (MUTEX) {
            try {
                System.out.println("test");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public void accessResource() {
        synchronized (MUTEX) {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
