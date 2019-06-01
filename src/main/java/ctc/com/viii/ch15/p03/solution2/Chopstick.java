package ctc.com.viii.ch15.p03.solution2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    // config the number for every chopstick
    private int number;
    private Lock lock;

    public Chopstick(int number) {

        this.number = number;
        this.lock = new ReentrantLock();
    }

    public void pickUp() {
        // if available the lock successfully return true;, else return false
        lock.lock();// tryLock()-> lock
    }

    public void putDown() {
        lock.unlock();
    }

    public int getNumber() {
        return number;
    }

}

