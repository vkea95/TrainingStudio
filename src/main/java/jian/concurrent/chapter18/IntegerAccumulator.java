package jian.concurrent.chapter18;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IntegerAccumulator {
    private int init;

    public IntegerAccumulator() {
        this(0);
    }

    public IntegerAccumulator(int init) {
        this.init = init;
    }

    public int add(int i) {
        this.init += i;
        return this.init;
    }

    public int getValue() {
        return this.init;
    }

    public static void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntegerAccumulator integerAccumulator = new IntegerAccumulator();

        IntStream.range(0, 4).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldVal = integerAccumulator.getValue();
                int res = integerAccumulator.add(inc);
                System.out.println(oldVal + "+" + inc + "=" + res);
                if (inc + oldVal != res) {
                    System.out.println("ERROR:" + oldVal + "+" + inc + "=" + res);
                }
                inc++;
                slowly();
            }
        }).start());

    }

}
