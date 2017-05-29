package leetcode.com.medium.part11;

/**
 * Created by jason on 2016/1/18.
 * Locations:
 * https://leetcode.com/problems/single-number-ii/
 * ****************************************************
 * Descriptions:
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * ****************************************************
 * Solutions:
 * 让3个相同的数在一起，抵消掉。活用SingleNumber_I的思路。考虑三进制。
 * XOR：也就是不进位加法，三进制的不进位加法：
 * 210
 * 210
 * 210
 * ----
 * 000
 * 设计这样一个位数组，长度为32位，对应整数的32bit，然后从每个整数元素的第零位开始进行累加，直到第32位，累加的结果进行模3的处理
 * 将该结果存起来，拼凑成最终的结果
 */
public class No137_Single_Number_II {
    public int singleNumber(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
        int result = 0;
        int[] bits = new int[32];
        for (int i=0;i<bits.length;i++){
            for(int num:nums){
                bits[i]+=num>>i &1;
                bits[i]%=3;
            }
            result |=(bits[i]<<i);
        }
        return result;
    }
}
