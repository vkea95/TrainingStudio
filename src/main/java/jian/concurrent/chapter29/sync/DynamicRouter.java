package jian.concurrent.chapter29.sync;

public interface DynamicRouter<E extends Message> {
    void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

    void dispatch(E message) throws MessageMatcherException;
}
