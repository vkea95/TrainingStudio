package alg4.com.ch0201;

/**
 * Created by JianZhang on 5/29/17.
 * Shell在最坏的情况下,时间复杂度是N^3/2
 */
public class Shell extends SortTemplate {

    public static void sort(Comparable[] a) {

        //sort a as asc
        int N = a.length;
        //initial value of h is 1
        int h = 1;
        while (h < N / 3) h = 3 * h + 1;//1, 4, 13, 40, 121, 364, 1093, ...
        while (h >= 1) {
            //change the array to h order
            for (int i = h; i < N; i++) { //-->此处循环是升序,步长是h
//                将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]...之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {//此处循环是逆序,步长是h
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        String[] a = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        sort(a);
        show(a);

    }
}
