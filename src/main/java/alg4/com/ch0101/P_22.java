package alg4.com.ch0101;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by JianZhang on 12/9/16.
 * WriteaversionofBinarySearchthatusestherecursiverank()givenonpage 25 and traces the method calls.
 * Each time the recursive method is called, print the argu- ment values lo and hi,
 * indented by the depth of the recursion. Hint: Add an argument to the recursive method that keeps track of the depth.
 */
public class P_22 {

    public static void main(String[] args) {

        int[] whitelist = In.readInts(args[0]);

        Arrays.sort(whitelist);

        // read key; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (rank(key, whitelist) == -1)
                StdOut.println(key);
        }
    }

    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1, 0);
    }

    public static int rank(int key, int[] a, int lo, int hi, int depth) {  // Index of key in a[], if present, is not smaller than lo
        System.out.println("depth: " + depth);
        //                                  and not larger than hi.
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) return rank(key, a, lo, mid - 1, ++depth);
        else if (key > a[mid]) return rank(key, a, mid + 1, hi, ++depth);
        else return mid;
    }

}
