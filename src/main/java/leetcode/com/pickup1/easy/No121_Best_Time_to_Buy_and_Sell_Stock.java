package leetcode.com.pickup1.easy;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 6/9/16.
 * ****************************************************
 * Location:
 * ****************************************************
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * ****************************************************
 * Time:20 mins
 * Beats: 50%(optmization 后)
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No121_Best_Time_to_Buy_and_Sell_Stock {
    public int maxProfit_for_version(int[] prices) {
        if (prices == null) return 0;

        //bug1:maxProfit最小值该是0,而非integer的最小值
        int maxProfit = 0;

        int i = 0;
        for (int j = 1; j < prices.length; j++) {
            if (prices[j] > prices[i]) {
                int result = (prices[j] - prices[i]);
                maxProfit = maxProfit > result ? maxProfit : result;
            } else {
                i = j;
            }

        }

        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        //bug1:maxProfit最小值该是0,而非integer的最小值
        int maxProfit = 0;

        int i = 0, j = 1;
        while (i < j && j < prices.length) {
            if (prices[j] > prices[i]) {
                int result = (prices[j] - prices[i]);
                maxProfit = maxProfit > result ? maxProfit : result;
                j++;
                //optimization:合并相同的处理情况
//            } else if (prices[j] < prices[i]) {
//                i = j;
//                j++;
                //optimization:合并相同的处理情况
            } else {
                i = j++;
            }
        }
        return maxProfit;
    }
}
