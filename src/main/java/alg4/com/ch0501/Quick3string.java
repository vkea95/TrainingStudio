package alg4.com.ch0501;

import edu.princeton.cs.algs4.UF;

/**
 * Created by JianZhang on 8/27/17.
 */
public class Quick3string {

    private static int charAt(String s, int d) {

        return d < s.length() ? s.charAt(d) : -1; //此时,返回-1的话,会让主程序的count的index到
    }

    public static void sort(String a) {

    }

    private static void exec(String[] a, int l, int h) {
        String temp = a[l];
        a[l] = a[h];
        a[h] = temp;
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;

        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) exec(a, lt++, i++);
            else if (t > v) exec(a, gt--, i);
            else i++;
        }
        //a[lo..lt-1]< v =a[lt..gt] < a[gt+1..hi]------>!!!!!! 要记住lt,gt是等值的边界,
        sort(a, lo, lt - 1, d);
        if (v >= 0) sort(a, lt, gt, d + 1);//-->继续比较下一部分字符的大小
        sort(a, gt + 1, hi, d);
//        UF
    }

}
