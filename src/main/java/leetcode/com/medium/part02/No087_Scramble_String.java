package leetcode.com.medium.part02;

import java.util.Arrays;

/**
 * Created by jason on 2016/3/11.
 * Location:
 * https://leetcode.com/problems/scramble-string/
 * ************************************************
 * Description:
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * Below is one possible representation of s1 = "great":
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * We say that "rgeat" is a scrambled string of "great".
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
 * We say that "rgtae" is a scrambled string of "great".
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * ****************************************************
 * Solution:
 */
public class No087_Scramble_String {

    public static void main(String[] args) {
        No087_Scramble_String no087 = new No087_Scramble_String();
        System.out.println(no087.isScramble("sqksrqzhhmfmlmqvlbnaqcmebbkqfy",
                "abbkyfqemcqnblvqmlmfmhhzqrskqs"));
    }

    public boolean isScramble(String s1, String s2) {
        // Write your code here
        if (s1.length() != s2.length()) {
            return false;
        }

        if (s1.length() == 0 || s1.equals(s2)) {
            return true;
        }

        if (!isValid(s1, s2)) {
            return false;
        }// Base Cases


        int len = s1.length();
        for (int i = 1; i < len; i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, len);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, len);
            String s23 = s2.substring(0, len - i);
            String s24 = s2.substring(len - i, len);

            if (isScramble(s11, s21) && isScramble(s12, s22)) return true;
            if (isScramble(s11, s24) && isScramble(s12, s23)) return true;

        }
        return false;
    }


    private boolean isValid(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);

        return (new String(c1)).equals(new String(c2));
    }


}
