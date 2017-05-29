package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/28/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/single-number/
 * ****************************************************
 * Description:
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * ****************************************************
 * Thoughts:
 * 1.异或处理后相同的2个数就会变成0了,但是0异或任何一个数,还是那个数
 * Time: 5 mins
 * Beat: 30%
 * Bug: 0
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No136_Single_Number {
    public int singleNumber(int[] nums) {
        int rst = 0;
        for (int i : nums) {
            rst ^= i;
        }
        return rst;
    }
}
