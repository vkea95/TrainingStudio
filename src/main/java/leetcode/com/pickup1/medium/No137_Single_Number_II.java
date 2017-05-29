package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 8/28/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/single-number-ii/
 * ***************************************************************
 * Description:
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * ***************************************************************
 * Thought:
 * 1.除一个数字外,所有数字都出现3次,
 * 2.出现2次的话呢,可以用异或处理来搞定。
 * 3.要求用O(n)来解决整个问题。
 * ***************************************************************
 * ***************************************************************
 * Hindsight:
 * 1.还是没有回忆出来整个问题 -_-!!
 * 2.终于明白了,因为每个数字会重复3次,那么对应每个数字额第i位,一般会有3个一组数字和它一致,那么就把这3个一组的数字,加起来,在模上3,就没了
 * 3.那个目标数字因为只有一个,而且第i位的数字只能是0或1,所以用移位的处理方式也没有问题啊
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No137_Single_Number_II {


    public int singleNumber(int[] nums) {
        int result = 0;
        int one = 0, two = 0, three = 0;
        if (nums == null || nums.length == 0) return 0;
        for (int num : nums) {
            two |= one & num;
            one ^= num;
            three |= one & two;
            one &= ~three;
            two &= ~three;

        }
        return result;
    }


    public int singleNumber_II(int[] nums) {
        int result = 0;
        if (nums == null || nums.length == 0) return 0;
        int[] bits = new int[32];
        for (int i = 0; i < bits.length; i++) {
            for (int num : nums) {
                bits[i] += num >> i & 1;
                bits[i] %= 3;
            }
            result |= (bits[i] << i);
        }
        return result;
    }
}
