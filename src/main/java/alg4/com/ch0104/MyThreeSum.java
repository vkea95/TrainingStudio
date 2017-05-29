package alg4.com.ch0104;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by JianZhang on 12/19/16.
 */
public class MyThreeSum {
    public static int count(int[] a) {  // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        cnt++;
        return cnt;
    }

    public static void main(String[] args) {
//        int[] a = In.readInts(args[0]);
//        StdOut.println(count(a));
        int N = Integer.parseInt(args[0]);
        if (N <= 0) return;
        int[] array = new int[N];
        for (int a : array) {
            a = StdRandom.uniform(-1000000, 1000000);
        }
        MyStopWatch stopWatch = new MyStopWatch();
        int cnt = MyThreeSum.count(array);
        double time = stopWatch.elapsedTime();
        StdOut.println(cnt + " triples " + time);
    }
}
