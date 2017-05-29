package leetcode.com.medium.part11;

/**
 * Created by jason on 2016/1/18.
 * Locations:
 * https://leetcode.com/problems/single-number/
 * **********************************************
 * Descriptions:
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * **********************************************
 * Solutions:
 * 数组中有2n+1个数，找出那不重复的数字。因为不让用额外的存储空间，所以不能用HashMap等数据结构，要利用位操作运算来解决
 * 位异或( ^ )：第一个操作数的的第n位于第二个操作数的第n位 相反，那么结果的第n为也为1，否则为0
 * 位与( & )：第一个操作数的的第n位于第二个操作数的第n位如果都是1，那么结果的第n为也为1，否则为0
 */
public class No136_Single_Number {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums)
            result ^= num;
        return result;
    }
}

