package leetcode.com.medium.part21;

/**
 * Created by tclresearchamerica on 4/18/16.
 * **********************************************************
 * Location:
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * **********************************************************
 * Description:
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of
 * which the sum ≥ s. If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * **********************************************************
 * Solution:
 * 两个游标操作,一个不断累加元素和直到循环完毕,或是sum超出s,如果内循环的sum一直小于s,则证明无果,直接返回
 */
public class No209_minimum_Size_Subarray_Sum {
    public int minSubArrayLen_TLE(int s, int[] nums) {
        int i, j = 0, sum = 0;
        int ans = Integer.MAX_VALUE;
        for (i = 0; i < nums.length; i++) {
            while (j < nums.length && sum < s) {
                sum += nums[j];
                j++;
            }
            if (sum >= s) {
                //bug1:i-j --> j-i
                ans = Math.min(ans, j - i);
                sum -= nums[i];
            } else {
                break;
            }

        }
        if (ans == Integer.MAX_VALUE) {
            ans = 0;
        }
        return ans;
    }
}
