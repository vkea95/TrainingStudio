package jian.concurrent.chapter29.sync;

public interface Message {
    Class<? extends Message> getType();
}
