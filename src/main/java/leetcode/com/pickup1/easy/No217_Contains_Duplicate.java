package leetcode.com.pickup1.easy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tclresearchamerica on 6/28/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/contains-duplicate/
 * ****************************************************
 * Description:
 * Given an array of integers, find if the array contains any duplicates. Your function should return true if any
 * value appears at least twice in the array, and it should return false if every element is distinct.
 * ****************************************************
 * Thought:
 * 1.题目简单,但是时间复杂度怎么考虑呢?最简单的就是排序后再次遍历,或者就是push进HashSet,发现contain就返回,空间复杂度O(n)
 * 2
 * ****************************************************
 * Hindsight
 * 1.用hashset每次判断的方式,或是数组遍历前后比较的方法,都会导致TLE,所以现在选用的是,就遍历一次,然后算长度就好了
 * ****************************************************
 * Time: 15mins
 * Beat: 48%
 * Bug: 1 -->未进行null和length=0的判断
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No217_Contains_Duplicate {


    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        Set<Integer> hashSet = new HashSet<>();
        for (int i : nums) {
            hashSet.add(i);
        }
        if (hashSet.size() == nums.length) return false;
        return true;


    }

    public boolean containsDuplicate_TLE(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        Set<Integer> hashSet = new HashSet<>();
        for (int i : nums) {
            if (hashSet.contains(i)) return true;
            else hashSet.add(i);
        }
        return false;

    }
}
