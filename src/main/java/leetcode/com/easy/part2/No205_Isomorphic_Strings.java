package leetcode.com.easy.part2;

import java.util.HashMap;

/**
 * Created by tclresearchamerica on 4/6/16.
 * Location:
 * https://leetcode.com/problems/isomorphic-strings/
 * ****************************************************
 * Description:
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * ****************************************************
 * Solution:
 * 将出现的字符放入map中,如果曾经出现过,那么比较下和他对位的字符是否变化,如果变化则返回false,
 * 如果没有出现过,就检查下和他对位的字符是否出现过,若曾经出现过,则返回false
 */
public class No205_Isomorphic_Strings {

    public static void main(String[] args) {
        No205_Isomorphic_Strings obj = new No205_Isomorphic_Strings();
        obj.isIsomorphic("aa", "ab");
    }

    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        HashMap<Character, Character> map = new HashMap<>();
        //bug2:只考虑了单向问题,没有考虑如果字符不存在于map,也要比较和他对位的那个字符是否曾经出现过
        HashMap<Character, Character> map2 = new HashMap<>();
        char[] t1 = t.toCharArray();
        char[] s1 = s.toCharArray();

        for (int i = 0; i < t1.length; i++) {
            if (map.containsKey(t1[i])) {
                //bug1: forget to get value from map
                if (s1[i] != map.get(t1[i])) return false;
            } else {
                if (map2.containsKey(s1[i])){
                    return false;
                }
                map.put(t1[i], s1[i]);
                map2.put(s1[i],t1[i]);
            }

        }

        return true;

    }
}
