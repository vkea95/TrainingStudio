package core.thread.synchronize;

public class SynchronizedCounter {
    private static  int count = 0;

    public synchronized static void increment() {
        ++count;
        System.out.println("Count is " + count + " @ " + System.nanoTime());
    }

    public synchronized static void decrement() {
        --count;
        System.out.println("Count is " + count + " @ " + System.nanoTime());
    }
}