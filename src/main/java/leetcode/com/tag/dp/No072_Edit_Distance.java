package leetcode.com.tag.dp;

/**
 * Created by JianZhang on 9/12/17.
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 * (each operation is counted as 1 step.)
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 * Thoughts:
 * 1. 在字符不匹配的情形下,需要考虑的是:左,上和左上位置的最小值,但容易漏掉上位,考虑这样的case, "ab","a"-->在后面的那个列上,a[0][1]<a[0][0],
 * Bugs:
 * 1. 数组初始化的时候,需要用下标初期化,或者是前项+1,
 * 2. 字符不等的时候,要考虑的是:左,上和左上位置的最小值+1
 * [i,j]->[i-1,j-1] + updateHelper operation
 * [i,j]->[i-1,j] + delete operation
 * [i,j]->[i,j-1] + insert operation
 */
public class No072_Edit_Distance {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m + 1; i++) dp[i][0] = i;
        for (int i = 1; i < n + 1; i++) dp[0][i] = i;

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }

            }
        }
        return dp[m][n];

    }
}
