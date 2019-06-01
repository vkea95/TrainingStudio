package jian.concurrent.chapter08;

@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
