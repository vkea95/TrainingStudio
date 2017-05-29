package leetcode.com.easy.part2;

/**
 * Created by jason on 2016/1/8
 * Locations:
 * https://leetcode.com/problems/power-of-two/.
 * ****************************************************
 * Solutions:
 * Power of 2: looks like 10....0 in binary.
 * (power of 2) -1: looks like 0111...1 in binary
 * so the intersection of (power of 2) and [(power of 2) -1 ] must be 0
 * ****************************************************
 * Comments:
 * The input parameter is integer, so the program need to check if the number is minus number.
 */
public class No231_Power_of_Two {
    public static void main(String[] args) {
        No231_Power_of_Two.isPowerOfTwo(-2147483648);
    }

    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        int n1 = n - 1;
        return (n & n1) == 0 ? true : false;
    }

}
