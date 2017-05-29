package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reverse-string/
 * ****************************************************
 * Description:
 * Write a function that takes a string as input and returns the string reversed.
 * Example:
 * Given s = "hello", return "olleh".
 * ****************************************************
 * Thoughts:
 * 1.可以使用StringBuffer的reverse方法
 * 2.我用的是数组,加倒序的方法
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No344_Reverse_String {
    public String reverseString(String s) {
        if (s == null || s.length() <= 1) return s;
        int i = 0, j = s.length() - 1;
        char[] array = s.toCharArray();
        while (i < j) {
            char c = array[i];
            array[i++] = array[j];
            array[j--] = c;
        }

        return new String(array);
    }
}
