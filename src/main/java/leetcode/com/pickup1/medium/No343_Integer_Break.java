package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 10/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/integer-break/
 * ****************************************************
 * Description:
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize
 * the product of those integers. Return the maximum product you can get.
 * <p>
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * ****************************************************
 * Thought:
 * 分解整数,然后取最大乘积
 * ****************************************************
 * Ref: https://segmentfault.com/a/1190000005763103
 * DP
 * 复杂度
 * O(N) 时间 O(N) 空间
 * <p>
 * 思路
 * dp[i]表示i这个数字拆分后的最大乘积，最后的答案是dp[n].
 * 初始化:
 * dp[0] = 0 //这个无所谓
 * dp[1] = 1 //得是1，方便后面乘法
 * dp[2] = 1,
 * dp[i] = Max(dp[k] * dp[i - k], k * (i - k), dp[k] * (i - k), k * (dp[i - k])), k取值从[1, i / 2]闭区间
 * ****************************************************
 * Ref:https://segmentfault.com/a/1190000005593891
 * You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
 * 此法略快
 * Hindsight:
 * 此时dp里面的值需要单独进行设置咯,
 * ****************************************************
 * dp的方程有的时候,是和前几个元素有关,有的时候是和前一个元素有关,这个没有定论的
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No343_Integer_Break {
    public static void main(String[] args) {
        No343_Integer_Break obj = new No343_Integer_Break();
        obj.integerBreak(4);
    }
    public int integerBreak(int n) {
        if (n < 4) return n - 1;
        int[] dp = new int[n + 1];
        dp[1] = 0;
        //bug1:2->dp[2] 3->dp[3]
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(3 * dp[i - 3], 2 * dp[i - 2]);
        }
        return dp[n];
    }
}
