package leetcode.com.easy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by tclresearchamerica on 5/13/16.
 * Location:
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 * ****************************************************
 * Description:
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * <p>
 * Example 1:
 * Given s = "hello", return "holle".
 * <p>
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 * ****************************************************
 * Analysis:
 * 1.没有思路,可以想到的是从两侧进行接近,发现一对替换一对
 * 2.可以将vowels的东东放到数组中进行处理,替换使用即可.
 * ****************************************************
 * 技术弱点:
 * 1.chars[] 转String对象: new String(chars)
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No345_Reverse_Vowels_of_a_String {
    public static void main(String[] args) {
        No345_Reverse_Vowels_of_a_String obj =new No345_Reverse_Vowels_of_a_String();
        obj.reverseVowels("hello");
    }
    public String reverseVowels(String s) {
        //HashSet 的定位功能会更有效率
        HashSet<Character> hashSet = new HashSet<>(Arrays.asList('A','E','I','O','U', 'a', 'e', 'i', 'o', 'u'));
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !hashSet.contains(chars[left])) {
                left++;
            }
            while (left < right && !hashSet.contains(chars[right])) {
                right--;
            }
            if (left < right) {
                char tmp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = tmp;

            }
        }
        return new String(chars);
    }
}
