package jian.concurrent.chapter18;


import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

//final：防止类被集成
// 这里所谓的线程安全，就是多个线程输出同样的值，不是说A,B,C三个线程连续操作一个变量
//此处的设计模式很有用处
public final class IntegerAccumulatorV2 {
    private final int init;

    public IntegerAccumulatorV2(int init) {
        this.init = init;
    }

    public IntegerAccumulatorV2(IntegerAccumulatorV2 integerAccumulatorV2, int init) {
        this.init = integerAccumulatorV2.getValue() + init;
    }

    public int getValue() {
        return this.init;
    }

    public IntegerAccumulatorV2 add(int i) {//bug： 此时的返回值就只能是一个对象了
        return new IntegerAccumulatorV2(this, i);
    }


    public static void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntegerAccumulatorV2 integerAccumulator = new IntegerAccumulatorV2(0);

        IntStream.range(0, 4).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldVal = integerAccumulator.getValue();
                int res = integerAccumulator.add(inc).getValue();
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
