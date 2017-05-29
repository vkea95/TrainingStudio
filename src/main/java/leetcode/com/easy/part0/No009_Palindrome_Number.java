package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/1/26.
 * Locations:
 * https://leetcode.com/problems/palindrome-number/
 * ***************************************************
 * Descriptions:
 * Determine whether an integer is a palindrome. Do this without extra space.
 * ****************************************************
 * Solutions:
 * 从个位数开始计算可以得到原来的数字
 * ****************************************************
 * Tips:
 * 负数不算是plaindrome数字
 */
public class No009_Palindrome_Number {

    public static void main(String[] args) {
        No009_Palindrome_Number no009 = new No009_Palindrome_Number();
        no009.isPalindrome(1);
    }

    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int y = 0;
        int xx = x;
        while (xx != 0) {
            y = xx % 10 + y * 10;
            xx /= 10;
        }
        if (x == y) {
            return true;
        } else {
            return false;
        }
    }


}
