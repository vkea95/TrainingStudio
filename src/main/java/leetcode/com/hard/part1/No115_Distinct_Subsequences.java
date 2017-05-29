package leetcode.com.hard.part1;

/**
 * Created by tcl on 2016/3/16.
 * Location:
 * https://leetcode.com/problems/distinct-subsequences/
 * *****************************************************
 * Description:
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 * ******************************************************
 * Solution:
 * http://www.cnblogs.com/yuzhangcmu/p/4196373.html
 * 遇到这种两个串的问题，很容易想到DP。但是这道题的递推关系不明显。可以先尝试做一个二维的表int[][] dp，
 * 用来记录匹配子序列的个数（以S ="rabbbit",T = "rabbit"为例）：
 * % & r a b b b i t
 * & 1 1 1 1 1 1 1 1
 * r 0 1 1 1 1 1 1 1
 * a 0 0 1 1 1 1 1 1
 * b 0 0 0 1 2 3 3 3
 * b 0 0 0 0 1 3 3 3
 * i 0 0 0 0 0 0 3 3
 * t 0 0 0 0 0 0 0 3
 * 从这个表可以看出，无论T的字符与S的字符是否匹配，dp[i][j] = dp[i][j - 1].就是说，假设S已经匹配了j - 1个字符，
 * 得到匹配个数为dp[i][j - 1].现在无论S[j]是不是和T[i]匹配，匹配的个数至少是dp[i][j - 1]。除此之外，当S[j]和T[i]相等时，
 * 我们可以让S[j]和T[i]匹配，然后让S[j - 1]和T[i - 1]去匹配。所以递推关系为：
 * dp[0][0] = 1; // T和S都是空串.
 * dp[0][1 ... S.length() - 1] = 1; // T是空串，S只有一种子序列匹配。
 * dp[1 ... T.length() - 1][0] = 0; // S是空串，T不是空串，S没有子序列匹配。
 * dp[i][j] = dp[i][j - 1] + (T[i - 1] == S[j - 1] ? dp[i - 1][j - 1] : 0).1 <= i <= T.length(), 1 <= j <= S.length()
 * ********************************************************
 * Tips:
 * 关于这样的DP问题还是要自己仔细推导才能够理解的透彻，这次的心得就是，当某个字符在两个串中匹配的时候，
 * 要去取它的左上对角线值，其意义为，当字符匹配之时，要看其前一字符的匹配情况，即往前看。这样理解似乎比较简单些啦
 * 当然，该位置的字符要至少取其与【i】【j-1】的值
 */
public class No115_Distinct_Subsequences {
    public int numDistinct(String S, String T) {
        if (S == null || S.length() == 0) return 0;
        if (T == null || T.length() == 0) return 0;

        int[][] matrix = new int[T.length() + 1][S.length() + 1];

        for (int i = 0; i <= S.length(); i++) matrix[0][i] = 1;

        for (int i = 1; i <= T.length(); i++) {
            for (int j = 1; j <= S.length(); j++) {
                matrix[i][j] = matrix[i][j - 1];
                if (S.charAt(j - 1) == T.charAt(i - 1))
                    matrix[i][j] += matrix[i - 1][j - 1];
            }
        }
        return matrix[T.length()][S.length()];


    }
}
