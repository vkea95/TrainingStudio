package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/3/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * ****************************************************
 * Thoughts:
 * Binary Search
 * ****************************************************
 * Times: 10 mins
 * Beats :3%
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No153_Find_Minimum_in_Rotated_Sorted_Array {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        int start = 0;
        int end = length - 1;
        int mid = 0;
        while (start < end - 1) {
            mid = (start + end) / 2;
            if (nums[mid] > nums[start]) {
                if (nums[end] < nums[start]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else if (nums[mid] < nums[end]) {
                end = mid;
            }
        }

        return Math.min(nums[start], nums[end]);

    }
}
