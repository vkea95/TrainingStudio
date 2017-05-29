package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 10/3/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * ****************************************************
 * Description:
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * <p>
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * ****************************************************
 * Location:
 * https://segmentfault.com/a/1190000002704041
 * ****************************************************
 * Description:
 * 动态规划。维护一个boolean[][] isPalindrome二维数组，isPalindrome[i][j]表示s.substring(i, j)是否为回文串。
 * 递推公式：检查s.charAt(begin)和s.charAt(end)是否相等，如果相等就检查isPalindrome[begin + 1][end - 1]的值，
 * 也就是对一个isPalindrome[begin][end]的赋值复杂度是O(1)。另外维护一个numOfCuts数组，
 * numOfCuts[i]表示分割s.substring(1, i)最少需要几个cut，这个值需要在每次找到一个回文串的时候就相应的更新一遍。
 * <p>
 * 简单来说，就是找出所有的回文串，找的方法就是先判断当前起点和终点字符串是否相等，如果相等就进一步检查起点和终点之间的字符串是否是回文的，
 * 找到了回文串之后表示从当前起点前的位置到当前终点只需切一刀即可分割，以此与已有的分割方案进行比较即可。
 * ****************************************************
 * Hindsight:
 * 需要按照dp的方式来理解, DP就是一个元过程,不要太多的优化,只要让程序机械去做,当然还要记忆,逻辑推理没有问题,就可以了呢
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No132_Palindrome_Partitioning_II {

    public int minCut_dp(String s) {
        //boundary check
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int[] numOfCuts = new int[n + 1];
        numOfCuts[0] = -1;
        //bu1: 下标从1开始哦
        for (int i = 1; i < n + 1; i++) {
            numOfCuts[i] = Integer.MAX_VALUE;
        }
        for (int end = 0; end < n; end++) {
            for (int begin = end; begin >= 0; begin--) {
                if (s.charAt(begin) == s.charAt(end) && (end - begin < 2 || isPalindrome[begin + 1][end - 1])) {
                    isPalindrome[begin][end] = true;
                    //bug3:此处判断完毕end后,改用end+1进行处理
                    numOfCuts[end + 1] = Math.min(numOfCuts[end + 1], numOfCuts[begin] + 1);
                }
            }
        }
        //bug2:应该取最后一位
        return numOfCuts[n];
    }

    public int minCut(String s) {
        //boundary check
        if (s.length() == 0) return 0;
        int[] c = new int[s.length() + 1];
        for (int i = 0; i < s.length(); i++) c[i] = Integer.MAX_VALUE;
        c[s.length()] = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int a = i, b = i; a >= 0 && b < s.length() && s.charAt(a) == s.charAt(b); a--, b++)
                c[a] = Math.min(c[a], 1 + c[b + 1]);
            for (int a = i, b = i + 1; a >= 0 && b < s.length() && s.charAt(a) == s.charAt(b); a--, b++)
                c[a] = Math.min(c[a], 1 + c[b + 1]);
        }
        return c[0];
    }

    /*
    This can be solved by two points:

    cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
    If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
    The 2nd point reminds us of using dp (caching).
     */

    public int minCut_dp_fast(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n];
        boolean[][] pal = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }
}
