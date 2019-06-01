package jian.concurrent.chapter27;

import jian.concurrent.chapter19.Future;
import jian.concurrent.chapter19.FutureTask;

import java.lang.reflect.Method;

public class ActiveMessage {
    private final Object[] objects;

    private final Method method;

    private final FutureTask<Object> future;

    private final Object service;

    private ActiveMessage(Builder builder) {
        this.objects = builder.objects;
        this.method = builder.method;
        this.future = builder.future;
        this.service = builder.service;
    }

    public void execute() {
        try {
            Object result = method.invoke(service, objects);
            if (future != null) {
                Future<?> realFuture = (Future<?>) result;
                Object realResult = realFuture.get();
                future.finish(result);
            }
        } catch (Exception e) {
            if (future != null) {
//                future.
                future.finish(null);
            }
        }
    }

    static class Builder {
        private Object[] objects;

        private Method method;

        private FutureTask<Object> future;

        private Object service;

        public Builder withObjects(Object[] objects) {
            this.objects = objects;
            return this;
        }

        public Builder useMethod(Method method) {
            this.method = method;
            return this;
        }

        public Builder returnFuture(FutureTask<Object> future) {
            this.future = future;
            return this;
        }

        public Builder forService(Object Service) {
            this.service = service;
            return this;
        }

        public ActiveMessage build() {
            return new ActiveMessage(this);
        }
    }
}
