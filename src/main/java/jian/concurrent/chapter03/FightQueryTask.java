package jian.concurrent.chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;
    private final String dest;
    private final List<String> flightList = new ArrayList<>();

    //Bug: 此处的final 成员变量没有进行初始化，所以需要，在构造函数中进行

    public FightQueryTask(String airline, String origin, String dest) {
//        setup the name of the thread
        super("[" + airline + "]");
        this.origin = origin;
        this.dest = dest;
    }

    @Override
    public void run() {
//        printf supports %s
        System.out.printf("%s-query from %s to %s \n", getName(), origin, dest);
//        ThreadLocalRandom在java.util.concurrent包下，是一个与当前线程隔离的随机数生成器。
// 它通过为每个线程实例化一个随机数生成器，来减少系统开销和对资源的争用。
//通过current方法ThreadLocalRandom实例：
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flightList.add(getName() + "-" + randomVal);
            System.out.printf("The Fight: %s list query successful. \n", getName());

        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return flightList;
    }
}
