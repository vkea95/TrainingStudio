package bittiger.io.review1810.thread.TaskScheduler;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

// Ref: https://soulmachine.gitbooks.io/system-design/content/cn/task-scheduler.html
//JDK中有一个接口java.util.concurrent.Delayed，可以用于表示具有过期时间的元素，刚好可以拿来表示任务这个概念
//A mix-in style interface for marking objects that should be acted upon after a given delay.
//An implementation of this interface must define a compareTo method that provides an ordering consistent with its getDelay method.
public class Task implements Delayed {// 因为DelayQueue的元素必须是extends Delayed

    private String name;
    private long startTime; // milliseconds


    public Task(String name, long delay) {
        this.name = name;
        this.startTime = System.currentTimeMillis() + delay; // bug: get startTime
    }

    @Override
    public long getDelay(TimeUnit unit) { // bug:  Time unit representing one thousandth of a microsecond
        long diff = startTime - System.currentTimeMillis();

        return unit.convert(diff, TimeUnit.MILLISECONDS); // 将时间转换为milliseconds
    }

    @Override
    public int compareTo(Delayed o) {
        // bug : 1. int -> long强制类型转换 2. o-> Task强制类型转换：要有两个括号
        return (int) (this.startTime - ((Task) o).startTime);
    }

    @Override
    public String toString() {
        return "task " + name + " at " + startTime;
    }
}
