package concurrency.practice.ch05.cache;

import java.util.Map;
import java.util.concurrent.*;

public class Memorizer4<K, V> implements Computable<K, V> {
    //    TODO: Future type is Very important to the map
    private final Map<K, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<K, V> c;

    public Memorizer4(Computable<K, V> c) {
        this.c = c;
    }

    @Override
    public V compute(K arg) throws InterruptedException {
        while (true) {

            Future<V> f = cache.get(arg);
            if (f == null) {
                //    TODO: take care of calling future task through callable interface

                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(eval);
                //    TODO: putIfAbsent is an automatic operation, so it's safe

                f = cache.putIfAbsent(arg, ft);
                if (f == null) {
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg, f);
            } catch (ExecutionException e) {
                throw new InterruptedException(e.getMessage());
            }
        }
    }
}
