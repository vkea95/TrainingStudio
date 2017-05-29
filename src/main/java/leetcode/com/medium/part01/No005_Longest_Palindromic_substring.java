package leetcode.com.medium.part01;

/**
 * Created by jason on 2016/1/17.
 * Locations:
 * https://leetcode.com/problems/longest-palindromic-substring/
 * ***************************************************************
 * Descriptions:
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic
 * ***************************************************************
 * Solutions:
 * 不明白为什么要对第偶数个字符串返回’#‘
 * 假设回文字符串的长度为count，中间字符的下标为i则有 0<=i-count/2 and i+count/2<=n  同时乘以2
 * 则：i-count>=0 and i+count<=2*n,又count
 * <p>
 * 方法四 传说中的Manacher算法。时间复杂度O(N)
 * <p>
 * 这个算法有一个很巧妙的地方，它把奇数的回文串和偶数的回文串统一起来考虑了。这一点一直是在做回文串问题中时比较烦的地方。
 * 这个算法还有一个很好的地方就是充分利用了字符匹配的特殊性，避免了大量不必要的重复匹配。
 * <p>
 * 算法大致过程是这样。先在每两个相邻字符中间插入一个分隔符，当然这个分隔符要在原串中没有出现过。一般可以用‘#’分隔。
 * 这样就非常巧妙的将奇数长度回文串与偶数长度回文串统一起来考虑了（见下面的一个例子，回文串长度全为奇数了），
 * 然后用一个辅助数组P记录以每个字符为中心的最长回文串的信息。P［parent］记录的是以字符str［parent］为中心的最长回文串，
 * 当以str［parent］为第一个字符，这个最长回文串向右延伸了P［parent］个字符。
 * 原串：    w aa bwsw f d
 * 新串:           #  w  # a # a #  b  # w # s # w #  f  # d #
 * 辅助数组P： 1  2  1 2 3 2 1  2  1  2 1 4 1 2 1  2 1 2 1
 * 这里有一个很好的性质，P［id］-1就是该回文子串在原串中的长度（包括‘#’）。如果这里不是特别清楚，可以自己拿出纸来画一画，
 * 自己体会体会。当然这里可能每个人写法不尽相同，不过我想大致思路应该是一样的吧。
 * <p>
 * 现在的关键问题就在于怎么在O（n）时间复杂度内求出P数组了。只要把这个P数组求出来，最长回文子串就可以直接扫一遍得出来了。
 * ********************************************************************************8
 * Tips:
 * 这个答案有几个注意点：
 * 1. i+count的范围应该是小于等2倍的字符串长度，否则答案有问题
 * 2. 第一次while循环结束后，要进行count--操作
 * 3. 没有想明白abba这样的字符串是怎么搞定的，估计还是用到了传说中的Manacher算法
 * 因为i+count和i-count是偶数是没有意义的，因为除2的操作会把奇偶数做同样处理
 */
public class No005_Longest_Palindromic_substring {

    public static void main(String[] args) {
        No005_Longest_Palindromic_substring No005 = new No005_Longest_Palindromic_substring();
        No005.longestPalindrome("cABBAcd");
    }

    public String longestPalindrome(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }

        int length = s.length();
        int max = 0;
        String result = "";
        for (int i = 1; i <= 2 * length - 1; i++) {
            System.out.println("New try @" + i + "  ..........................");
            int count = 1;
            while (i - count >= 0 && i + count <= 2 * length && get(s, i - count) == get(s, i + count)) {
                count++;
            }
            count--;//there will be extra count for the outbound #
            if (count > max) {
                result = s.substring((i - count) / 2, (i + count) / 2);
                max = count;
            }
        }
        return result;
    }

    private char get(String s, int i) {
        char result;
        if (i % 2 == 0) {
            result = '#';
        } else {
            result = s.charAt(i / 2);
        }
        System.out.println("char: " + result + " i:" + i + " , i / 2: " + i / 2);
        return result;

    }
}
