package jian.concurrent.chapter19;

public class FutureTask<T> implements Future<T> {

    private T result;

    private boolean isDone = false;

    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized (LOCK) {
            while (!isDone) {
                LOCK.wait();
            }
            return result;
        }

    }

//    此处完成了任务通知的工作，注意里面的if条件，会和get方法，形成呼应
    public void finish(T result) {
        synchronized (LOCK) {
//            balking design pattern
            if (isDone) {// reaction with get method
                return;
            }
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();// wake up the blocked thread
        }
    }

    @Override
    public boolean done() {
        return isDone;
    }
}
