package jian.concurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyTask01 {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        ExecutorService service1 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            service1.submit(myTask);
        }
        service1.shutdown();
    }


    static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out
                    .println(System.currentTimeMillis() + ": Thread ID :" + Thread.currentThread().getId());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}