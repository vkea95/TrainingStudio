package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/20/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/sqrtx/
 * ****************************************************
 * Description:
 * Implement int sqrt(int x).
 * Compute and return the square root of x.
 * ****************************************************
 * Thoughts:
 * 求平方根应该用到极值逼近法
 * ****************************************************
 * Time: 30 mins
 * Beats: 12% -> 18%
 * Bug: 2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No069_Sqrt_x {

    public static void main(String[] args) {
        No069_Sqrt_x obj = new No069_Sqrt_x();
        obj.mySqrt(6);
    }

    public int mySqrt(int x) {

        long r = x;
        while (r * r > x)
            r = (r + x / r) / 2;
        return (int) r;
    }

    public int mySqrt_slowest(int x) {
        if (x <= 1) return x;
        long from = 1, to = x;

        while (from + 1 < to) {
            long mid = (from + to) / 2;
            //bug2: miss spell the condition
            if (mid * mid >= x) {
                from = mid;
            } else {
                to = mid;
            }
        }
        //bug1: don't knwo how to judge
        if (to * to<= x) {
            return (int) to;
        } else {
            return (int) from;
        }
    }
}
