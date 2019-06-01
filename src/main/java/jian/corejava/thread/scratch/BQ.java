package jian.corejava.thread.scratch;

public interface BQ<T> {
    void put(T t);

    T get();
}