package jian.concurrent.chapter19;


public interface FutureServcie<IN, OUT> {
    Future<?> submit(Runnable runnable);// Futrue.get will return null,
    Future<OUT> submit(Task<IN,OUT> task, IN input);
}
