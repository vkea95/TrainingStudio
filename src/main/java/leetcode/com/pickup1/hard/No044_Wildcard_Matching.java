package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 7/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/wildcard-matching/
 * ****************************************************
 * Description:
 * Implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * ****************************************************
 * Thoughts:
 * 1.带通配符的问题,做起来就略显复杂了,先搁置,有时间的话,和朋友讨论下
 * ****************************************************
 * Time:-
 * Beat: 70%
 * Bug: -
 * ****************************************************
 * Hindsight:
 * https://segmentfault.com/a/1190000003786247
 * 复杂度
 * 时间 O(N) 空间 O(1)
 * 思路
 * 假设我们用两个指针分别指向s和p字符串中要匹配的位置，首先分析下通配符匹配过程中会有哪些情况是成功：
 * 1.s的字符和p的字符相等
 * 2.p中的字符是?，这时无论s的字符是什么都可以匹配一个
 * 3.p中遇到了一个*，这时无论s的字符是什么都没关系
 * 4.之前的都不符合，但是p在之前的位置有一个*，我们可以从上一个*后面开始匹配
 * 5.s已经匹配完，但是p后面还有很多连续的`*
 * 这里1和2的情况比较好处理，关键在于如何处理3和4的情况。当我们遇到一个*时，因为之后可能要退回至该位置重新匹配，我们要将它的下标记录下来，
 * 比如idxstar。但是，当我们连续遇到两次4的情况，如何保证我还是能继续匹配s，而不是每次都退回idxstar+1导致循环呢？
 * 所以我们还要记录一个idxmatch，用来记录用上一个*连续匹配到的s中的下标。最后，对于情况5，我们用一个循环跳过末尾的*跳过就行了。
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No044_Wildcard_Matching {

    public static void main(String[] args) {
        No044_Wildcard_Matching obj = new No044_Wildcard_Matching();
        String s = "aaaabbbbcc";
        String p = "a*b*";
        obj.isMatch(s, p);
    }


    public boolean isMatch(String s, String p) {
        int idxS = 0, idxP = 0, idxStar = -1, idxMatch = 0;
        while (idxS < s.length()) {
            // 当两个指针指向完全相同的字符时，或者p中遇到的是?时
            if (idxP < p.length() && (s.charAt(idxS) == p.charAt(idxP) || p.charAt(idxP) == '?')) {
                idxP++;
                idxS++;
                // 如果字符不同也没有?，但在p中遇到是*时，我们记录下*的位置，但不改变s的指针
            } else if (idxP < p.length() && p.charAt(idxP) == '*') {
                //Explain 1:在这个匹配到*的时候,子串s的坐标并没有移动,只是增加了p的坐标而已
                //如果发生不匹配问题的话,下一个分支会对s的坐标进行+1处理的
                idxStar = idxP;
                idxP++;
                //遇到*后，我们用idxmatch来记录*匹配到的s字符串的位置，和不用*匹配到的s字符串位置相区分
                idxMatch = idxS;
                // 如果字符不同也没有?，p指向的也不是*，但之前已经遇到*的话，我们可以从idxmatch继续匹配任意字符
            } else if (idxStar != -1) {
                // 用上一个*来匹配，那我们p的指针也应该退回至上一个*的后面
                idxP = idxStar + 1;
                // 用*匹配到的位置递增
                idxMatch++;
                idxS = idxMatch;
            } else {
                return false;
            }
        }

        while (idxP < p.length() && p.charAt(idxP) == '*') {
            idxP++;
        }
        return idxP == p.length();
    }

}


class Solution_slow {

    public boolean isMatch(String s, String p) {


        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
        match[s.length()][p.length()] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) != '*')
                break;
            else
                match[s.length()][i] = true;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
                    match[i][j] = match[i + 1][j + 1];
                else if (p.charAt(j) == '*')
                    match[i][j] = match[i + 1][j] || match[i][j + 1];
                else
                    match[i][j] = false;
            }
        }
        return match[0][0];
    }


}