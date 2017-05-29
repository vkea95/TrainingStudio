package leetcode.com.medium.part01;

/**
 * Created by jason on 2016/2/5.
 * Locations:
 * https://leetcode.com/problems/search-insert-position/
 * *******************************************************
 * Descriptions:
 * Given a sorted array and a target value, return the index if the target is found. If not,
 * return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * ********************************************************
 * Solutions:
 * Binary search 变种，while循环结束的时候就已经定位出来插入元素最合适的位置了，只是还需要略加判断再决定怎么放
 * 3个可选位置：start，end，end+1
 */
public class No035_Search_Insert_Position_My_Submissions_Question {
    public int searchInsert(int[] nums, int target) {

        int start = 0;
        int end = nums.length - 1;
        int mid;
        //for left boundary
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start]>=target){
            return start;
        }else if(nums[end]>=target){
            return end;
        }else {
            return end+1;
        }
    }
}
