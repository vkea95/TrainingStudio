package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/2/29.
 * Location:
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * **********************************************************************
 * Description:
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3.
 * It doesn't matter what you leave beyond the new length.
 * **********************************************************************
 * Solution:
 * 既然是允许重复的话，那么就整2个游标好了，在第2个游标判断，赋值等等
 */
public class No080_Remove_Duplicates_from_Sorted_Array_II {
    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;
        int cur = 0;

        int i, j;
        for (i = 0; i < nums.length; ) {
            int now = nums[i];
            for (j = i; j < nums.length; j++) {
                if (nums[j] != now) {
                    break;
                }
                if (j - i < 2)
                    nums[cur++] = now;
            }
            i = j;
        }
        return cur;
    }
}
