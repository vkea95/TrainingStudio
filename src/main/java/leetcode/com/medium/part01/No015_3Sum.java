package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jason on 2016/1/30.
 * Locations:
 * https://leetcode.com/problems/3sum/
 * **************************************************
 * Descriptions:
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain dupNumber triplets.
 * For example, given array S = {-1 0 1 2 -1 -4},
 * A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 * ***************************************************
 * Solutions:
 * 因为结果并没有和数字的顺序有任何关系，所以可以先对数组进行排序，然后从该数字的左侧和数组的最右侧开始进行求和计算
 * sum<0 -> left++,sum>0 -> right--,sum==0,存储结果集，然后left++，right--,
 * 并跳过重复的数字即left和left-1，right和right+1
 */
public class No015_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        //Step 1: sort
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;//to skip dupNumber numbers; e.g [0,0,0,0]
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum=nums[i]+nums[left]+nums[right];
                if (sum==0){
                    List<Integer> triplet= new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[left]);
                    triplet.add(nums[right]);
                    result.add(triplet);
                    left++;
                    right--;
                    while (left<right &&nums[left]==nums[left-1]){
                        left++;
                    }
                    while (left<right && nums[right]==nums[right+1]){
                        right--;
                    }
                }else if (sum<0){
                    left++;
                }else {
                    right--;
                }
            }
        }

        return result;

    }
}
