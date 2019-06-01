package concurrency.practice.ch05;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        public ProductInfo call() {
            return null;
        }
    });
    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() {
        try {
            return future.get();
        } catch (Exception e) {
            Throwable cause = e.getCause();
//            if (cause instanceof DataLoadException){
//                throw (DataLoadException) cause;
//            } else
//                throw launderThrowable(cause);
        }
        return null;
    }
}

class ProductInfo {

}