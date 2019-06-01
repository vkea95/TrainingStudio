package jian.concurrent.chapter19;



public interface FutureService<IN, OUT> {
    Future<?> submit(Runnable runnable);// Futrue.get will return null,

    Future<OUT> submit(Task<IN, OUT> task, IN input);

    Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback);

    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }

}
