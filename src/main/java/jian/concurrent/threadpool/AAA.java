package jian.concurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AAA {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor service4 = (ScheduledThreadPoolExecutor) Executors
                .newScheduledThreadPool(2);
        /*
        我们看到，ThreadPoolExecutor 也就是线程池有 7 个参数，我们一起来好好看看：

corePoolSize 线程池中核心线程数量
maximumPoolSize 最大线程数量
keepAliveTime 空闲时间（当线程池梳理超过核心数量时，多余的空闲时间的存活时间，即超过核心线程数量的空闲线程，在多长时间内，会被销毁）
unit 时间单位
workQueue 当核心线程工作已满，需要存储任务的队列
threadFactory 创建线程的工厂
handler 当队列满了之后的拒绝策略
前面几个参数我们就不讲了，很简单，主要是后面几个参数，队列，线程工厂，拒绝策略。

我们先看看队列，线程池默认提供了 4 个队列。

无界队列： 默认大小 int 最大值，因此可能会耗尽系统内存，引起OOM，非常危险。
直接提交的队列 ： 没有容量，不会保存，直接创建新的线程，因此需要设置很大的线程池数。否则容易执行拒绝策略，也很危险。
有界队列：如果core满了，则存储在队列中，如果core满了且队列满了，则创建线程，直到maximumPoolSize 到了，如果队列满了且最大线程数已经到了，则执行拒绝策略。
先级队列：按照优先级执行任务。也可以设置大小。
楼主在自己的项目中使用了无界队列，但是设置了任务大小，1024。如果你的任务很多，建议分为多个线程池。不要把鸡蛋放在一个篮子里。
再看看拒绝策略，什么是拒绝策略呢？当队列满了，如何处理那些仍然提交的任务。JDK 默认有4种策略。

AbortPolicy ：直接抛出异常，阻止系统正常工作.
CallerRunsPolicy : 只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降。
DiscardOldestPolicy: 该策略将丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务.
DiscardPolicy: 该策略默默地丢弃无法处理的任务，不予任何处理，如果允许任务丢失，我觉得这是最好的方案.
当然，如果你不满意JDK提供的拒绝策略，可以自己实现，只需要实现 RejectedExecutionHandler 接口，并重写 rejectedExecution 方法即可。
        */
        // 如果前面的任务没有完成，则调度也不会启动
        service4.scheduleAtFixedRate(new Runnable() {
                                         @Override
                                         public void run() {
                                             try {
                                                 // 如果任务执行时间大于间隔时间，那么就以执行时间为准（防止任务出现堆叠）。
                                                 Thread.sleep(10000);
                                                 System.out.println(System.currentTimeMillis() / 1000);
                                             } catch (InterruptedException e) {
                                                 e.printStackTrace();
                                             }
                                         }
                                         // initialDelay（初始延迟） 表示第一次延时时间 ; period 表示间隔时间
                                     }
                , 0, 2, TimeUnit.SECONDS);
        service4.scheduleWithFixedDelay(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    Thread.sleep(5000);
                                                    System.out.println(System.currentTimeMillis() / 1000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            // initialDelay（初始延迟） 表示延时时间；delay + 任务执行时间 = 等于间隔时间 period
                                        }
                , 0, 2, TimeUnit.SECONDS);
        // 在给定时间，对任务进行一次调度
        service4.schedule(new Runnable() {
                              @Override
                              public void run() {
                                  System.out.println("5 秒之后执行 schedule");
                              }
                          }
                , 5, TimeUnit.SECONDS);
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
