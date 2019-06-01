package bittiger.io.review1810.thread.TaskScheduler;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

public class TaskProducer implements Runnable {

    private final Random random = new Random();

    private DelayQueue<Task> q;

    public TaskProducer(DelayQueue<Task> q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int delay = random.nextInt(10000);
                Task task = new Task(UUID.randomUUID().toString(), delay);
                System.out.println("Put " + task);
                q.put(task);

                Thread.sleep(3000); // bug ---> 只有它会抛出 InterruptedException
            } catch (InterruptedException e) {// bug: forget to add the exception
                e.printStackTrace();
            }
        }
    }
}
