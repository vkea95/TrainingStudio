package alg4.com.ch0201;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 5/29/17.
 */
public abstract class SortTemplate {
    public static void sort(Comparable[] a) {
    }

    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected static void show(Comparable[] a) {
        //print the data in the single line
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

}
