package jian.concurrent.chapter05;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {
    void lock() throws InterruptedException;// bug：没有throws exception

    void lock(long millSeconds) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreads();

}
