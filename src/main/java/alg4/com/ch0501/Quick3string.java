package alg4.com.ch0501;

import edu.princeton.cs.algs4.UF;

/**
 * Created by JianZhang on 8/27/17.
 */
public class Quick3string {

//    public static void sort(String a) {
//        sort(a, 0, a.length() - 1, 0);
////    }

    private static int charAt(String s, int d) {

        return d < s.length() ? s.charAt(d) : -1; //此时,返回-1的话,会让主程序的count的index到
    }

    private static void exec(String[] a, int l, int h) {
        String temp = a[l];
        a[l] = a[h];
        a[h] = temp;
    }


}
