package jian.concurrent.chapter16;

import java.util.stream.IntStream;

public class EatNoodleTest {
    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
//        IntStream.range(0, 2).forEach(i -> new EatNoodleThread(String.valueOf(i), fork, knife).start());
//        Bug:如果俩人的tool顺序不是相反的话，就没有可能死锁
        new EatNoodleThread("A", fork, knife).start();
        new EatNoodleThread("B", knife, fork).start();
    }
}
