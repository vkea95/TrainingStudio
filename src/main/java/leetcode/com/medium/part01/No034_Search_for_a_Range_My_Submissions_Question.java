package leetcode.com.medium.part01;

/**
 * Created by jason on 2016/2/5.
 * Locations：
 * https://leetcode.com/problems/search-for-a-range/
 * ****************************************************
 * Descriptions:
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * *******************************************************
 * Solutions:
 * Binary search twice: one is for left boundary, the other is for right boudany
 * *******************************************************
 * Tips:
 * 算作右边界的时候，左边界应该先从start开始选取，右边界应该先从end开始选取
 * *******************************************************
 * 再次验证的时候,发现解决方案不正确,修正版的是正确的,这一版本保留是为了提醒自己哪里出现了错误
 * *******************************************************
 *
 */
public class No034_Search_for_a_Range_My_Submissions_Question {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        int start = 0;
        int end = nums.length - 1;
        int mid;
        //for left boundary
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            result[0] = start;
        } else if (nums[end] == target) {
            result[0] = end;
        } else {
            return result;
        }

        start = 0;
        end = nums.length - 1;
        //right boudnday
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            result[1] = end;
        } else if (nums[start] == target) {
            result[1] = start;
        }

        return result;
    }
}
