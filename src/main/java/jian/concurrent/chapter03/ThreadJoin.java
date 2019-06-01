package jian.concurrent.chapter03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
//        important: Java 8
        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoin::create).collect(Collectors.toList());

//        threads.forEach(Thread::start);
// 因为下面的三行代码，所以main thread被block住了，所以main后面的程序就可以停止了（但因为第二个程序已经开始执行了，所以它不会停止）
        for (Thread thread : threads) {
            thread.start();
            thread.join();
//            shortSleep();

        }

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }
    }

    //    生成Thread实例，然后每个threa短暂休眠
    private static Thread create(int seq) {
        return new Thread(() ->
        {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        }, String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
