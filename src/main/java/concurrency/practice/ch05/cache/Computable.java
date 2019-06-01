package concurrency.practice.ch05.cache;

public interface Computable<K, V> {
    V compute(K arg) throws InterruptedException;
}
