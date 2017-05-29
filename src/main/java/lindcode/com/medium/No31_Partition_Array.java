package lindcode.com.medium;

/**
 * Created by jason on 2016/1/23.
 * Locations:
 * http://www.lintcode.com/en/problem/partition-array/
 * ****************************************************
 * Descriptions:
 * Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:
 * All elements < k are moved to the left
 * All elements >= k are moved to the right
 * Return the partitioning index, i.e the first index i nums[i] >= k.
 * If nums = [3,2,2,1] and k=2, a valid answer is 1.
 * Note
 * You should do really partition in array nums instead of just counting the numbers of integers smaller than k.
 * If all elements in nums are smaller than k, then return nums.length
 * Challenge
 * Can you partition the array in-place and in O(n)?
 * ****************************************************
 * Solutions:
 * 制造两个指针，分别从头尾循环，头指针循环至value比target大，尾指针循环至value比target小，然后交换所找到的值
 * 全程保证左指针 小于等于右指针，保证两个指针重合，没有遗漏，同时交换的时候对start++，end--，保证k可以找的自己的位置，
 * 否则会有value会差1
 */
public class No31_Partition_Array {
    /**
     * @param nums: The integer array you should partition
     * @param k:    As description
     *              return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {

        int start = 0, end = nums.length - 1;
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
        return start;
    }
}
