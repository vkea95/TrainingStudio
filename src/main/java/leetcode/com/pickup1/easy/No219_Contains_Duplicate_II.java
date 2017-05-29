package leetcode.com.pickup1.easy;

import java.util.HashSet;

/**
 * Created by tclresearchamerica on 7/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/contains-duplicate-ii/
 * ****************************************************
 * Description:
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the difference between i and j is at most k.
 * ****************************************************
 * Thoughts:
 * 1.循环比较呗,太土啦,不屑于使用
 * 2.回看第一次做的答案,发现效果一般
 * 3.再次寻找网络答案,发现可以利用下标和set的结合完成各种功能
 * My set only contain the numbers in the window. slide the window which is size k, if the new coming number
 * cannot be add to set then return true. The time complexity is O(n), space complexity is O(k).
 * ****************************************************
 * Beat: 90%
 * Time: 20
 * Bug: -
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No219_Contains_Duplicate_II {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();

        for(int i=0;i<nums.length && i<=k;i++){
            if(!set.add(nums[i])){
                return true;
            }
        }

        for(int i=k+1;i<nums.length;i++){
            set.remove(nums[i-k-1]);
            if(!set.add(nums[i])){
                return true;
            }
        }
        return false;
    }
}
