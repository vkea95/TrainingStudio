package leetcode.com.medium.part22;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 5/2/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problemset/algorithms/
 * *******************************************
 * Description:
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
 * which sum to n.
 * <p>
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 * *******************************************
 * Analysis:
 * 想着将其数字开平方后进行处理,发现因为没有开平凡的函数,所以较难处理.
 * 按照计算机的思路该是循环计算才对,
 * 所以网络答案是:动态规划
 * 如果一个数x可以表示为一个任意数a加上一个平方数bｘb，也就是x=a+bｘb，那么能组成这个数x最少的平方数个数，
 * 就是能组成a最少的平方数个数加上1（因为b*b已经是平方数了）。
 * <p>
 * *******************************************
 * 感受:
 * 1.真难想啊!没想到是动态规划,后来也就释然了,毕竟让计算机做重复的事情比较合乎情理.
 * 2.动态规划的思维方式依然没有掌握,即使是看着答案,也会茫然...
 */
public class No279_Perfect_Squares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];

        //初期化:平方数的结果设为1,其余值设为Max_Value,防止后来被选中.
        Arrays.fill(dp, Integer.MAX_VALUE);

        //bug1:i=1  -> i=0
        for (int i = 0; i * i <= n; i++) {
            dp[i * i] = 1;
        }

        //从小到大的任意数字
        for (int i = 0; i <= n; i++) {
            // 从小到大找平方数j*j
            for (int j = 0; i + j * j <= n; j++) {
                // 因为i+j*j可能本身就是平方数，所以我们要取两个中较小的
                dp[i + j * j] = Math.min(dp[i] + 1, dp[i + j * j]);
            }
        }
        return dp[n];
    }
}
