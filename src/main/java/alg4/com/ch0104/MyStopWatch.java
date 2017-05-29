package alg4.com.ch0104;

/**
 * Created by JianZhang on 12/19/16.
 */
public class MyStopWatch {
    private final long start;

    public MyStopWatch() {
        //get the current timestamp
        start = System.currentTimeMillis();
    }

    //此处用double,是因为要将毫秒转换为秒
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

}
