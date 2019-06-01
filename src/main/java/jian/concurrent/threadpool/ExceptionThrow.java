package jian.concurrent.threadpool;

import java.util.concurrent.*;

public class ExceptionThrow {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L,
                TimeUnit.MILLISECONDS, new SynchronousQueue<>());
//        TODO:zero exception are not throwed from the source
        for (int i = 0; i < 5; i++) {
            executor.submit(new NoExceptionThrow.DivTask(100, i));
        }

    }

    static class TraceThreadPoolExecutor extends ThreadPoolExecutor {
        public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                       TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        public void execute(Runnable command) {
            //      super.execute(command);
//             TODO: wrap the source
            super.execute(wrap(command, clientTrace(), Thread.currentThread().getName()));
        }

        @Override
        public Future<?> submit(Runnable task) {
            //      return super.submit(task);
//             TODO: wrap the source
            return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
        }

        private Exception clientTrace() {
            return new Exception("Client stack trace");
        }

        private Runnable wrap(final Runnable task, final Exception clientStack,
                              String clientThreaName) {
            return new Runnable() {
                @Override
                public void run() {
                    try {
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                        clientStack.printStackTrace();
                        throw e;
                    }
                }
            }
                    ;
        }
    }
}
