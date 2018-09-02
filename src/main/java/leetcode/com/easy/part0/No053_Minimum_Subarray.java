package leetcode.com.easy.part0;

import java.util.ArrayList;

/**
 * Created by jason on 2016/1/23.
 * Locations:
 * http://www.lintcode.com/en/problem/minimum-subarray/
 * **********************************************************
 * Descriptions:
 * Given an array of integers, find the subarray with smallest sum.
 * Return the sum of the subarray.
 * Example
 * For [1, -1, -2, 1], return -3
 * <p>
 * Note
 * The subarray should contain at least one integer.
 * ************************************************************
 * Solutions:
 * 对数字进行求反操作，再按照maximum subarray的思路做
 * maximum,求和操作然后再减去一个最小的和即可。
 */
public class No053_Minimum_Subarray {
    /**
     * @param nums: a indexList of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(ArrayList<Integer> nums) {
        // write your code
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += (nums.get(i) * -1);
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }
        return max * -1;
    }
}
