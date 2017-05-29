package leetcode.com.medium;

/**
 * Created by tclresearchamerica on 7/10/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * ****************************************************
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one
 * and sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 * ****************************************************
 * Thoughts:
 * 1.买卖股票的变种,就是有一天是不可用的,当你卖了股票后,所以应该是在原来股票题的基础上加工下即可
 * 2.解法是DP吗?还是啥呢?好困啊
 * 3.让我来定义下,最大获利即当前元素的之前元素的最大-最小 然后累计
 * ****************************************************
 * Reference:   -->还是要继续理解这个说明方法
 * https://discuss.leetcode.com/topic/30431/easiest-java-solution-with-explanations
 * Much simpler explanations:
 * https://discuss.leetcode.com/topic/30680/share-my-dp-solution-by-state-machine-thinking/5
 * ****************************************************
 * Time: 30 mins
 * Beat: 50%
 * Bug:-
 * ****************************************************
 * 1. Define States
 * <p>
 * To represent the decision at index i:
 * <p>
 * buy[i]: Max profit till index i. The series of transaction is ending with a buy.
 * sell[i]: Max profit till index i. The series of transaction is ending with a sell.
 * To clarify:
 * <p>
 * Till index i, the buy / sell action must happen and must be the last action. It may not happen at index i.
 * It may happen at i - 1, i - 2, ... 0.
 * In the end n - 1, return sell[n - 1]. Apparently we cannot finally end up with a buy. In that case,
 * we would rather take a rest at n - 1.
 * For special case no transaction at all, classify it as sell[i], so that in the end, we can still return sell[n - 1].
 * Thanks @alex153 @kennethliaoke @anshu2.
 * 2. Define Recursion
 * <p>
 * buy[i]: To make a decision whether to buy at i, we either take a rest, by just using the old decision at i - 1,
 * or sell at/before i - 2, then buy at i, We cannot sell at i - 1, then buy at i, because of cooldown.
 * sell[i]: To make a decision whether to sell at i, we either take a rest, by just using the old decision at i - 1,
 * or buy at/before i - 1, then sell at i.
 * So we get the following formula:
 * <p>
 * buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
 * sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
 * 3. Optimize to O(1) Space
 * <p>
 * DP solution only depending on i - 1 and i - 2 can be optimized using O(1) space.
 * <p>
 * Let b2, b1, b0 represent buy[i - 2], buy[i - 1], buy[i]
 * Let s2, s1, s0 represent sell[i - 2], sell[i - 1], sell[i]
 * Then arrays turn into Fibonacci like recursion:
 * <p>
 * b0 = Math.max(b1, s2 - prices[i]);
 * s0 = Math.max(s1, b1 + prices[i]);
 * 4. Write Code in 5 Minutes
 * <p>
 * First we define the initial states at i = 0:
 * <p>
 * We can buy. The max profit at i = 0 ending with a buy is -prices[0].
 * We cannot sell. The max profit at i = 0 ending with a sell is 0.
 * <p>
 * ****************************************************
 * buy[i]:截止第i天最大获利,traction的终了动作是买
 * sell[i]:截止第i天最大获利,traction的终了动作是买
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {

    public int maxProfit_leetCode(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int b0 = -prices[0], b1 = b0;
        int s0 = 0, s1 = 0, s2 = 0;

        for (int i = 1; i < prices.length; i++) {
            b0 = Math.max(b1, s2 - prices[i]);
            s0 = Math.max(s1, b1 + prices[i]);
            b1 = b0;
            s2 = s1;
            s1 = s0;
        }
        return s0;
    }
}
