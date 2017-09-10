package leetcode.com.tag.sort;

import java.util.Arrays;

/**
 * Created by JianZhang on 9/10/17.
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * Example:
 * (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 * You may assume all input has valid answer.
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 * Credits:
 * Special thanks to @dietpepsi for adding this problem and creating all test cases.
 * thoughts:
 * 1. sort
 * 2. 寻找数组下标的中点,然后下标自中点想原点(不含0)移动,每次找到一个,就和下标乘2的元素交换,只是没有想明白如何控制O(1)空间复杂度,
 * 如何将下标影射好
 * -->这个思路可以参照 https://discuss.leetcode.com/topic/32929/o-n-o-1-after-median-virtual-indexing
 * 3. 当前版本为利用辅助空间实现
 */
public class No324_Wiggle_Sort_II {
    public void wiggleSort(int[] nums) {
        int[] t = nums;
        if (nums == null || nums.length <= 1) {
            return;
        }

        Arrays.sort(nums);
        int n = nums.length;

        int[] temp = new int[n];
        int left = (n - 1) / 2;
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                temp[i] = nums[left];
                left--;
            } else {
                temp[i] = nums[right];
                right--;
            }
        }

        System.arraycopy(temp, 0, nums, 0, n);
    }
}
