package jian.corejava.thread.scratch;

import jian.corejava.thread.scratch.BQ;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class JianBQ<T> implements BQ<T> {
    Deque<T> dq;
    int size;
    ReentrantLock lock;
    Condition isFull;
    Condition isEmpty;

    JianBQ(int size) {
        dq = new ArrayDeque<>();
        this.size = size;
        lock = new ReentrantLock();
        isFull = lock.newCondition();
        isEmpty = lock.newCondition();

    }

    public void put(T t) {
        lock.lock();
        try {
            while (dq.size() == size) {
                isFull.wait();
            }
            dq.addLast(t);
            isEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();// bug: name;
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        lock.lock();
        T t = null;
        try {
            while (dq.size() == 0) {
                isEmpty.wait();
            }
            t = dq.removeFirst();
            isFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();// name;
        } finally {
            lock.unlock();
        }
        return t;

    }
}