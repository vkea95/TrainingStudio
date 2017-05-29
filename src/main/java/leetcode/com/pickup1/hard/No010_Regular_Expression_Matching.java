package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 8/31/16.
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
 * reference:https://segmentfault.com/a/1190000003904286
 * 解法的核心理念是：从后往前看pattern的每一位，对于pattern的每一位，我们尽可能的把待匹配串string从后往前给匹配上。
 * 我们用一个数组match[string.length() + 1]来表示string被匹配的情况，这里如果match[j]是true，而我们pattern正指向第i位，
 * 则说明string从第j位到最后都已经被pattern第i位之前的某些部分给成功匹配了，所以我们不用再操心了。match[i]为true的条件是
 * match[i + 1]为true，且string第i个字符也能被成功匹配。
 * <p>
 * 那我们就可以从后往前开始看pattern的每一位能匹配多少string的字符了：
 * <p>
 * 如果pattern的这一位是*，那我们要用这一位，来从后往前尝试匹配string，因为string后面是已经匹配好的，前面是还没匹配好的，
 * 所以从前往后匹配星号可能会导致我们匹配了一些pattern该星号前面的星号应该匹配的部分。
 * 而从后往前匹配则不会影响pattern该星号后面星号所匹配的部分，因为已经匹配的部分我们会直接跳过。
 * 如果pattern这一位不是*，那我们则不能匹配多个字符，我们只能匹配一个字符，这时候要对string从前往后匹配，因为如果后面没被匹配，
 * 前面也肯定不会被匹配，所以从前向后能保证我们把pattern的这一位匹配到string当前最后面那个还没匹配的字符。这样如果那个字符能被匹配就通过了。
 * 我们举个例子
 * <p>
 * match:   0 0 0 1
 * string:  a a b
 * pattern: a * b
 * |
 * 这里我们先看pattern最后一位b能匹配到多少，这里因为b不是星号，所以我们从左往右尝试匹配string，第一个a不行，第二个a也不行，
 * 然后到b，这里因为match[3]是true，b也和b相同，所以匹配成功。
 * <p>
 * match:   0 0 1 1
 * string:  a a b
 * pattern: a * b
 * |
 * 然后看pattern的这个星号，我们要从后往前匹配string。因为b已经被匹配了，match[2]是true，所以直接跳过。然后到a，
 * 发现个pattern中星号前面的字符a相同，所以匹配成功，match[1]也置为true再看string的第一个a，还是可以匹配成功，
 * 这样整个string都被匹配成功了。
 * 这里还有几个情况，首先，无论刚才那pattern中最后一个b有没有匹配到string中任何一个字符，match[3]也要置为false。
 * 这样才能防止pattern最后字母没有匹配上，而pattern前面的部分反而把string的结尾给匹配了。还有如果pattern中是句号的话，那相当于字符相同。
 * ****************************************************************
 * Hindsight:
 * 1.理解了这个问题,这个*代表的不存在,或是对前面字符的无线重复,因为匹配的时候已经参考使用了前面的那个字符,所以要主动地进行一次额外的i--
 * 2.每次匹配的时候,都要从match的j+1来进行判断 再&&上字符的匹配结果
 * 3.在非*的匹配时候,要将match的最后元素置成false
 * 4.match【0】作为返回结果返回
 * ****************************************************************
 * ****************************************************************
 * ****************************************************************
 * ****************************************************************
 * ****************************************************************
 * ****************************************************************
 */
public class No010_Regular_Expression_Matching {
    public static void main(String[] args) {
        No010_Regular_Expression_Matching obj = new No010_Regular_Expression_Matching();
        System.out.print(obj.isMatch("1", "1*"));
    }

    public boolean isMatch(String s, String p) {
        boolean[] match = new boolean[s.length() + 1];
        match[s.length()] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) == '*') {
                //如果是星号,从后往前匹配
                for (int j = s.length() - 1; j >= 0; j--) {
                    match[j] = match[j] || (match[j + 1] && compare(p.charAt(i - 1), s.charAt(j)));
                }
                //要把i多减一,因为星号和前面的字符配合使用的
                i--;
            } else {
                // 如果不是星号，从前往后匹配
                for (int j = 0; j < s.length(); j++) {
                    match[j] = match[j + 1] && compare(p.charAt(i), s.charAt(j));
                }
                // 只要试过了pattern中最后一个字符，就要把match[s.length()]置为false
                match[s.length()] = false;
            }
        }
        return match[0];
    }

    private boolean compare(char p, char c) {
        return p == '.' || p == c;
    }
}
