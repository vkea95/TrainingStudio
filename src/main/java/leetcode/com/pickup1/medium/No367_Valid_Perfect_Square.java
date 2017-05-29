package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/19/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/valid-perfect-square/
 * ****************************************************
 * Description:
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Note: Do not use any built-in library function such as sqrt.
 * Example 1:
 * Input: 16
 * Returns: True
 * Example 2:
 * Input: 14
 * Returns: False
 * ****************************************************
 * Thoughts:
 * 1. 完全没有想法,这是一道数学问题,有个数学公式,如下
 * This is a math problem：
 * 1 = 1
 * 4 = 1 + 3
 * 9 = 1 + 3 + 5
 * 16 = 1 + 3 + 5 + 7
 * 25 = 1 + 3 + 5 + 7 + 9
 * 36 = 1 + 3 + 5 + 7 + 9 + 11
 * ....
 * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn
 * 2.参阅之前的答案,发现是通过逼近的方式解决问题,但是为什么这么做,并不知道
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No367_Valid_Perfect_Square {

    public static void main(String[] args) {
        int j=1;
        int num=0;
        for (int i =0;i<5;i++){
            num+=j;
            System.out.println(num+" : "+j);

            j+=2;
        }
    }

    public boolean isPerfectSquare(int num) {

        long r = num;
        while (r * r > num) {
            r = (r + num / r) / 2;

        }
        return r * r == num;

    }

    public boolean isPerfectSquare_standard(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
}
