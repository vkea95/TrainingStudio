package cci.com.ch15.p06;

import java.util.function.Function;
import java.util.function.Predicate;

public class FBThread extends Thread {
    private static Object lock = new Object();
    protected static int current = 1;
    private Predicate<Integer> validate;
    private Function<Integer, String> printer;
    int x = 1;
    private int max;

    public FBThread(Predicate<Integer> validate, Function<Integer, String> printer, int max) {
        this.validate = validate;
        this.printer = printer;
        this.max = max;
    }

    public void run() {
        while (true) {
            synchronized (lock) {
                if (current > max) {
                    return;
                }
                if (validate.test(current)) {
                    System.out.println(printer.apply(current));
                    current++;
                }
            }
        }
    }
}
