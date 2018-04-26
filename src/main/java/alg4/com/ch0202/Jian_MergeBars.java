package alg4.com.ch0202;

/**
 * Created by JianZhang on 6/8/17.
 * <p>
 * 根据书上的说法,如果有数组比较小的情况下,
 * 用Inseration排序可以提高效率
 * <p>
 * Use insertion sort for small subarrays. We can improve most recursive algorithms by handling small cases differently,
 * because the recursion guarantees that the method will be used often for small cases,
 * so improvements in handling them lead to improvements in the whole algorithm. In the case of sorting,
 * we know that insertion sort (or selection sort) is simple and therefore likely to be faster than mergesort for tiny
 * subarrays. As usual, a visual trace provides insight into the operation of mergesort.
 * The visual trace on the facing page shows the operation of a mergesort implementation with a cutoff for small
 * subarrays. Switching to insertion sort for small subarrays (length 15 or less, say) will improve the running time
 * of a typical mergesort implementation by 10 to 15 percent (see Exercise 2.2.23).
 */
public class Jian_MergeBars {

    private static final int CUTOFF = 12;

    private Comparable[] a = null;

    private Jian_MergeBars() {
    }

    public void sort(Comparable[] array) {
        int len = array.length;
        a = new Comparable[len];


    }

    private void sort(Comparable[] array, Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int n = hi - lo + 1;
        if (n < CUTOFF) {
            inseration(array, lo, hi);
        }
        int mid = lo + (hi - lo) / 2;
        if (less(array[mid], array[mid + 1])) return;
        sort(array, a, lo, mid);
        sort(array, a, mid + 1, hi);
        merge(array, a, lo, mid, hi);

    }

    private void inseration(Comparable[] array, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)// j>lo 保证了没有问题,插入排序,就是局部有序,然后递减index
                exch(array, j, j - 1);
        }
    }

    private void exch(Comparable[] array, int lo, int hi) {
        Comparable temp = array[lo];
        array[lo] = array[hi];
        array[hi] = temp;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) == -1 ? true : false;
    }

    private void merge(Comparable[] array, Comparable[] a, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            a[i] = array[i];
        }
        int i = lo;
        int j = hi;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) array[k] = a[j++];
            else if (j > hi) array[k] = a[i++];
            else if (less(a[i], a[j])) array[k] = a[i++];
            else array[k] = a[j++];
        }
    }
}
