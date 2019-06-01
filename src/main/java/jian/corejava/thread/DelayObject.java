package jian.corejava.thread;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayObject implements Delayed {
    private String data;
    private long startTime;

    public DelayObject(String data, long delay) {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        System.out.println("in getDelay");
        return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.startTime, ((DelayObject) o).startTime);
    }

    @Override
    public String toString() {
        return "DelayObject{" +
                "data='" + data + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
