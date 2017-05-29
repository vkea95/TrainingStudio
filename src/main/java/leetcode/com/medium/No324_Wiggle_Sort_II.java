package leetcode.com.medium;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 5/10/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/wiggle-sort-ii/
 * ****************************************************
 * Description:
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * <p>
 * Note:
 * You may assume all input has valid answer.
 * <p>
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 * ****************************************************
 * Analysis:
 * 1.先升序排序,取index的中间值mid,然后从mid+1开始,从第二位进行隔位插入,如果是奇数个的话,最后2个数字互换位置,
 * 看下,这个这个算法是否可以满足follow up的要求?貌似可以,每次插入的时候都是换位,这个方法处理完全不相等的数是没问题的,
 * 一旦数字有相等的问题,就会不好用了
 * 2.排序后,找到中位数的开始和结束index,
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No324_Wiggle_Sort_II {
    public void wiggleSort_wrong(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        Arrays.sort(nums);
        int mid = nums.length / 2;
        int midStart,midEnd;
        int tmp = 0;
        for (int i = mid; i >= 0; i--) {
            if (nums[i]!=nums[mid])
                break;
            midStart=i;
        }
        for (int i = mid; i >= 0; i--) {
            if (nums[i]!=nums[mid])
                break;
            midStart=i;
        }


        for (int i = mid, j = 1; i < nums.length && j < nums.length; i++) {
            tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
            if (j + 1 < nums.length) {
                if (nums[j + 1] <= nums[j]) {
                    tmp = nums[j + 1];
                    nums[j + 1] = nums[i];
                    nums[j + 1] = tmp;
                }
            }

            j += 2;
        }
    }
}
