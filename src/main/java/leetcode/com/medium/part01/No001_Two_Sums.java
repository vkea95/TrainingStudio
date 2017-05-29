package leetcode.com.medium.part01;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by jason on 2016/1/17.
 * Locations:
 * https://leetcode.com/problems/two-sum/
 * *******************************************************
 * Descriptions:
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned
 * answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * ************************************************************
 * Solutions:
 * Space: O(n) time:O(n)
 * Another space:O(1) time(oLgn) :先排序
 */
public class No001_Two_Sums {

    public static void main(String[] args) {
        No001_Two_Sums no001 = new No001_Two_Sums();
        no001.twoSum(new int[]{3, 2, 4}, 6);
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, int[]> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {

                map.get(nums[i])[1] = i + 1;

                return map.get(nums[i]);
            }

            int[] val = new int[2];
            val[0] = i + 1;
            map.put((target - nums[i]), val);

        }
        return null;

    }
    // Can’t use the sort method here, since the question asks for indexes.
    //第二种做法 排序后 时间复杂度为o(logn) 空间复杂度是O（1）
    public int[] twoSum_pointer(int[] numbers, int target) {
        if(numbers == null || numbers.length < 2) {
            return null;
        }
        Arrays.sort(numbers);
        int left = 0;
        int right = numbers.length - 1;
        int[] rst = new int[2];

        while( left < right){
            int sum = numbers[left] +  numbers[right];
            if( sum == target){
                rst[0] = left + 1;
                rst[1] = right + 1;
                break;
            }else if( sum < target){
                left++;
            }else{
                right--;
            }
        }
        return rst;
    }
}
