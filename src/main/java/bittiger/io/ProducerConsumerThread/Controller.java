package bittiger.io.ProducerConsumerThread;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static void main(String[] args) {

        Bucket bucket = new Bucket(20);
        TaskConsumer putExecuter = new TaskConsumer(bucket);
        TaskProducer getExecuter = new TaskProducer(bucket);
        List<Thread> putThreads = new ArrayList<>();
        List<Thread> getThreads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            putThreads.add(new Thread(putExecuter));
        }
        for (int i = 0; i < 3; i++) {
            getThreads.add(new Thread(getExecuter));
        }
        putThreads.forEach(item -> item.start());
        getThreads.forEach(item -> item.start());
//        java.util.concurrent.ConcurrentHashMap
    }
}
