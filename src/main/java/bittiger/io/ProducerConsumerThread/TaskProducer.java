package bittiger.io.ProducerConsumerThread;

import java.time.LocalDateTime;

public class TaskProducer implements Runnable {

    private Bucket bucket;

    public TaskProducer(Bucket bucket) {
        this.bucket = bucket;
    }


    @Override
    public void run() {
        // bug: 不知道该如何implement了 -> 首先这是个生产者消费者模型，而且array是fixed长度的
        while (true) {
            LocalDateTime now = LocalDateTime.now();
            Task task = new Task(now.toString());
            bucket.put(task);

            System.out.println("Put:" + Thread.currentThread().getName() +
                    " " + now + " put " + task);
        }
    }
}
