package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/12/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/powx-n/
 * ****************************************************
 * Description:
 * Implement pow(x, n).
 * ****************************************************
 * Translation:
 * 求x的n次幂
 * ****************************************************
 * Thoughts:
 * 1.和最初做这道题的想法一样,不知道要怎样处理,不就是一道不断循环的问题嘛!简单的方案导致TLE了
 * 2.要算幂值的话,必须相乘,但是如果能避免TLE呢?看了答案,发现用的是二分法.为什么会快呢?
 * 这个要和logN有关系吧,测试表明 二分法的方式计算幂循环次数减半
 * ****************************************************
 * Time: 40 mins
 * Beat: 30%
 * Bug: 2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No050_Pow_x_n {

    private int count;

    public static void main(String[] args) {
        No050_Pow_x_n obj = new No050_Pow_x_n();
        obj.count = 0;
        obj.myPow(1.0d, 10);
        System.out.println("Good solution count: " + obj.count);
        obj.count = 0;
        obj.myPow_TLE(1.0d, 10);
        System.out.println("Good solution count: " + obj.count);
        int min = Integer.MIN_VALUE;
        System.out.println("min:" + min);
        System.out.println("min+1:" + (min + 1));
        System.out.println("-1* (min+1):" + (-1*(min + 1)));
        System.out.println("-min:" + (-1 * min));
    }

    double myPow(double x, int n) {
        count++;

        //Bug:1 / x * myPow(1 / x, -(n + 1))  -->用来处理n为MIN_VALUE的情况 -1乘以Integer.MIN_VALUE还是原来那个值
        if (n < 0) return 1 / x * myPow(1 / x, -(n + 1));
        if (n == 0) return 1;
        if (n == 2) return x * x;
        if (n % 2 == 0) return myPow(myPow(x, n / 2), 2);
        else return x * myPow(myPow(x, n / 2), 2);
    }

    public double myPow_TLE(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        double result = 1;
        for (int i = 0; i < n; i++) {
            count++;
            result *= x;
        }
        return result;
    }
}
