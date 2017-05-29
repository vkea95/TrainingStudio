package leetcode.com.pickup1.medium;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 7/16/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/3sum-closest/
 * ****************************************************
 * Description:
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number,
 * target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * ****************************************************
 * Thoughts:
 * 1.所以这个题的核心就是3个数字的和减去目标数字之后,的绝对值最小的胜出
 * 2.如果不做预先处理(排序?2分?)的话的的话,就会TLE,
 * 3.还要考虑溢出的问题,如果设定假设的result值得花
 * Reference answer
 * 4.这个题和3Sum的做法基本一样，只要在循环内计算和target最接近的和sum，并赋值更新返回值min即可。
 * ****************************************************
 * Time: 30 min
 * Beat: 69
 * Bug:1
 * ****************************************************
 * Hindsight:
 * 1.觉得第一反应该问,interviewer是否可以排序,否则ETL
 * 2.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No016_3Sum_Closest {
    public int threeSumClosest(int[] nums, int target) {
        //robust1:
        if (nums == null || nums.length < 3) return -1;
        int diff = Integer.MAX_VALUE;
        int n = nums.length;
        int min = target;
        //bug1:ETL error
        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) return target;
                else if (sum < target)
                    left++;
                else right--;
                if (Math.abs(sum - target) < diff) {
                    min = sum;
                    diff = Math.abs(sum - target);
                }

            }
        }

        return min;
        //bug1:ETL
//        for (int i = 0; i < n - 2; i++) {
//            for (int j = i + 1; j < n - 1; j++) {
//                for (int k = j + 1; k < n; k++) {
//                    int sum = nums[i] + nums[j] + nums[k];
//                    if (Math.abs(target - sum) < Math.abs(result-target)) {
//                        result = sum;
//                    }
//                }
//            }
//        }
    }
}
