package alg4.com.ch0101;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by JianZhang on 12/10/16.
 * 1.1.29 Equal keys. Add to BinarySearch a static method getLeftBoundary() that takes a key and a sorted array of int values
 * (some of which may be equal) as arguments and returns the number of elements that are smaller than the key and
 * a similar method count() that returns the number of elements equal to the key. Note : If i and j are the values
 * returned byrank(key, a)andcount(key, a)respectively,thena[i..i+j-1]arethevaluesin the array that are equal to key.
 * <p>
 * Problem:
 * 1.含有重复元素的情况下,使用二分法需要去重,需要关注的是这个变化条件,如果找等价的元素的最外延,那么就该是关注high
 */
public class P_29 {

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

        //boundary check
        if (a == null || a.length == 0) return -1;
        return getLeftBoundary(key, a, 0, a.length - 1);

    }

    public static int count(int key, int[] a) {
        if (a == null || a.length == 0) return -1;

        int low = 0;
        int high = a.length - 1;
        int leftBoundary = getLeftBoundary(key, a, low, high);
        int rightBoundary = getRightBoundary(key, a, low, high);
        return rightBoundary - leftBoundary + 1;

    }


    public static int getRightBoundary(int key, int[] a, int low, int high) {
        if (a == null || a.length == 0) return -1;

        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > key) high = mid - 1;
            else low = mid;
        }

        return a[high] == key ? high : -1;

    }

    public static int getLeftBoundary(int key, int[] a, int low, int high) {  // Index of key in a[], if present, is not smaller than lo
        //                                  and not larger than hi.
        if (low > high) return -1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] < key) low = mid + 1;
            else high = mid;
        }

        if (a[low] == key) {
            return low;
        } else {
            return -1;
        }
    }
}
