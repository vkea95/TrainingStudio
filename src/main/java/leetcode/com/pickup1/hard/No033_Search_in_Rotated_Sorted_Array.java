package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 9/4/16.
 * ****************************************************************
 * Location:
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * ****************************************************************
 * Description:
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2)
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * ****************************************************************
 * thought:
 * 1.写下来,画出图来,开始分析,
 * 2.2分法
 */
public class No033_Search_in_Rotated_Sorted_Array {
    public int search(int[] nums, int target) {
        if (nums == null || nums == null)	return -1;
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target)	return mid;
            if (nums[start] < nums[mid]) {
                if (nums[mid] > target && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] < target && target<=nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) {
            return start;
        }else if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}