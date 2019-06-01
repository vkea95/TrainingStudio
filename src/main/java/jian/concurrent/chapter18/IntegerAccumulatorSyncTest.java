package jian.concurrent.chapter18;

import java.util.stream.IntStream;

public class IntegerAccumulatorSyncTest {
    public static void main(String[] args) {
        IntegerAccumulator integerAccumulator = new IntegerAccumulator();

        IntStream.range(0, 4).forEach(i -> new Thread(() -> {
            int inc = 0;
            while (true) {
                int oldVal;
                int res;
//                同步synchronized(*.class)代码块的作用其实和synchronized static方法作用一样。Class锁对类的所有对象实例起作用。
                synchronized (IntegerAccumulator.class) {

                    oldVal = integerAccumulator.getValue();
                    res = integerAccumulator.add(inc);
                }
                System.out.println(oldVal + "+" + inc + "=" + res);
                if (inc + oldVal != res) {
                    System.out.println("ERROR:" + oldVal + "+" + inc + "=" + res);
                }
                inc++;
                IntegerAccumulator.slowly();
            }
        }).start());
    }
}
