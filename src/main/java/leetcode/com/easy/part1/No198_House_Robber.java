package leetcode.com.easy.part1;

/**
 * Created by jason on 2016/4/3.
 * Location:
 * https://leetcode.com/problems/house-robber/
 * *********************************************
 * Description:
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given a indexList of non-negative integers representing the amount of money of each house, determine the maximum amount
 * of money you can rob tonight without alerting the police.
 * *******************************************************************
 * Solution:
 * http://www.programcreek.com/2014/03/leetcode-house-robber-java/
 * 1.DP: the key is to find that  the relation dp[i]=Math.max(dp[i-1],dp[i-2]+num[i-1])
 * 2.因为不能去连续两个的数组元素，所以就只有考虑奇数或偶数下标的数组元素
 * 但是需要注意的是，如果当前的奇数的和仍旧小于偶数的和，那么就该将已知的偶数的和付给奇数的和，
 * 这样下次奇数做累加的时候，其和就会更大，而且也不会违反规则
 * 例子：【2,1,1,2】
 */
public class No198_House_Robber {
    public int rob_dp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[1 - 1];
        for (int i = 2; i < n + 1; i++)
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        return dp[n];
    }

    public int rob(int[] num) {
        if (num == null || num.length == 0) return 0;
        int even = 0, odd = 0;

        for (int i = 0; i < num.length; i++) {
            if (i % 2 == 0) {
                even += num[i];
                even = even > odd ? even : odd;
            } else {
                odd += num[i];
                odd = even > odd ? even : odd;
            }
        }

        return even > odd ? even : odd;
    }
}
