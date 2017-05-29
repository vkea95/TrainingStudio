package leetcode.com.easy.part2;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 4/9/16.
 * Location:
 * https://leetcode.com/problems/contains-duplicate/
 * *************************************************
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 * *************************************************
 * Solution:
 */
public class No217_Contains_Duplicate {
    public static void main(String[] args) {
        No217_Contains_Duplicate obj = new No217_Contains_Duplicate();
        obj.containsDuplicate(new int[]{3,3});

    }
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length==0 ) return false;

        Arrays.sort(nums);
        for(int i =1;i<nums.length;i++){
            if(nums[i-1]==nums[i]) return true;
        }
        return false;
    }
}
