package jian.concurrent.chapter08;

public interface RunnableQueue {
    void offer(Runnable runnable);

    Runnable take() throws InterruptedException;

    int size();
}
