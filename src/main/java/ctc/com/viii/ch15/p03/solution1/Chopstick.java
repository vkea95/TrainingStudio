package ctc.com.viii.ch15.p03.solution1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private Lock lock;

    public Chopstick() {
        this.lock = new ReentrantLock();
    }

    public boolean pickUp() {
        // if available the lock successfully return true;, else return false
        return lock.tryLock();
    }

    public void putDown() {
        lock.unlock();
    }
}
