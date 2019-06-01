package jian.corejava.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;

public class DelayQueueConsumer implements Runnable {
    private String name;
    private BlockingQueue<Delayed> queue;


    public DelayQueueConsumer(String name, BlockingQueue<Delayed> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Take elements out from the DelayQueue object.
                Delayed object = queue.take();
                System.out.printf("[%s] - Take object = %s%n @, %d",
                        Thread.currentThread().getName(), object, System.currentTimeMillis());
                System.out.println();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
