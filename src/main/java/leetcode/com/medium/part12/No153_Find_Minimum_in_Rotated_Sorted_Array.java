package leetcode.com.medium.part12;

/**
 * Created by jason on 2016/3/29.
 * Location:
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 * ************************************************************************
 * Description:
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no dupNumber exists in the array.
 * *************************************************************************
 * Solution:
 * 1.用二分法找到第一个比末尾元素的value，他就是答案
 */
public class No153_Find_Minimum_in_Rotated_Sorted_Array {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int start = 0, end = nums.length - 1;
        int target = nums[end];

        //bug1:forget the condition: start+1<end
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            //bug2: don't the judgement condition
            if (nums[mid] <= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return nums[start] <= nums[end] ? nums[start] : nums[end];

    }
}
