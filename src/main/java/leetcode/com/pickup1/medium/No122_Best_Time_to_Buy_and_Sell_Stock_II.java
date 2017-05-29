package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * ****************************************************
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one
 * and sell one share of the stock multiple times). However, you may not engage in multiple transactions
 * at the same time (ie, you must sell the stock before you buy again).
 * ****************************************************
 * Time: 20mins
 * Beats: 10%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No122_Best_Time_to_Buy_and_Sell_Stock_II {
    public int maxProfit_clean(int[] prices) {
        int maxProfit = 0;
        for (int j = 0; j < prices.length - 1; j++) {
            if (prices[j + 1] > prices[j]) {
                maxProfit += prices[j + 1] - prices[j];
            }
        }
        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int i = 0, j = 1;
        while (j < prices.length) {
            if (prices[j] > prices[i]) {
                if (j + 1 < prices.length && prices[j] < prices[j + 1]) {


                } else {
                    maxProfit += prices[j] - prices[i];
                    j++;
                    i = j;

                }
            } else {
                i = j;
            }
            j++;
        }
        //bug1: forget to return the result
        return maxProfit;
    }
}
