package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/19/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * **************************************************************
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie,
 * buy one and sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 * **************************************************************
 * Thoughts:
 * 1.刚做完第一遍没多久,感觉好像还是难懂哦。不过可以试着解下
 * **************************************************************
 * Ref:https://segmentfault.com/a/1190000004193861
 * 因为当前日期买卖股票会受到之前日期买卖股票行为的影响，首先考虑到用DP解决。
 * <p>
 * 这道题比较麻烦的是有个cooldown的限制，其实本质也就是买与卖之间的限制。对于某一天，股票有三种状态: buy, sell, cooldown,
 * sell与cooldown我们可以合并成一种状态，因为手里最终都没股票，最终需要的结果是sell，即手里股票卖了获得最大利润。
 * 所以我们可以用两个DP数组分别记录当前持股跟未持股的状态。然后根据题目中的限制条件，理清两个DP数组的表达式。
 * <p>
 * 对于当天最终未持股的状态，最终最大利润有两种可能，一是今天没动作跟昨天未持股状态一样，二是昨天持股了，今天卖了。
 * 所以我们只要取这两者之间最大值即可，表达式如下：
 * sellDp[i] = Math.max(sellDp[i - 1], buyDp[i - 1] + prices[i]);
 * <p>
 * 对于当天最终持股的状态，最终最大利润有两种可能，一是今天没动作跟昨天持股状态一样，二是前天还没持股，今天买了股票，
 * 这里是因为cooldown的原因，所以今天买股要追溯到前天的状态。我们只要取这两者之间最大值即可，表达式如下：
 * buyDp[i] = Math.max(buyDp[i - 1], sellDp[i - 2] - prices[i]);
 * 最终我们要求的结果是:
 * sellDp[n - 1] 表示最后一天结束时手里没股票时的累积最大利润
 * 当然，这里空间复杂度是可以降到O(1)的，具体见第二种代码实现。
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 0, 2};
        No309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown obj = new No309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown();
        obj.maxProfit(prices);
    }

    public int maxProfit(int[] prices) {
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

    public int maxProfit_O1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int currBuy = -prices[0];
        int currSell = 0;
        int prevSell = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp = currSell;
            currSell = Math.max(currSell, currBuy + prices[i]);
            if (i >= 2) {
                currBuy = Math.max(currBuy, prevSell - prices[i]);
            } else {
                currBuy = Math.max(currBuy, -prices[i]);
            }
            prevSell = temp;
        }
        return currSell;
    }


    public int maxProfit_On(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // 表示当天最终未持股的情况下，当天结束后的累计最大利润
        int[] sellDp = new int[prices.length];
        // 表示当天最终持股的情况下，当天结束后的累计最大利润
        int[] buyDp = new int[prices.length];

        // 考虑初始情况
        buyDp[0] = -prices[0];
        sellDp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            sellDp[i] = Math.max(sellDp[i - 1], buyDp[i - 1] + prices[i]);
            if (i >= 2) {
                buyDp[i] = Math.max(buyDp[i - 1], sellDp[i - 2] - prices[i]);
            } else {
                buyDp[i] = Math.max(buyDp[i - 1], -prices[i]);
            }
        }
        return sellDp[prices.length - 1];
    }

    public int maxProfit_leetCode_2(int[] prices) {
        int sold = 0;
        int hold = Integer.MIN_VALUE;
        int rest = 0;
        for (int price : prices) {
            int prvSold = sold;
            sold = hold + price;
            hold = Math.max(hold, rest - price);
            rest = Math.max(rest, prvSold);
        }
        return Math.max(sold, rest);
    }
}
