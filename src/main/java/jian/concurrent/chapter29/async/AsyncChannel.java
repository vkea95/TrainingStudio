package jian.concurrent.chapter29.async;

import jian.concurrent.chapter29.sync.Channel;
import jian.concurrent.chapter29.sync.Event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AsyncChannel implements Channel<Event> {
    private final ExecutorService executorService;

    public AsyncChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public AsyncChannel() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    @Override
    public void dispatch(Event message) {
        executorService.submit(() -> this.handle(message));
    }

    protected abstract void handle(Event message);

    void stop() {
        if (null != executorService && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
