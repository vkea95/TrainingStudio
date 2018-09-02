package bittiger.io.thread;

public class DeadLockTest01 {
    public static void main(String[] args) {
        ThreadDemo1 demo1 = new ThreadDemo1();
        ThreadDemo2 demo2 = new ThreadDemo2();
        demo1.start();
        demo2.start();

    }

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    static class ThreadDemo1 extends Thread {
        public void run() {
            synchronized (lock1) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }

                synchronized (lock2) {
                    System.out.println("ThreadDemo1 !");
                }
            }
        }
    }

    static class ThreadDemo2 extends Thread {
        public void run() {
            synchronized (lock2) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }

                synchronized (lock1) {
                    System.out.println("ThreadDemo2 !");
                }
            }
        }
    }
}
