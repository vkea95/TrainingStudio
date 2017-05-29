package leetcode.com.medium.part21;

/**
 * Created by jason on 2016/4/3.
 * Location：
 * https://leetcode.com/problems/house-robber-ii/
 * *************************************************
 * Description：
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that
 * he will not get too much attention. This time, all houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain
 * the same as for those in the previous street.
 * Given a list of non-negative integers representing the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without alerting the police.
 * *************************************************
 * Solution：
 * 解法就在2个中间选一个最大的，一个是包含第一个不含最后一个，另一个是包含最后一个不含第一个
 * *************************************************
 * Hints：
 * 以下算法可以进行精简（简化成一个循环）
 */
public class No213_House_Robber_II {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        //bug1:当n=1时，后面的公式不能返回正确的值，但是元素等于2的时候没有问题
        if (n == 1) return nums[0];
        //包含第一个不含最后一个
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[1 - 1];
        for (int i = 2; i < n + 1; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);

        //包含最后一个不含第一个
        int[] dr = new int[n + 1];
        dr[0] = 0;
        dr[1] = 0;
        for (int i = 2; i < n + 1; i++)
            dr[i] = Math.max(dr[i - 1], dr[i - 2] + nums[i - 1]);

        return Math.max(dp[n - 1], dr[n]);

    }
}
