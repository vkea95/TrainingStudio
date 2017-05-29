package leetcode.com.hard.part1;

/**
 * Created by jason on 2016/1/19.
 * Locations:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * *****************************************************************
 * Descriptions:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * *****************************************************************
 * Solutions:
 * 数组切成2部分，分别去计算最大的获益值，然后再累计即可。可以从左到右扫一遍，算到某个点的最大获益，然后再从右到左扫描，
 * 计算，两组结果放到数组中保存，再计算累计的最大值，即为结果
 * *****************************************************************
 * Tips:
 * 1.从两侧开始扫描的时候，可将头个元素放入min/max中，然后从次个元素开始循环，这样逻辑简单
 * 2.
 */
public class No123_Best_Time_to_Buy_and_Sell_Stock_III {

    public static void main(String[] args) {
        No123_Best_Time_to_Buy_and_Sell_Stock_III No123 = new No123_Best_Time_to_Buy_and_Sell_Stock_III();
        No123.maxProfit(new int[]{2, 1, 2, 0, 1});
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int result = 0;
        int[] leftResult = new int[prices.length];
        int[] rightResult = new int[prices.length];
        // DP from left to right;
        leftResult[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            leftResult[i] = Math.max(leftResult[i - 1], prices[i] - min);
        }
        rightResult[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(prices[i], max);
            rightResult[i] = Math.max(rightResult[i + 1], max - prices[i]);

        }
        for (int i = 0; i < leftResult.length; i++)
            result = Math.max(result, (leftResult[i] + rightResult[i]));
        return result;

    }
}
