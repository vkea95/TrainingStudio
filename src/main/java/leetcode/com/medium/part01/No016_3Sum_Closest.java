package leetcode.com.medium.part01;

import java.util.Arrays;

/**
 * Created by jason on 2016/1/30.
 * Locations:
 * https://leetcode.com/problems/3sum-closest/
 * ***********************************************
 * Descriptions:
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number,
 * target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * ***********************************************
 * Solutions:
 * 参考No015，将零换成target，然后算接近值就可以了
 */
public class No016_3Sum_Closest {
    public int threeSumClosest(int[] nums, int target) {
        int result = Integer.MAX_VALUE;
        int abs = Integer.MAX_VALUE;
        if (nums == null || nums.length < 3) {
            return result;
        }
        //Step 1: sort
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;//to skip dupNumber numbers; e.g [0,0,0,0]
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
                int tmpabs = Math.abs(target - sum);
                if (tmpabs < abs) {
                    result = sum;
                    abs = tmpabs;
                }
            }
        }

        return result;

    }
}
