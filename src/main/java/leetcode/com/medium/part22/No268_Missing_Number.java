package leetcode.com.medium.part22;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 5/2/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/missing-number/
 * *******************************************
 * Description:
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * *******************************************
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space
 * complexity?
 * *******************************************
 * Analysis:
 * 1.排序,要求下标和value匹配,否则break返回
 */
public class No268_Missing_Number {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length && i == nums[i]) {
            i++;
        }
        return i;

    }
}
