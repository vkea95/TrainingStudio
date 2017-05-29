package alg4.com.ch0104;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.ThreeSumFast;

/**
 * Created by JianZhang on 12/20/16.
 */
public class DoublingTest {

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
        int i = 0;
        for (int N = 250; i++ < 100; N += N) {
            double time = timeTrial(N);
            StdOut.printf("%7d %5.1f\n", N, time);
        }
    }
}
