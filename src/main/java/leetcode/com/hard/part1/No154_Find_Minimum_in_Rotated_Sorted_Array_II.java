package leetcode.com.hard.part1;

/**
 * Created by jason on 2016/3/29.
 * Location:
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * **********************************************************************
 * Description:
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The array may contain duplicates.
 * **************************************************************************
 * Solution:
 * 如果含有重复元素的话，那么就不可以用二分法，必须线性扫描
 */
public class No154_Find_Minimum_in_Rotated_Sorted_Array_II {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;

        for (int num : nums)
            min = Math.min(num, min);
        return min;
    }
}
