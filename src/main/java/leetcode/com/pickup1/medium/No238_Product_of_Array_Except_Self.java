package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/23/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/product-of-array-except-self/
 * ****************************************************
 * Description:
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the
 * product of all the elements of nums except nums[i].
 * Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 * Follow up:
 * Could you solve it with constant space complexity? (Note: The output array does not count as extra space for
 * the purpose of space complexity analysis.)
 * ****************************************************
 * Thoughts:
 * 1.第一遍,扫描只存当前元素之前的所有元素的乘积,再逆序扫一遍,只算后面元素的乘积
 * ****************************************************
 *
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No238_Product_of_Array_Except_Self {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int temp = 1;
        result[0] = 1;

        for (int i = 1; i < result.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
//        temp = 1;
        for (int i = result.length - 2; i >= 0; i--) {
            //bug1:想着写nums【i+1】结果写成了result【i+1】
            temp *= nums[i + 1];
            result[i] = result[i] * temp;
        }
        return result;


    }
}
