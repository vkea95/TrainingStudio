package leetcode.com.medium.part11;

/**
 * Created by jason on 2016/1/19.
 * Locations:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * ****************************************************
 * Descriptions:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit.You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times).
 * However,you may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 * ****************************************************
 * Solutions:
 * 逢低买入，逢高卖出，获益累计，
 * 补充：
 * 只要价格差值为正即可买入卖出，然后累计即可。。。-_-!!咋就没有想到哩。。。
 * ****************************************************
 * Bugs:
 * 1.循环范围问题，
 * 2.买入价格该是非负
 */
public class No122_Best_Time_to_Buy_and_Sell_Stock_II {
    public int maxProfit(int[] prices) {

        int profits = 0;
        if (prices == null || prices.length == 0) {
            return profits;
        }

        int price = -1;
        for (int i = 0; i < prices.length - 1; i++) {//bug:数组最后一个元素不应该出现在循环中

            if (prices[i] < prices[i + 1] && price < 0) {
                price = prices[i];//buy
            } else if (prices[i] > prices[i + 1] && price >= 0) {//bug:价格需要非负，而不是只需要正数，零也是可以的
                profits += (prices[i] - price);
                price = -1;
            }
        }
        if (price >= 0 && prices[prices.length - 1] > price) {//bug:价格需要非负，而不是只需要正数，零也是可以的
            profits += (prices[prices.length - 1] - price);
        }

        return profits;
    }

    //第二种简单的做法，没有更多的判断，只是计算处理
    public int maxProfit2(int[] prices) {

        int profits = 0;
        int diff =0;
        if (prices == null || prices.length == 0) {
            return profits;
        }
        for (int i = 0; i < prices.length-1; i++) {
            diff = prices[i+1]-prices[i];
            if(diff>0){
                profits+=diff;
            }
        }

        return profits;

    }
}
