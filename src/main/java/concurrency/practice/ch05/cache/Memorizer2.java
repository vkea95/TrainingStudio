package concurrency.practice.ch05.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memorizer2<K, V> implements Computable<K, V> {
    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final Computable<K, V> c;

    public Memorizer2(Computable<K, V> c) {
        this.c = c;
    }

    @Override
    public V compute(K arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
