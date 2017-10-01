package leetcode.com.tag.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JianZhang on 10/1/17.
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
 * which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 * Solutions:
 * 1. DP
 * //   dp[i]代表组成i的最少平方数的个数。至于状态转移，我们可以这样想：组成这个数的最小值要么就是本身，
 * // 要么就是前面某一个数+一个平方数（所以看作值加上1）,类似0-1背包的思想。
 */
public class No279_Perfect_Squares {


    //Credit: http://www.cnblogs.com/njczy2010/p/5466263.html
//   dp[i]代表组成i的最少平方数的个数。至于状态转移，我们可以这样想：组成这个数的最小值要么就是本身，
// 要么就是前面某一个数+一个平方数（所以看作值加上1）,类似0-1背包的思想。
    public int numSquares(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

}
