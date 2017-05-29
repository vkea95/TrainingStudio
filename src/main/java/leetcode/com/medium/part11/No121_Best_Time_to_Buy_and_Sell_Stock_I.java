package leetcode.com.medium.part11;

/**
 * Created by jason on 2016/1/19.
 * Locations:
 * ***************************************************
 * Descriptions:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * **************************************************
 * Solutions:
 * 动态规划？
 * 换个思路：相对于某位置的价格，最大获益该是其价格减去其前方最小的价格，所以在数组循环的时候，该留存其min数值
 * 然后，做减法取得获益，并留存目前的最大获益作为备选答案
 */
public class No121_Best_Time_to_Buy_and_Sell_Stock_I {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int price : prices) {
            result = (price - min) > result ? (price - min) : result;
            min = price < min ? price : min;
        }
        return result;
    }
}
