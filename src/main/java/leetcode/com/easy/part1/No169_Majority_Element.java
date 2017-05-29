package leetcode.com.easy.part1;

/**
 * Created by jason on 2016/1/19.
 * Locations:
 * ***************************************************
 * Descriptions:
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * ***************************************************
 * Solutions:
 * O(n)的时间，即只遍历一次，O(1)的空间
 * 两个变量，一个存数字，个存该数字出现的次数，
 */
public class No169_Majority_Element {
    public int majorityElement(int[] nums) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (result[0] == nums[i]) result[1]++;
            else if (result[1] > 0) result[1]--;
            else {
                result[0] = nums[i];
                result[1]++;
            }
        }
        return result[0];
    }
}
