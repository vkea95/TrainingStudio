package leetcode.com.hard.part1;

/**
 * Created by jason on 2016/1/19.
 * Locations:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * ********************************************************************************
 * Descriptions:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * ********************************************************************************
 * Solutions:
 * 借鉴III的解法，答案显示可以用二维数组，模拟k次，但这样的话，并不能得出最大获益的解
 * 若K>数组长度的一半，则表明可以不断地用买入卖出来计算最大获益
 * 若K<数组长度的一半，则需要动态规划来处理
 */
public class No188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public static void main(String[] args) {
        No188_Best_Time_to_Buy_and_Sell_Stock_IV No188 = new No188_Best_Time_to_Buy_and_Sell_Stock_IV();

        No188.maxProfit(2, new int[]{1, 3, 5, 1, 1, 2});
    }

    public int maxProfit(int k, int[] prices) {
        if (k == 0) return 0;

        int profit = 0;
        int n = prices.length;
        if (k >= n / 2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }
        } else {
            int[][] mustsell = new int[n + 1][n + 1];//sell[i][j]表示前i天，至多进行j次交易，第i天必须sell的最大获益
            int[][] globalbest = new int[n + 1][n + 1];//globalbest[i][j]表示前i天，至多进行j次交易，第i天可以不sell的最大获益

            //initialization
            mustsell[0][0] = globalbest[0][0] = 0;
            for (int j = 1; j <= k; j++) {
                mustsell[0][j] = globalbest[0][j] = 0;
            }

            for (int i = 1; i < n; i++) {
                int gainOrLose = prices[i] - prices[i - 1];
                mustsell[i][0] = 0;
                for (int j = 1; j <= k; j++) {
                    mustsell[i][j] = Math.max(globalbest[(i - 1)][j - 1] + gainOrLose, mustsell[i - 1][j] + gainOrLose);
                    globalbest[i][j] = Math.max(globalbest[(i - 1)][j], mustsell[i][j]);
                }
            }
            profit = globalbest[n - 1][k];
        }

        return profit;
    }
}
