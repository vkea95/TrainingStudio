package alg4.com.ch0203;

import java.awt.event.ComponentAdapter;


/**
 * Created by JianZhang on 6/24/17.
 * 未进行验证的做法。
 */
public class Ch020315 {

    public void sort(Comparable[] nuts, Comparable[] bolts) {
        Comparable v = bolts[0];

        sort(nuts, bolts, 0, nuts.length - 1, nuts[0]);


    }

    private void sort(Comparable[] nuts, Comparable[] bolts, int lo, int hi, Comparable v) {
        if (hi<=lo)return;
        int i = partition(nuts, lo, hi, v);
        int j = partition(bolts, lo, hi, nuts[i]);
        sort(nuts, bolts, lo, i - 1, bolts[lo]);
        sort(nuts, bolts, i + 1, hi, bolts[lo]);


    }

    public int partition(Comparable[] main, int lo, int hi, Comparable v) {
        int i = lo - 1, j = hi + 1;

//        Comparable v = aux[lo];
        int vInde = lo;

        while (true) {
            while (less(main[++i], v)) if (i == hi) break;//bug 1 没有设置break 条件
            while (less(v, main[--j])) if (j == lo) break;//bug 2 没有设置break 条件 bug3 less里面的元素可以互换呢
            if (i >= j) break;
            exch(main, i, j);
//            exch(aux, i, j);
            if (equal(main[i], v)) vInde = i;
            if (equal(main[j], v)) vInde = j;


        }
        //此处不太确定,是否可以在内循环搞定vInde,以及外循环是否只考虑i 或j中的一个,还是两个都考虑
        if (equal(main[i], v)) vInde = i;
        if (equal(main[j], v)) vInde = j;

        return vInde;
    }

    private boolean equal(Comparable i, Comparable v) {
        return i.compareTo(v) == 0 ;
    }

    private boolean less(Comparable i, Comparable v) {
        return i.compareTo(v) < 0 ;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
