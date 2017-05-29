package leetcode.com.easy.part1;

/**
 * Created by jason on 2016/1/23.
 * Locations:
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 * ********************************************************
 * Description
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 * **********************************************************
 * Solutions:
 * 零是由5的个数觉得的，所以算算多少个5就好了,用输入的数字除以5,直到除尽？例如25其实它是5*5所以是2个5
 * **********************************************************
 * Hindsight:
 * 1.其实在计算有多少个5时候,就要明白是5的乘法,所以光计算n/5是不够的,还要计算
 * **********************************************************
 * **********************************************************
 *
 */
public class No172_Factorial_Trailing_Zeroes {
    public static void main(String[] args) {
        No172_Factorial_Trailing_Zeroes no172 = new No172_Factorial_Trailing_Zeroes();
        System.out.println(no172.trailingZeroes(6));
    }

    public int trailingZeroes(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n / 5;
            n /= 5;
        }
        return sum;
    }
}
