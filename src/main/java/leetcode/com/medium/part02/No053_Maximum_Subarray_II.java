package leetcode.com.medium.part02;

import java.util.ArrayList;

/**
 * Created by jason on 2016/1/22.
 * Locations：
 * http://www.lintcode.com/zh-cn/problem/maximum-subarray-ii/
 * ******************************************************************
 * 给定一个整数数组，找出两个不重叠子数组使得它们的和最大。
 * 每个子数组的数字在数组中的位置应该是连续的。
 * 返回最大的和。
 * 样例
 * 给出数组[1, 3, -1, 2, -1, 2]，这两个子数组分别为[1, 3]和[2, -1, 2]或者[1, 3, -1, 2]和[2]，它们的最大和都是7
 * 注意
 * 子数组最少包含一个数
 * 挑战
 * 要求时间复杂度为O(n)
 * ******************************************************************
 * Solutions：
 * 类似于买卖股票II，从左边算一边，从右边算一遍,算累计最大值的时候，要考虑的是i和i+1的累计
 */
public class No053_Maximum_Subarray_II {
    /**
     * @param nums: A indexList of integers
     * @return: An integer denotes the sum of max two non-overlapping subarrays
     */
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        // write your code
        int result = 0;
        int[] left = new int[nums.size()];
        int[] right = new int[nums.size()];

        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            left[i] = max;
        }

        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for (int i = nums.size() - 1; i >= 0; i--) {
            sum += nums.get(i);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            right[i] = max;
        }

        max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            max = Math.max(max, left[i] + right[i + 1]);
        }
        return max;
    }
}
