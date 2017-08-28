package alg4.com.ch0501;

import edu.princeton.cs.algs4.Insertion;

/**
 * Created by JianZhang on 8/27/17.
 */
public class MSD {

    private static int R = 256;
    private static final int M = 15;//小数组的切换阈值
    private static String[] aux;

    private static int charAt(String s, int d) {

        return d < s.length() ? s.charAt(d) : -1; //此时,返回-1的话,会让主程序的count的index到
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        //以第d个字符为键将a[lo]和a[hi]排序
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi);
        }

        int[] count = new int[R + 2];//---> different from LSD[R+1]
        for (int i = lo; i < hi; i++) {//----->!!!!!
            count[charAt(a[i], d) + 2]++; // 长度为d的字符,数量会被记在count的第1个位置上
        }

        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        for (int i = lo; i <= hi; i++) {//----->!!!!!
            aux[count[charAt(a[i], d) + 1]++] = a[i];// why 此处是+1,而不是+2呢? 这个是和当d为最后一个字符的时候,返回-1有关
        }

        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];//因为aux是从第0位开始的----->!!!!!
        }

        for (int r = 0; r < R; r++)//递归地以每个字符为键进行排序!!!
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }
}
