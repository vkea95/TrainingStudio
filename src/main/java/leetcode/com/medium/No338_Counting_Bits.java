package leetcode.com.medium;

/**
 * Created by tclresearchamerica on 5/12/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/counting-bits/
 * ****************************************************
 * Description:
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate
 * the number of 1's in their binary representation and return them as an array.
 * <p>
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 * <p>
 * Follow up:
 * <p>
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear
 * time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or
 * in any other language.
 * <p>
 * ****************************************************
 * Analysis:
 * 根据提示,可以用O(n)接近的方式完成,且不借助任何的内置函数,那么就需要从数字的binary本质分析了,寻找规律,可以是数学的,也可以是计算机科学的
 * 1.思路是看着2个连续的整数的位的变化,数学归纳法不管用,
 * 看本质,只要不进位,就是,在前面数字的位数上加1,一旦进位,就变成1位了,进位与否,可以通过求"&"的结果是否为零来判断
 * ****************************************************
 * 性能分析:
 * 只beat了 40%, 还要想办法提高性能
 * ****************************************************
 * ****************************************************
 */
public class No338_Counting_Bits {
    public int[] countBits(int num) {
        int[] bits = new int[num + 1];
        bits[0] = 0;
        int j = 0;
        for (int i = 1; i < num + 1; i++) {
            if ((i & (i - 1)) == 0) {
                j = 0;
                bits[i] = 1;
            } else {
                //bug1: 相&为零后,就要从从头开始累计啦
                bits[i] = bits[j] + 1;
            }
            j++;
        }
        return bits;
    }
}
