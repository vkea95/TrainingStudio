package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 8/16/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * ****************************************************
 * Description:
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?`
 * Would this affect the run-time complexity? How and why?
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The array may contain duplicates.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No154_Find_Minimum_in_Rotated_Sorted_Array_II {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;

        for (int num : nums)
            min = Math.min(num, min);
        return min;

    }
}
