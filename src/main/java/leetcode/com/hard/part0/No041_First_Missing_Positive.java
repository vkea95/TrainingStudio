package leetcode.com.hard.part0;

/**
 * Created by jason on 2016/2/21.
 * Location:
 * https://leetcode.com/problems/first-missing-positive/
 * *****************************************************
 * Description:
 * Given an unsorted integer array, find the first missing positive integer.
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * Your algorithm should run in O(n) time and uses constant space.
 * *******************************************************
 * Solution:
 * 在这个数组中能够存放的正数，其实是和这个数组的长度有关系的，考虑将value为i的元素，放入i-1这个位置
 * 这样从地零个位置开始循环，理论上如果所有的正数都在，那么它就应该在自己的位置上，当然进行交换的过程中，
 * 还需要对交换过来的元素进行再判断和再交换，条件：A[i] > 0 && A[i]<=A.length && A[i] != (i+1)
 * 最后，如果所有位置的数值都是合适的，那么就返回长度+1
 */
public class No041_First_Missing_Positive {
    public int firstMissingPositive(int[] nums) {
        if (nums == null)
            return 1;

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != (i + 1)) {
                int tmp = nums[nums[i] - 1];
                if (tmp == nums[i]) {
                    break;
                }
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
