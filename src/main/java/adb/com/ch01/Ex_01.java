package adb.com.ch01;

import java.net.Inet4Address;

/**
 * Created by tclresearchamerica on 11/23/16.
 */
public class Ex_01 {
    public static void main(String[] args) {

        Ex_01 ex_01 = new Ex_01();
        int n = 31415;
        int m = 14142;
        n = 100;
        m = 25;
        m = Math.abs(m);
        System.out.println(ex_01.getGCD(n, m));

        double res = 1.0 / 0.0;
        int a1 = (-14) / 3;
        int r1 = (-14) % 3;
        int a2 = 14 / (-3);
        int r2 = 14 % (-3);
        Integer[] myInt = new Integer[3];
        myInt[0] = 123;
        Integer[] yourInt = new Integer[3];
        for (int i = 0; i < myInt.length; i++) {
            yourInt[i] = myInt[i];
        }

        yourInt[0] = 79;
        System.out.print("myInt[0]=" + myInt[0]);
        System.out.print("yourInt[0]=" + yourInt[0]);
    }

    public int getGCD(int n, int m) {
        //boundary check
        if (n < m) return getGCD(m, n);
        if (m == 0) return n;

        n = n % m;
        return getGCD(m, n);

    }
}
