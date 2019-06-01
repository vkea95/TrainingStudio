package jian.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("I'm interrupted");
                }
            }
        };

        thread.start();
        // short sleep
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}
