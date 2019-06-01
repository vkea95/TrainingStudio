package jian.concurrent.chapter19;

import edu.princeton.cs.algs4.In;

import java.util.concurrent.TimeUnit;

public class FutureServiceTest {

    public static void main(String[] args) throws InterruptedException {
//        FutureServiceTest.testStance();
        FutureServiceTest.testCallback();
    }

    private static void testCallback
            () throws InterruptedException {
        FutureService<String, Integer> futureService = FutureService.newService();
        Future<Integer> future = futureService.submit(input -> {

            try {
                TimeUnit.SECONDS.sleep(10);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "hello", System.out::println);

        System.out.println("Before getting.");
//        System.out.println(future.get());
        System.out.println("after getting.");
    }
    private static void testStance() throws InterruptedException {
        FutureService<String, Integer> futureService = FutureService.newService();
        Future<Integer> future = futureService.submit(input -> {

            try {
                TimeUnit.SECONDS.sleep(10);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return input.length();
        }, "hello");

        System.out.println("Before getting.");
        System.out.println(future.get());
        System.out.println("after getting.");

    }

    private static void testNull() throws InterruptedException {
        FutureService<Void, Void> futureService = FutureService.newService();

        Future<?> future = futureService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("I am finish done.");
        });
//        this method will made the current thread blocked
        System.out.println("Before getting.");
        future.get();
        System.out.println("after getting.");

    }
}
