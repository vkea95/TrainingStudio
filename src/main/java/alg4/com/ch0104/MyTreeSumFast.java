package alg4.com.ch0104;

import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

/**
 * Created by JianZhang on 12/20/16.
 * This is much faster than old threesum
 */
public class MyTreeSumFast {
    public static int count(int[] a) {  // Count triples that sum to 0.
        int N = a.length;
        int cnt = 0;
        Arrays.sort(a);
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                if (BinarySearch.indexOf(a, -a[i] - a[j]) > j)
                    cnt++;
        return cnt;
    }
}
