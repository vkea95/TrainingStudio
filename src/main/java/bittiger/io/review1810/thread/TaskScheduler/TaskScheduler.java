package bittiger.io.review1810.thread.TaskScheduler;

import java.util.concurrent.DelayQueue;

//https://soulmachine.gitbooks.io/system-design/content/cn/task-scheduler.html
public class TaskScheduler {
    public static void main(String[] args) {
        DelayQueue<Task> queue = new DelayQueue<>();

        new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
    }
}
