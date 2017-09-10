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
    这个是关键过程,要用最左边的元素,当做哨兵,凡是比它小的,都到左边去,比它大的都到右边去，i从lo开始，j从hi+1开始，分别使用++i,—j最后再交换哨兵和j的位置
     */
    protected int partition(Comparable[] a, int lo, int hi) {
        int left = lo, right = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++left], v)) if (left == hi) break;
            while (less(v, a[--right])) if (right == lo) break;
            if (left >= right) break;
            exch(a, left, right);
        }
        exch(a, lo, right);

        return right;
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
