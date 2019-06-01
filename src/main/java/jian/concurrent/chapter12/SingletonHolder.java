package jian.concurrent.chapter12;

import bittiger.io.thread.Singleton;

//SingletonHolder初始化的时候，不会实例化Holder
public class SingletonHolder {

    private SingletonHolder() {

    }

    private static class Holder {
        private static SingletonHolder instance = new SingletonHolder();
    }

    public SingletonHolder getInstance() {
        return Holder.instance;
    }
}
