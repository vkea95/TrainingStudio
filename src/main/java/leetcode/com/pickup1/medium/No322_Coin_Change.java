package leetcode.com.pickup1.medium;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 7/21/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/coin-change/
 * ****************************************************
 * Description:
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the
 * fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by
 * any combination of the coins, return -1.
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
 * Thoughts:
 * 1.想着用amount去除最大面额的硬币,然后再寻找就可以了,但是这样的问题在于,amount的可变性,和硬币金额的不一样,
 * 导致这样的思路存在瑕疵。
 * 2.所以看了下答案发现,还是需要动态规划的,但是在如何处理余数呢?
 * 难道动态规划的方式要按照分解amount来进行?
 * 3.看了第一版的答案,意识到按照amount的size来设置dp也是没问题的,不过就是比较消耗空间的感觉
 * 那么当时的那个按照最大的来除的问题,也是可以的,不过不完全,要考虑除不完,继续递归调用较小的元素的做法,同时还要保证最大的面值不错选0个的时候
 * 也就是说,尝试在较小的面值中寻找答案的做法。
 * ****************************************************
 * Time: 30 mins
 * Beat: 87%
 * Bug:2
 * ****************************************************
 * Hindsight:
 * 1.原来以为DFS会很快,后来OJ发现根据除法法则进行的计算,会导致TLE
 * 2.还是曾经以为的按照amount进行计算速度较快。
 * 3.这个DP的解法,就是要站在当前的数值上,想着coin的方向看,如果能够看到就用最小的好了
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No322_Coin_Change {
    public static void main(String[] args) {
        No322_Coin_Change obj = new No322_Coin_Change();
        obj.coinChange(new int[]{2}, 4);
    }

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        Arrays.sort(coins);

        for (int coin : coins) {
            //bug2:i的判断条件有问题
            for (int i = 1; i <= amount; i++) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        //bug1:返回值得判断式子有问题
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];

    }

    public int coinChange_DFS_TLE(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        Arrays.sort(coins);
        int rst = DFS_helper(coins, coins.length - 1, amount);
        return rst == Integer.MAX_VALUE ? -1 : rst;
    }

    private int DFS_helper(int[] coins, int index, int amount) {

        //bug1:没有检查index是否超过区间,如果超过区间直接返回MaxValue就好了
        if (index < 0 || index >= coins.length) return Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        int rst = 0;
        int n = amount / coins[index];
        if (amount % coins[index] == 0) {
            return n;
        }

        for (int i = n; i >= 0; i--) {
            rst = DFS_helper(coins, index - 1, amount - i * coins[index]);
            //bug1:没有检查index是否超过区间,如果超过区间直接返回MaxValue就好了
            if (rst != Integer.MAX_VALUE)
                result = Math.min(result, rst + i);
        }
        return result;
    }
}
