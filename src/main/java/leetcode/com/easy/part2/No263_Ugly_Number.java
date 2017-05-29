package leetcode.com.easy.part2;

/**
 * Created by tclresearchamerica on 5/1/16.
 * *********************************************
 * Location:
 * https://leetcode.com/problems/ugly-number/
 * *********************************************
 * Description:
 * Write a program to check whether a given number is an ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is
 * not ugly since it includes another prime factor 7.
 * <p>
 * Note that 1 is typically treated as an ugly number.
 * <p>
 * *********************************************
 * Analysis:
 * 将输入的数分别对2,3,和5进行整除,直到不能除尽,最后只要这个数等于1即可
 * *********************************************
 */
public class No263_Ugly_Number {
    public boolean isUgly(int num) {
        //bug1:0会导致无限循环...
        if (num == 0) return false;
        int div = num;

        do {
            num = div;
            div = num / 2;
        }
        while (div * 2 == num);

        div = num;
        do {
            num = div;
            div = num / 3;
        }
        while (div * 3 == num);

        div = num;
        do {
            num = div;
            div = num / 5;
        }
        while (div * 5 == num);

        return num == 1;


    }

    /**
     * @param num an integer
     * @return true if num is an ugly number or false
     */
    public boolean isUgly_II(int num) {
        if (num <= 0) return false;
        if (num == 1) return true;

        while (num >= 2 && num % 2 == 0) num /= 2;
        while (num >= 3 && num % 3 == 0) num /= 3;
        while (num >= 5 && num % 5 == 0) num /= 5;

        return num == 1;
    }
}
