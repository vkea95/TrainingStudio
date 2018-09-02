package jian.alg.com;

interface Hashing<T> {
    void addServerNode(T t);

    void removeServerNode(T t);

    T getServerNode(String val);

    Long getHasing(String key);

}