package leetcode.com.tag.dp;

/**
 * Created by JianZhang on 9/24/17.
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * <p>
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * <p>
 * Credits:
 * Special thanks to @Freezen for adding this problem and creating all test cases.
 * credit:https://discuss.leetcode.com/topic/8984/a-concise-dp-solution-in-java
 */
public class No188_Best_Time_to_Buy_and_Sell_Stock_IV {

    public int maxProfit_2(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }
        if (k >= prices.length) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                res += Math.max(0, prices[i] - prices[i - 1]);
            }
            return res;
        }

        int[] local = new int[k + 1];
        int[] global = new int[k + 1];
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[k];
    }


    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            //tmpMax初期值代表买了第0天的stock,预备将来卖出,当然,卖出的时候,至多是因为不合适,可以保留收益为零,而不是一定要卖的
            //后面还会对这个值进行更新,因为初期是负值,后面也都是考虑在i-1次交易的基础上再买一次第j的股票,
            // 但是要在旧tmpMa和这个操作间保留收益最大
            //这个tmpMax也可以当做买入收益最大来理解?
            //tmpMax means the maximum profit of just doing at most i-1 transactions,
            // using at most first j-1 prices, and buying the stock at price[j] - this is used for the next loop.
            int tmpMax = -prices[0];
            for (int j = 1; j < len; j++) {
                //t[i][x] 意味着第j-1天,的第i次交易的收益,
                //gives us the maximum price when we sell at this price;
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                //gives us the value when we buy at this price and leave this value for prices[j+1].
                tmpMax = Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}
