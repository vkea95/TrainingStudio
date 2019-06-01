package jian.concurrent.chapter19;

public interface Future<T> {
    //    after return the result, the method will be blocked if the task is not done
    T get() throws InterruptedException;

    boolean done();
}
