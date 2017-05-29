package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 9/22/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/burst-balloons/
 * ****************************************************
 * Description:
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right]
 * coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * <p>
 * Example:
 * <p>
 * Given [3, 1, 5, 8]
 * <p>
 * Return 167
 * <p>
 * nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * <p>
 * ****************************************************
 * Reference:
 * https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations
 * Explanation:
 * Be Naive First
 * <p>
 * When I first get this problem, it is far from dynamic programming to me. I started with the most naive idea the
 * backtracking.
 * <p>
 * We have n balloons to burst, which mean we have n steps in the game. In the i th step we have n-i balloons to burst,
 * i = 0~n-1. Therefore we are looking at an algorithm of O(n!). Well, it is slow, probably works for n < 12 only.
 * <p>
 * Of course this is not the point to implement it. We need to identify the redundant works we did in it and try to
 * optimize.
 * <p>
 * Well, we can find that for any balloons left the maxCoins does not depends on the balloons already bursted.
 * This indicate that we can use memorization (top down) or dynamic programming (bottom up) for all the cases from
 * small numbers of balloon until n balloons. How many cases are there? For k balloons there are C(n, k) cases and
 * for each case it need to scan the k balloons to compare. The sum is quite big still. It is better than O(n!)
 * but worse than O(2^n).
 * <p>
 * Better idea
 * <p>
 * We then think can we apply the divide and conquer technique? After all there seems to be many self similar sub
 * problems from the previous analysis.
 * <p>
 * Well, the nature way to divide the problem is burst one balloon and separate the balloons into 2 sub sections one
 * on the left and one one the right. However, in this problem the left and right become adjacent and have effects
 * on the maxCoins in the future.
 * <p>
 * Then another interesting idea come up. Which is quite often seen in dp problem analysis. That is reverse thinking.
 * Like I said the coins you get for a balloon does not depend on the balloons already burst. Therefore
 * instead of divide the problem by the first balloon to burst, we divide the problem by the last balloon to burst.
 * <p>
 * Why is that? Because only the first and last balloons we are sure of their adjacent balloons before hand!
 * <p>
 * For the first we have nums[i-1]*nums[i]*nums[i+1] for the last we have nums[-1]*nums[i]*nums[n].
 * <p>
 * OK. Think about n balloons if i is the last one to burst, what now?
 * <p>
 * We can see that the balloons is again separated into 2 sections. But this time since the balloon i is the last
 * balloon of all to burst, the left and right section now has well defined boundary and do not affect each other!
 * Therefore we can do either recursive method with memoization or dp.
 * <p>
 * Final
 * <p>
 * Here comes the final solutions. Note that we put 2 balloons with 1 as boundaries and also burst all the zero
 * balloons in the first round since they won't give any coins.
 * The algorithm runs in O(n^3) which can be easily seen from the 3 loops in dp solution.
 * ****************************************************
 * Hindsight:
 * 1.看了答案也不是很明白,但是如果按照解释的内容,尝试D&C去分析的话,就比较清楚了,假设找到了这最后的元素,然后按照D&C的方式去处理。
 * 倒推,寻找合理的left和right,边界值就在那里,这个D&C的做法,理解起来有点困难,但是解决问题却很方便。
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No312_Burst_Balloons {
    public int maxCoins_dc(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;


        int[][] memo = new int[n][n];
        return burst(memo, nums, 0, n - 1);
    }

    public int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int ans = 0;
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans, nums[left] * nums[i] * nums[right]
                    + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        memo[left][right] = ans;
        return ans;
    }

    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];
        for (int k = 2; k < n; ++k)
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                for (int i = left + 1; i < right; ++i)
                    dp[left][right] = Math.max(dp[left][right],
                            nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
            }

        return dp[0][n - 1];
    }
}
