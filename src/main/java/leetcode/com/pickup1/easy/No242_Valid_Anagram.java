package leetcode.com.pickup1.easy;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 8/3/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/valid-anagram/
 * ****************************************************
 * Description:
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 * ****************************************************
 * Time: 10 mins
 * Beat: 63%
 * Bug: 0
 * ****************************************************
 * Hindsight:
 * 1.如果不借助Arrays.sort,如何自己手动处理呢?干脆自己写个排序处理? 建立一个26个字符的数组,然后进行计数就好了
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No242_Valid_Anagram {

    public boolean isAnagram_manual(String s, String t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        } else if ((s.length() != t.length())) {
            return false;
        }

        int[] res1 = new int[26];
        int[] res2 = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 97;
            res1[index] += 1;
            index =t.charAt(i)-97;
            res2[index] += 1;
        }

//        for (int i = 0; i < t.length(); i++) {
//            char c = t.charAt(i);
//            int index = c - 97;
//            res2[index] += 1;
//        }

        for (int i = 0; i < 26; i++) {
            if (res1[i] != res2[i]) {
                return false;
            }
        }
        return true;
    }


    public boolean isAnagram_Zj(String s, String t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        } else if ((s.length() != t.length())) {
            return false;
        }

        char[] c1 = t.toCharArray();
        char[] c2 = s.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) return false;
        }
        return true;


    }
}
