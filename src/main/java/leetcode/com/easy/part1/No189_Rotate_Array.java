package leetcode.com.easy.part1;

/**
 * Created by tclresearchamerica on 6/15/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/rotate-array/
 * ****************************************************
 * Description:
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem
 * ****************************************************
 * Thoughts:
 * 题目很容易,但是需要一种很smart的方法,就是编程之美上面说的解法,分成2部分进行旋转,然后再一起旋转
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
public class No189_Rotate_Array {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;
        int pos = nums.length - k % nums.length;
        //bug1: pos --> pos-1
        rotate(nums, 0, pos - 1);
        rotate(nums, pos, nums.length - 1);
        rotate(nums, 0, nums.length);

    }

    private void rotate(int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        for (int i = start; i <= mid; i++) {
            int step = i - start;
            int tmp = nums[i];
            nums[i] = nums[end - step];
            nums[end - step] = tmp;
        }
    }
}
