package alg4.com.ch0104;

/**
 * Created by JianZhang on 12/20/16.
 * Write a program that, given two sorted arrays of N int values, prints all elements that appear in both arrays,
 * in sorted order. The running time of your program should be proportional to N in the worst case.
 */
public class P_12 {
    /*
    This method is working for non-duplicate element.
     */
    public static void findSame(int[] a, int[] b) {
        int i = 0;
        int j = 0;
        for (; i < a.length; i++) {
            for (; j < b.length; j++) {
                if (a[i] == b[j]) {
                    System.out.print(a[i]);
                    j++;
                    break;
                } else if (b[j] > a[i]) {
                    break;
                }
            }
        }

    }
}
