package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 8/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reverse-bits/
 * ****************************************************
 * Description:Reverse bits of a given 32 bits unsigned integer.
 * <p>
 * For example, given input 43261596 (represented in binary as 0000 0010 1001 0100 0001 1110 1001 1100),
 * return 964176192 (represented in binary as 0011 1001 0111 1000 0010 1001 0100 0000).
 * <p>
 * Follow up:
 * If this function is called many times, how would you optimize it?
 * <p>
 * Related problem: Reverse Integer
 * ****************************************************
 * Time:15 mins
 * Beat: 10%->50
 * Bug: 1
 * ****************************************************
 * https://segmentfault.com/a/1190000003483740
 * 复杂度
 * 时间 O(1) 空间 O(1)
 * <p>
 * 思路
 * 最简单的做法，原数不断右移取出最低位，赋给新数的最低位后新数再不断左移。
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No190_Reverse_Bits {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++, n >>= 1) {
            result = result << 1 | (n & 1);
        }
        return result;
    }

    public int reverseBits_slow(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            //bug1:result=resutl<<1
            if (((n >> i) & 1) == 0) result = result << 1;
            else result = (result << 1) | 1;
        }
        return result;

    }
}
