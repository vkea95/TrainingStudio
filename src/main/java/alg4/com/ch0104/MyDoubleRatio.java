package alg4.com.ch0104;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by JianZhang on 12/20/16.
 */
public class MyDoubleRatio {

    public static double timeTrial(int N) {
        int MAX = 1000000;

        int[] array = new int[N];
        for (int i : array) {
            i = StdRandom.uniform(-MAX, MAX);
        }
        MyStopWatch myStopWatch = new MyStopWatch();
        int cnt = MyTreeSumFast.count(array);
        return myStopWatch.elapsedTime();
    }

    public static void main(String[] args) {
        double prev = timeTrial(125);
        int i = 0;
        for (int N = 250; i++ < 100; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%6d %7.1f ", N, time);
            StdOut.printf("%5.1f\n", time / prev);
            prev = time;
        }
    }
}
