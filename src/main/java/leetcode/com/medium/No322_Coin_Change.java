package leetcode.com.medium;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by tclresearchamerica on 5/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/coin-change/
 * ****************************************************
 * Description:
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 * <p>
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 * <p>
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 * ****************************************************
 * Analysis:
 * 1.想着用整除的方法拼凑起来,结果发现不对啊!数字需要动态的去凑才可以
 * 2.恍然大悟:其实就是解多项式,然后求系数和最小的数学问题
 * 思路:每个coin的系数都有个最大值,我就从最大面值的coin的最大系数,开始累加算,第一个出现的就该是答案啦
 * 3.然而,按照No.2的思路,会发生超时问题,看了网络答案,发现还是回到DP才能解决问题.
 * ****************************************************
 * 难点分析:
 * 1.首先,建立了错误的解题思路,以为上面No.2的解法就是正确的.发现错误之后,就找不到解题的思路了,根本没有意识到可以用DP来解决问题.
 * 2.其次,DP的核心就是要动态的计算amount以内的数,进行动态规划...
 * ****************************************************
 * 结论:
 * 1.不要想当然的认为思路正确,要尽力证明思路正确.
 * 2.需要培养DP的直觉
 */
public class No322_Coin_Change {

    public static void main(String[] args) {
        No322_Coin_Change obj = new No322_Coin_Change();
        int[] coins = {186, 419, 83, 408};
        System.out.println("result:" + obj.coinChange(coins, 6249));
    }


    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (amount <= 0) return 0;

        int[] dp = new int[amount + 1];

        Arrays.sort(coins);
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    //取较小值,
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }


        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public int coinChange_TLE(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (amount == 0) return 0;

        Arrays.sort(coins);


        return DFS(coins.length - 1, coins, amount);
    }

    public int DFS(int index, int[] coins, int amount) {
        if (index >= coins.length || index < 0)
            return -1;
        int n = amount / coins[index];
        int module = amount % coins[index];
        if (module == 0) {
            System.out.println("coin: " + coins[index] + ":  " + n);
            return n;
        }
        int min = Integer.MAX_VALUE;
        for (int i = n; i >= 0; i--) {
            int value = DFS(index - 1, coins, amount - coins[index] * i);
            if (value != -1) {
                System.out.println("coin: " + coins[index] + ":  " + i);
                min = Math.min(min, i + value);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int coinChange_Bug1(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (amount == 0) return 0;
        Arrays.sort(coins);
        int result = 0;
        //bug1:coins.length --> coins.length-1
        for (int i = coins.length - 1; i >= 0; i--) {
            int count = amount / coins[i];
            if (count == 0) continue;
            if (count > 0) {
                result += count;
                amount = amount % coins[i];
                if (amount == 0) break;
            }
        }

        return amount == 0 ? result : -1;
    }
}
