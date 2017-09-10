package leetcode.com.tag.sort;

/**
 * Created by JianZhang on 9/10/17.
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * Solutions:
 * 1. 直接计算、判断每个字符出现的次数是否相等就好了。
 */
public class No242_Valid_Anagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] res1 = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 97;
            res1[index] += 1;
            c = t.charAt(i);
            index = c - 97;
            res1[index] -= 1;
        }

        for (int i = 0; i < 26; i++) {
            if (res1[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
