package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/1/23.
 * Locations：
 * http://www.lintcode.com/en/problem/maximum-subarray-difference/
 * ***************************************************************
 * Descriptions:
 * Given an array with integers.
 * Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)| is the largest.
 * Return the largest difference.
 * Example
 * For [1, 2, -3, 1], return 6.
 * Note
 * The subarray should contain at least one number
 * Challenge
 * O(n) time and O(n) space.
 * ******************************************************************
 * Solutions:
 * 左侧最大减右侧最小，然后右侧最大减左侧最小，比较之后取最大值
 */
public class No053_Maximum_Subarray_Difference {

    public static void main(String[] args) {
        No053_Maximum_Subarray_Difference No053 = new No053_Maximum_Subarray_Difference();
        No053.maxDiffSubArrays(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0});
    }

    /**
     * @param nums: A indexList of integers
     * @return: An integer indicate the value of maximum difference between two
     * Subarrays
     */
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;
        int length = nums.length;
        int[] leftMax = new int[length];
        int[] rightMin = new int[length];
        int[] leftMin = new int[length];
        int[] rightMax = new int[length];
//        int[] leftMax = new int[length];
//        int[] rightMax = new int[length];
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            leftMax[i] = max;
        }

        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for (int i = 0; i < length; i++) {
            sum += -1 * nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            leftMin[i] = -1 * max;
        }
        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for (int i = length - 1; i >= 0; i--) {
            sum += -1 * nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            rightMin[i] = -1 * max;
        }
        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for (int i = length - 1; i >= 0; i--) {
            sum += nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            rightMax[i] =  max;
        }
        max = Integer.MIN_VALUE;
        for (int i = 0; i < length - 1; i++) {
            max = Math.max(max, leftMax[i] - rightMin[i + 1]);
            max = Math.max(max, rightMax[i + 1] - leftMin[i]);
        }

        return max;
    }
}
