package leetcode.com.pickup1.easy;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by tclresearchamerica on 6/8/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 * ****************************************************
 * Description:
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * Example 1:
 * Given s = "hello", return "holle".
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 * ****************************************************
 * Thought:
 * 1.按照原始的想法,就是到hashset中去寻找,根据String的indexOf可以分析出来是否存在于字符串中,
 * 然后再进行替换等的
 * ****************************************************
 * Time: 30 mins
 * Beats:60%
 * Bug: -
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No345_Reverse_Vowels_of_a_String {
    public String reverseVowels(String s) {

        String vowels = "AEIOUaeiou";
        char[] letters = s.toCharArray();


        int left = 0;
        int right = letters.length - 1;

        while (left < right) {

            if (vowels.indexOf(letters[left]) != -1) {

                while (vowels.indexOf(letters[right]) == -1) {
                    right--;
                }

                char temp = letters[left];
                letters[left] = letters[right];
                letters[right] = temp;

                right--;
                left++;

            } else {
                left++;
            }

        }

        return new String(letters);
    }


    public String reverseVowels_Slow(String s) {
        HashSet<Character> hashSet = new HashSet<>();
        initSets(hashSet);
        int start = 0, end = s.length() - 1;
        char left = 'a';
        char[] chars = s.toCharArray();
        while (start < end) {
            while (start < end) {
                if (hashSet.contains(chars[start])) {
                    left = s.charAt(start);
                    break;
                }
                start++;
            }
            while (start < end) {
                if (hashSet.contains(chars[end])) {
                    chars[start] = chars[end];
                    chars[end] = left;
                    start++;
                    end--;
                    break;
                }
                end--;
            }
        }
        return new String(chars);
    }


    private void initSets(HashSet<Character> hashSet) {
        hashSet.add('a');
        hashSet.add('e');
        hashSet.add('i');
        hashSet.add('o');
        hashSet.add('u');
        hashSet.add('A');
        hashSet.add('E');
        hashSet.add('I');
        hashSet.add('O');
        hashSet.add('U');

    }
}
