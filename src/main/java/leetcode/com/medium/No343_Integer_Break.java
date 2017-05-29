package leetcode.com.medium;

/**
 * Created by tclresearchamerica on 5/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/integer-break/
 * ****************************************************
 * Description:
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize
 * the product of those integers. Return the maximum product you can get.
 * <p>
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * <p>
 * Note: you may assume that n is not less than 2.
 * ****************************************************
 * Analysis:
 * 1.x1+x2+...+xn=M
 * return Math.max(M1,M2..),bei
 * 2.网络答案:背后的数学道理
 * greedy: claim: every integer can be represented as m3, m*3+2, m*3+4 except 2,3 which is trivial. these 3 breaking
 * method give the max product.
 * In other words, break n into sum of only 2 and 3. maximum number of 3 gives max product.
 * assuming Math.pow(x,n) is O(logn), the time complexity is therefore O(logn)
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No343_Integer_Break {
    public static void main(String[] args) {
        No343_Integer_Break obj =new No343_Integer_Break();
        obj.integerBreak(10);
    }
    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n % 3 == 0) {
            return (int) Math.pow(3, n / 3);
        }
        if (n % 3 == 2) {
            return 2 * (int) Math.pow(3, (n - 2) / 3);
        }
        //余数为1的时候,就直接凑成4去和原来的数去做乘法好啦
        return 4 * (int) Math.pow(3, (n - 4) / 3); // n % 3 == 2
    }
}
