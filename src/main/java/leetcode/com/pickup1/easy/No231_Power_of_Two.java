package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 6/30/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/power-of-two/
 * ****************************************************
 * Description:
 * Given an integer, write a function to determine if it is a power of two.
 * ****************************************************
 * Thought:
 * 1.发现2进制的数字,只有一个1,前后都是零,那么a和(a-1)的与操作就会是零,而其他的数字不具备这个条件,要注意1比较特殊
 * ****************************************************
 * Time: 5 mins
 * Beat: 10%
 * Bug: 0
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No231_Power_of_Two {

    public boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0 ? true : false;
    }
}
