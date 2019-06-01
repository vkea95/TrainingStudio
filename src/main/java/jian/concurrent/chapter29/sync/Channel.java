package jian.concurrent.chapter29.sync;

public interface Channel<E extends Message> {
    void dispatch(E message);
}
