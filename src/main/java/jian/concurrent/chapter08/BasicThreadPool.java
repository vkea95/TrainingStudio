package jian.concurrent.chapter08;

import com.sun.tools.corba.se.idl.toJavaPortable.DefaultFactory;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicThreadPool extends Thread implements ThreadPool {

    private final int initSize;

    private final int maxSize;

    private final int coreSize;

    private int activeCount;

    private final ThreadFactory threadFactory;

    private final RunnableQueue runnableQueue;

    private volatile boolean isShutdown;

    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();


    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory threadFactory, int queueSize, DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();

    }

    @Override
    public void run() {
        System.out.println("ready for running .......");
        System.out.println(isShutdown);
        System.out.println(isInterrupted());
        while (!isShutdown && !isInterrupted()) {
            try {
                System.out.println("timu unit sleep.");
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }

            synchronized (this) {
                if (isShutdown) {
                    break;

                }
                System.out.println("check if the queue is ok.");
                // 还有任务待处理，且队伍不满，则扩容
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                    continue;
                }
                //队伍未满，且有未处理的任务，则扩容
                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    System.out.println("judge if it's almost empty queue.");
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }

                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
        System.out.println("out of running .......");
    }

    private void removeThread() {
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown()) {
            throw new IllegalStateException("the threadpool is destoryed");
        }
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }
            isShutdown = true;

            threadQueue.forEach(threadTask -> {
                //TODO: 停掉线程
                threadTask.internalTask.stop();
                //TODO : why  关掉线程
                threadTask.thread.interrupt();
            });
            //TODO : why
            this.interrupt(); //
        }
    }

    @Override
    public int getInitSize() {
        if (isShutdown) {
            throw new IllegalStateException("the thread pool is destory");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutdown) {
            throw new IllegalStateException("the thread pool is destory");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutdown) {
            throw new IllegalStateException("the thread pool is destory");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutdown) {
            throw new IllegalStateException("the thread pool is destory");
        }
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
//        TODO: 此处需要synchronization
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    public static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

        //        TODO:此处final 元素依然可以修改，为啥要修改呢
        private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndDecrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndDecrement());
        }
    }

    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.internalTask = internalTask;
            this.thread = thread;
        }
    }
}
