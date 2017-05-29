package leetcode.com.medium.part22;

/**
 * Created by jason on 2016/1/18.
 * Locations:
 * https://leetcode.com/problems/single-number-iii/
 * *****************************************************
 * Descriptions:
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
 * exactly twice. Find the two elements that appear only once.
 * For example:
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * Note:
 * 1.The order of the result is not important. So in the above example, [5, 3] is also correct.
 * 2.Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 * *******************************************************
 * Solutions:
 * 将输入的数组分成两拨，创造出类似于SingleNumber_I的情况，然后分别处理即可。对不同的两个数进行异或处理，结果必然非零
 * 以异或结果的某不为零的位，以该为去将数组分成两组，这样就可以形成我们想要的情况了。
 * *******************************************************
 * Step:
 * 1.所有的元素做异或操作的到结果X
 * 2.X&(X-1)-->得到两个不同的数字进行异或操作的结果,去除二进制位上的最后一位
 * 3.X-X&(X-1)-->得到最后一位那个1
 * 4.按照No.3方法分别处理所有的元素,分为2组进行异或操作
 * *******************************************************
 * *******************************************************
 * *******************************************************
 */
public class No260_Single_Number_III {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }
        int lastBit = xor - (xor & (xor - 1));
        int group0 = 0;
        int group1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((lastBit & nums[i]) == 0) {
                group0 ^= nums[i];
            } else {
                group1 ^= nums[i];
            }
        }
        int[] result = new int[]{group0, group1};
        return result;


    }
}
