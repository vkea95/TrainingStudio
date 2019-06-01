package jian.corejava.thread;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;

public class DelayQueueProducer implements Runnable {
    // Creates an instance of blocking queue using the DelayQueue.
    private BlockingQueue<Delayed> queue;
    private final Random random = new Random();
    private String name;

    public DelayQueueProducer(String name, BlockingQueue<Delayed> queue) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {

                // Put some Delayed object into the DelayQueue.
                int delay = random.nextInt(10000);
                Delayed object = new DelayObject(
                        UUID.randomUUID().toString(), delay);

                System.out.printf("Put object = %s%n", object);
                queue.put(object);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "DelayQueueProducer{" +
                "name='" + name + '\'' +
                '}';
    }
}
