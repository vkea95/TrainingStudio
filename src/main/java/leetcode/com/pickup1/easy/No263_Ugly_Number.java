package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/8/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/ugly-number/
 * ****************************************************
 * Description:
 * Write a program to check whether a given number is an ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not
 * ugly since it includes another prime factor 7.
 * <p>
 * Note that 1 is typically treated as an ugly number.
 * ****************************************************
 * Thoughts:
 * 1.一个数字的因子如果只是由2,3和5构成,那么他就是一个丑数
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No263_Ugly_Number {
    public boolean isUgly(int num) {
        boolean result = false;
        if (num <= 0) return false;
        if (num == 1) return true;

        num = divided(num, 2);
        num = divided(num, 3);
        num = divided(num, 5);


        return num == 1;
    }

    private int divided(int num, int div) {
        int rst = num;

        do {
            num = rst;
            rst = num / div;
        } while (rst * div == num);
        return num;
    }


    private int divided_2(int num, int div) {
        while (num >= div && num % div == 0) num /= div;
        return num;
    }


}
