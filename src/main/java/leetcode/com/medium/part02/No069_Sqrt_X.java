package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/2/23.
 * Location:
 * https://leetcode.com/problems/sqrtx/
 * *************************************
 * Description:
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 * *************************************
 * Solution:
 * 极限逼近法求解，从左右两侧不断逼近求解
 * find the last number which square of it <= x
 */
public class No069_Sqrt_X {
    public int mySqrt(int x) {
        long start = 1;
        long end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (end * end <= x) {
            return (int) end;
        } else {
            return (int) start;
        }
    }
}
