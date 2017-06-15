package alg4.com.ch0203;

/**
 * Created by JianZhang on 6/15/17.
 */
public class Quick {
    public void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    protected void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);

    }

    /*
    这个是关键过程,要用最左边的元素,当做哨兵,凡是比它小的,都到左边去,比它大的都到右边去
     */
    protected int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);

        return j;
    }

    protected boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    protected void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
