package concurrency.practice.ch05.cache;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Memorizer1<K, V> implements Computable<K, V> {
    @Override
    public synchronized V compute(K arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    private final Map<K, V> cache = new HashMap<>();
    private final Computable<K, V> c;

    public Memorizer1(Computable<K, V> c) {
        this.c = c;
    }


}
