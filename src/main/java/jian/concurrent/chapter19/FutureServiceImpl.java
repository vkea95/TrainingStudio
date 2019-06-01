package jian.concurrent.chapter19;

import java.awt.datatransfer.SystemFlavorMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FutureServiceImpl<IN, OUT> implements FutureService<IN, OUT> {

    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";
    private final AtomicInteger nextCounter = new AtomicInteger(0);

    private String getNextName() {
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }


    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(() -> {
            runnable.run();
            // after finishing the task, null was passed to FutureTask as result
            futureTask.finish(null);
        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() ->
        {
//            Java 8 function coding
            OUT result = task.get(input);
            System.out.println("inside the submit");
            System.out.println(result);
            futureTask.finish(result);// trigger the finish method

        }, getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input, Callback<OUT> callback) {
        final FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(() ->
        {
//            Java 8 function coding
            OUT result = task.get(input);
            System.out.println("inside the submit");
            System.out.println(result);
            futureTask.finish(result);// trigger the finish method
            if (callback != null) {
                callback.call(result);
            }

        }, getNextName()).start();
        return futureTask;
    }
}
