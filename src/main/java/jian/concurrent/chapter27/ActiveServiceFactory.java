package jian.concurrent.chapter27;

import jian.concurrent.chapter19.Future;
import jian.concurrent.chapter19.FutureTask;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActiveServiceFactory {


    private final static ActiveMessageQueue queue = new ActiveMessageQueue();

    public static <T> T active(T instance) {
        Object proxy = Proxy.newProxyInstance(instance.getClass().getClassLoader(),
                instance.getClass().getInterfaces(),
                new ActiveInvocationHandler<>(instance));
        return (T) proxy;
    }


    private static class ActiveInvocationHandler<T> implements InvocationHandler {

        private final T instance;

        public ActiveInvocationHandler(T instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(ActiveMethod.class)) {
                this.checkMethod(method);
                ActiveMessage.Builder builder = new ActiveMessage.Builder();
                builder.useMethod(method).withObjects(args).forService(instance);
                Object result = null;
                if (this.isReturnFutureType(method)) {
                    result = new FutureTask<>();
                    builder.returnFuture((FutureTask) result);
                }
                queue.offer(builder.build());
                return result;
            } else {
                return method.invoke(instance, args);
            }

        }

        private void checkMethod(Method method) throws IllegalActivateMethod {
            if (!this.isReturnVoidype(method) && !this.isReturnFutureType(method)) {
                throw new IllegalActivateMethod("the method [ " + method.getName() + " return type must be void/Future");
            }
        }

        private boolean isReturnFutureType(Method method) {
            return method.getReturnType().isAssignableFrom(Future.class);
        }

        private boolean isReturnVoidype(Method method) {
            return method.getReturnType().isAssignableFrom(Void.class);
        }

    }
}
