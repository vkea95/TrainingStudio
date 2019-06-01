package jian.corejava.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

public class DeayQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Delayed> queue = new DelayQueue<>();
        DelayQueueProducer pd = new DelayQueueProducer("Producer Thread-1", queue);
        DelayQueueConsumer consumer = new DelayQueueConsumer("Consumer Thread-1", queue);
        List<Thread> pdList = new ArrayList<>();
        List<Thread> conList = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            pdList.add(new Thread(pd));
        }

        for (int i = 0; i < 1; i++) {
            conList.add(new Thread(consumer));
        }
        pdList.forEach(item -> item.start());
        conList.forEach(item -> item.start());
    }
}
