package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/2/2.
 * Locations:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * *******************************************************************
 * Descriptions:
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * For example,
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 * *******************************************************************
 * Solutions:
 * 1.源代码中的做法，很巧妙，碰到不一样的元素就存到下一个有效的位置中
 * ********************************************************************
 * Tips:
 * 1.题目中还要求将输入数组的数值一起修正，所以源代码中的方法很巧妙
 */
public class No026_Remove_Duplicates_from_Sorted_Array {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[size]) {
                nums[++size] = nums[i];
            }

        }
        return size + 1;
    }
}
