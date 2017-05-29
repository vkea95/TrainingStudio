package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/30/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/counting-bits/
 * ****************************************************
 * Description:
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in
 * their binary representation and return them as an array.
 * <p>
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 * <p>
 * Follow up:
 * <p>
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time
 * O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any
 * other language.
 * ****************************************************
 * Thought:
 * 1.按照数字由小到大,根据二进制状态,将bit为1的数量,推算出来了.可是没有找到规律,用时15 mins
 * Time: 20mins无解
 * Beat: 83%
 * Bug: 1
 * ****************************************************
 * Hindsight:
 * 1. 这是个数学归纳法问题,但是没有想到的是,这里还会牵扯到迭代和递归,关键是找到入口点这个方法,自己不是很熟悉,这个和算排列的那个问题类似.
 * 这样的寻找方法,自己不适应,不喜欢.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No338_Counting_Bits {
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        //冗余代码
//        if (num == 0) {
//            result[0] = 0;
//        }
        int j = 1;
        for (int i = 1; i <= num; i++) {
            if ((i & (i - 1)) == 0) {
                result[i] = 1;
                j = 1;
            } else {
                //bug1:j->j++
                result[i] = result[j++] + 1;
            }
        }

        return result;

    }
}
