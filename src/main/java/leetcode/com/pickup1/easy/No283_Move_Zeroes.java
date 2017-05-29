package leetcode.com.pickup1.easy;

import sun.nio.cs.ext.MacHebrew;

/**
 * Created by tclresearchamerica on 8/6/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/move-zeroes/
 * ****************************************************
 * Description:
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of
 * the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * ****************************************************
 * Hindsight:
 * 首先发现0的位置,然后从该位置开始循环处理
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
 * ****************************************************
 */
public class No283_Move_Zeroes {
    public void moveZeroes(int[] nums) {
        int j = -1;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                j = Math.max(i, j);
                while (j < nums.length) {
                    if (nums[j] != 0) {
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                    j++;
                }
            }
            i++;
        }
    }
}
