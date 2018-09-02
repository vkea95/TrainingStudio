package cci.com.ch15.p06;

public class FBThreadTest {
    public static void main(String[] args) {
        int n = 100;
        Thread[] threads = {
                new FBThread(i -> i % 3 == 0 && i % 5 == 0, i -> "FizzBuzz", n),
                new FBThread(i -> i % 3 == 0 && i % 5 != 0, i -> "Fizz", n),
                new FBThread(i -> i % 3 != 0 && i % 5 == 0, i -> "Buzz", n),
                new FBThread(i -> i % 3 != 0 && i % 5 != 0, i -> Integer.toString(i), n)
        };
        for (Thread t : threads) {
            t.start();
        }
    }
}
