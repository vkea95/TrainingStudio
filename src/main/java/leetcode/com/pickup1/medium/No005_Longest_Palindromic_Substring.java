package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 8/6/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/longest-palindromic-substring/
 * ****************************************************
 * Description:
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 * ****************************************************
 * Thought:
 * 1.最大长度是1000
 * 2.肯定存在唯一最长回文子串
 * 3.好像没有很好的办法啦,只有多次地进行遍历啦
 * 4.解法似乎没有那么的简单,还是参照了第一遍的答案进行处理
 * ****************************************************
 * TIme:-
 * Beat:23%->78%
 * Bug:-
 * ****************************************************
 * Hindsight:
 * https://segmentfault.com/a/1190000002991199
 * 1.后来的网络答案,就是先确定中点,再向两边扩张,判断是否是回文
 * 中心扩散法 Spread From Center
 * `复杂度
 * 时间 O(n^2) 空间 O(1)
 * <p>
 * 思路
 * 动态规划虽然优化了时间，但也浪费了空间。实际上我们并不需要一直存储所有子字符串的回文情况，我们需要知道的只是中心对称的较小一层是否是回文。
 * 所以如果我们从小到大连续以某点为个中心的所有子字符串进行计算，就能省略这个空间。 这种解法中，外层循环遍历的是子字符串的中心点，
 * 内层循环则是从中心扩散，一旦不是回文就不再计算其他以此为中心的较大的字符串。由于中心对称有两种情况，一是奇数个字母以某个字母对称，
 * 而是偶数个字母以两个字母中间为对称，所以我们要分别计算这两种对称情况
 * 2.马拉车算法 Manacher Algorithm
 * 复杂度
 * 时间 O(n) 空间 O(n)
 * 关于时间复杂度的证明：http://www.zhihu.com/question/30226229
 * 思路
 * Manacher算法是非常经典的计算连续下标回文的算法。它利用了回文的对称性，更具体的来说，是回文内回文的对称性，来解决这个问题。
 * 参见：http://www.felix021.com/blog/read.php?2040
 * <p>
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No005_Longest_Palindromic_Substring {

    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        //bug1:i的开始值是0
        for (int i = 0; i < len - 1; i++) {
            //分别应对偶数和奇数两种可能的回文字符串
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i + 1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }
}

class Solution_Manacher {
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        // 预处理字符串，避免奇偶问题
        String str = preProcess(s);
        // idx是当前能够向右延伸的最远的回文串中心点，随着迭代而更新
        // max是当前最长回文串在总字符串中所能延伸到的最右端的位置
        // maxIdx是当前已知的最长回文串中心点
        // maxSpan是当前已知的最长回文串向左或向右能延伸的长度
        int idx = 0, max = 0;
        int maxIdx = 0;
        int maxSpan = 0;
        int[] p = new int[str.length()];
        for (int curr = 1; curr < str.length(); curr++) {
            // 找出当前下标相对于idx的对称点
            int symmetryOfCurr = 2 * idx - curr;
            // 如果当前已知延伸的最右端大于当前下标，我们可以用对称点的P值，否则记为1等待检查
            p[curr] = max > curr ? Math.min(p[symmetryOfCurr], max - curr) : 1;
            // 检查并更新当前下标为中心的回文串最远延伸的长度
            while ((curr + p[curr]) < str.length() && str.charAt(curr + p[curr]) == str.charAt(curr - p[curr])) {
                p[curr]++;
            }
            // 检查并更新当前已知能够延伸最远的回文串信息
            if (curr + p[curr] > max) {
                max = p[curr] + curr;
                idx = curr;
            }
            // 检查并更新当前已知的最长回文串信息
            if (p[curr] > maxSpan) {
                maxSpan = p[curr];
                maxIdx = curr;
            }
        }
        //去除占位符
        return s.substring((maxIdx - maxSpan) / 2, (maxSpan + maxIdx) / 2 - 1);
    }

    private String preProcess(String s) {
        // 如ABC,变为$#A#B#C#
        StringBuilder sb = new StringBuilder();
        sb.append("$");
        for (int i = 0; i < s.length(); i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString();
    }
}

class Solution_slow {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int length = s.length();
        int max = 0;
        String result = "";
        for (int i = 1; i <= 2 * length - 1; i++) {
            int count = 1;
            while (i - count >= 0 && i + count <= 2 * length && getChar(s, i - count) == getChar(s, i + count)) {
                count++;
            }
            //there will be extra count for the outbound
            count--;
            if (count > max) {
                result = s.substring((i - count) / 2, (i + count) / 2);
                max = count;
            }
        }
        return result;
    }


    private char getChar(String s, int i) {
        char result;
        if (i % 2 == 0) {
            result = '#';
        } else {
            result = s.charAt(i / 2);
        }
        return result;
    }
}