package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/12/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * ****************************************************
 * Description:
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new
 * length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't
 * matter what you leave beyond the new length
 * ****************************************************
 * Thoughts:
 * 完全没有思路,看了别人的答案,才知道如何搞
 * Time:25mins
 * Beat: 50%
 * Bug:1
 * ****************************************************
 * Hindsight;
 * 将与前一元素不同的元素,按顺序排列,并返回
 * ****************************************************
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
public class No026_Remove_Duplicates_from_Sorted_Array {
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int id = 1;
        for (int i = 1; i < nums.length; ++i)
            if (nums[i] != nums[i - 1]) nums[id++] = nums[i];
        return id;
    }
}
