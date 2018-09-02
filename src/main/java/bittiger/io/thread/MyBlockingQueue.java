package bittiger.io.thread;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue {

    public static void main(String[] args) {
        MyBlockingQueue myBlockingQueue = new MyBlockingQueue(5);

        Producer producer = new Producer(myBlockingQueue);
        Consumer consumer = new Consumer(myBlockingQueue);


        new Thread(consumer).start();
        new Thread(consumer).start();
        new Thread(producer).start();
        new Thread(producer).start();

    }


    private Lock lock = new ReentrantLock();
    private Condition isFull = lock.newCondition();
    private Condition isEmpty = lock.newCondition();

    private Deque<String> queue;
    private int size;

    public MyBlockingQueue(int size) {
        this.size = size;
        this.queue = new ArrayDeque<>();
    }


    public void put(String val) {
        System.out.println(((ReentrantLock) lock).isLocked());
        lock.lock();
        try {
            while (queue.size() == this.size) {
                isFull.await();//condition --》哪个线程call 这个await，哪个线程挂起
            }
            Thread.sleep(1000);
            this.queue.addLast(val);
            System.out.println("PUT:  " + val);
            isEmpty.signalAll();// 通知 isEmpty condition
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void take() {
        System.out.println(((ReentrantLock) lock).isLocked());
        lock.lock();
        try {
            while (queue.isEmpty()) {
                isEmpty.await();
            }
            Thread.sleep(100);
            String val = this.queue.removeFirst();
            System.out.println("TAKE: " + val);

            isFull.signalAll();// 通知 isEmpty condition
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static class Producer implements Runnable {
        MyBlockingQueue queue;

        Producer(MyBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                this.queue.put("Token " + i);
            }
        }
    }


    static class Consumer implements Runnable {
        MyBlockingQueue queue;

        Consumer(MyBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                this.queue.take();
            }
        }
    }
}
