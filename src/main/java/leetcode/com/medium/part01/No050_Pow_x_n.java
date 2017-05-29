package leetcode.com.medium.part01;

/**
 * Created by jason on 2016/2/15.
 * Location:
 * https://leetcode.com/problems/powx-n/
 * ************************************************
 * Description:
 * Implement pow(x, n).
 * ************************************************
 * Solution:
 * 用到了递归，最后计算的时候同底数幂相乘，底数不变，指数相加
 */
public class No050_Pow_x_n {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        boolean isNegative = false;

        if (n < 0) {
            isNegative = true;
            n *= -1;
        }

        int k = n / 2;
        int l = n - k * 2;
        double t1 = myPow(x, k);
        double t2 = myPow(x, l);
        if (isNegative) {
            return 1 / (t1 * t1 * t2);
        } else {
            return t1 * t1 * t2;
        }

    }
}
