package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/1/23.
 * Locations：
 * https://leetcode.com/problems/sort-colors/
 * ********************************************
 * Descriptions：
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * *********************************************
 * Solutions：
 * 按照 lindcode.com.medium.No31_Partition_Array的方法，执行2遍即可，第一遍区分0和1,2，第二遍区分1和2
 */
public class No075_Sort_Colors {
    public void sortColors(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int k = 1;
        while (start <= end) {
            while (start <= end && nums[start] < k) {
                start++;
            }
            while (start <= end && nums[end] >= k) {
                end--;
            }
            if (start <= end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
        start = 0;
        end = nums.length - 1;
        k = 2;
        while (start <= end) {
            while (start <= end && nums[start] < k) {
                start++;
            }
            while (start <= end && nums[end] >= k) {
                end--;
            }
            if (start <= end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }

    }
}
