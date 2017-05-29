package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 8/2/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/number-of-1-bits/
 * ****************************************************
 * Description:
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has
 * (also known as the Hamming weight).
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011,
 * so the function should return 3.
 * ****************************************************
 * Thought:
 * 1.就是这个算法该是隐藏在位操作中,就是n和n-1不断地进行&操作,然后计数
 * ****************************************************
 * Hindsight:
 * 1.想过右移操作,没成功。看来答案换成了n&(n-1)
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No191_Number_of_1_Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int rst = 0;
        while (n!=0){
            n=n&(n-1);
            rst++;
        }
        return rst;
    }
}
