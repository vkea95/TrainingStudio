package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 6/30/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/rotate-array/
 * ****************************************************
 * Description:
 * Rotate an array of n elements to the right by k steps.
 * <p>
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * <p>
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * ****************************************************
 * Time: 8 mins
 * Beat: 10%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No189_Rotate_Array {

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return;

        int n = nums.length;
        //bug1:未对k进行模处理
        k=k%n;
        swap(nums, 0, n - k - 1);
        swap(nums, n - k, n - 1);
        swap(nums, 0, n - 1);

    }

    private void swap(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

}
