package leetcode.com.hard.part0;

/**
 * Created by jason on 2016/1/26.
 * Locations:
 * https://leetcode.com/problems/regular-expression-matching/
 * http://www.jiuzhang.com/solutions/regular-expression-matching/
 * ***************************************************************
 * Description:
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 * ****************************************************************
 * Solutions:
 * ****************************************************************
 * Tips
 * 感觉九章的解答方案有bug，处理（"a",'*)时，返回结果有误
 */
public class No010_Regular_Expression_Matching {
    public static void main(String[] args) {
        No010_Regular_Expression_Matching no010 = new No010_Regular_Expression_Matching();
//        System.out.println(no010.isMatch("aab", "*"));

    }

    public boolean isMatch(String s, String p) {
        //java note: s.substring(n) will be "" if n==s.length(), but if n > s.length(), index oob error

        //you don't have to construct a state machine for this problem
        if (s.length() == 0) {
            return checkEmpty(p);
        }
        if (p.length() == 0) {
            return false;
        }

        char c1 = s.charAt(0);
        char d1 = p.charAt(0), d2 = '0';//any init value except '*' for d2 will do

        if (p.length() > 1) {
            d2 = p.charAt(1);
        }
        if (d2 == '*') {
            if (compare(c1, d1)) {
                //fork here:1.consume the character, and use the same pattern again.
                //          2.skip the character,and skip 'd1*'pattern

                //Here is also an opportunity to use DP, but the idea is the same
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            } else {
                return isMatch(s, p.substring(2));
            }
        } else {
            if (compare(c1, d1)) {
                return isMatch(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        }

    }

    public boolean compare(char c1, char d1) {
        return d1 == '.' || c1 == d1;
    }

    public boolean checkEmpty(String p) {
        if (p.length() % 2 != 0) {
            return false;
        }
        for (int i = 1; i < p.length(); i += 2) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}
