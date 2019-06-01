package bittiger.io.review1810.thread.TaskScheduler;

import edu.princeton.cs.algs4.In;

import java.util.concurrent.DelayQueue;
//DelayQueue这个方案，每个消费者线程只需要等待所需要的时间差，因此响应速度更快。它内部用了一个优先队列，所以插入和删除的时间复杂度都是\log nlogn。
public class TaskConsumer implements Runnable {
    private DelayQueue<Task> q;


    public TaskConsumer(DelayQueue<Task> q) {
        this.q = q;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Task task = q.take();
                System.out.println("take " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
