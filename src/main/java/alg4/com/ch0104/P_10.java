package alg4.com.ch0104;

/**
 * Created by JianZhang on 12/20/16.
 * 1.4.10 Modify binary search so that it always returns the element with the smallest index that matches
 * the search element (and still guarantees logarithmic running time).
 * Bug:
 * 因为要寻找match的下限,所以在binarySearch中的while循环条件要用(lo+1<hi),而不能用lo<=hi或是lo<hi
 */
public class P_10 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 2, 2, 2, 3, 3, 3, 5, 6, 6, 7};
        System.out.println("index of 1 is " + MyBinarySearch.indexOf(a, 1));
        System.out.println("index of 2 is " + MyBinarySearch.indexOf(a, 2));
        System.out.println("index of 3 is " + MyBinarySearch.indexOf(a, 3));
        System.out.println("index of 5 is " + MyBinarySearch.indexOf(a, 5));
        System.out.println("index of 6 is " + MyBinarySearch.indexOf(a, 6));
        System.out.println("index of 7 is " + MyBinarySearch.indexOf(a, 7));
        System.out.println("index of 8 is " + MyBinarySearch.indexOf(a, 8));
    }
}

class MyBinarySearch {

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param a   the array of integers, must be sorted in ascending order
     * @param key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        //bug
        while (lo + 1 < hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) hi = mid;
            else lo = mid;
        }
        if (a[lo] == key) return lo;
        else if (a[hi] == key) return hi;
        else return -1;
    }

}