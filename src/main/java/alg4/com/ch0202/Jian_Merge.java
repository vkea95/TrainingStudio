package alg4.com.ch0202;

import alg4.com.ch0201.SortTemplate;

/**
 * Created by JianZhang on 6/4/17.
 */
public class Jian_Merge extends SortTemplate {
    public static Comparable[] aux;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];

    }

    public static void sort(Comparable[] a, int lo, int hi) {

        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = hi;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];// copy a[lo..hi]--> aux[lo..hi]
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = a[j++];//bug1: 在使用i和j之前,先对i&j的范围进行限定
            else if (j > mid) a[k] = a[i++];
            else if (less(a[j], a[i])) a[k] = a[j++];//bug2: comparable的数组,需要通过less方法来实现比较操作而不是 直接使用<
            else a[k] = a[i++];
        }

    }


}
