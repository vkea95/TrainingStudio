package leetcode.com.hard.part0;

/**
 * Created by jason on 2016/2/4.
 * Locations:
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * ***************************************************************
 * Descriptions:
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * ****************************************************************
 * Solutions:
 * 画出图来自然就可以解决问题，当然是用二分法啦
 * ****************************************************************
 * Tips：
 * 似乎对于二分法来说，需要保证将wehile的循环条件设置成（start+1<end)，这样比较靠谱。。。
 * while循环中单独对mid进行处理，还有最后要对start和end分别处理下，也不要忘记哦
 */
public class No033_Search_in_Rotated_Sorted_Array {
    public static void main(String[] args) {
        No033_Search_in_Rotated_Sorted_Array no033 = new No033_Search_in_Rotated_Sorted_Array();
        no033.search(new int[]{5, 1, 3}, 5);
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums == null) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[start] < nums[mid]) {
                if (nums[mid] > target && target >= nums[start]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (nums[mid] < target && target<=nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }

        return -1;

    }
}
