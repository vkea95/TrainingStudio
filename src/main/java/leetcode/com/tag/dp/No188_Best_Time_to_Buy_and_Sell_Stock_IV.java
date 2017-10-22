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

    //    Credit:http://www.cnblogs.com/en-heng/p/7257071.html
    public int maxProfit_2(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }
        if (k >= prices.length / 2) return quickSolve(prices);
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

    public static void main(String[] args) {
        No188_Best_Time_to_Buy_and_Sell_Stock_IV obj = new No188_Best_Time_to_Buy_and_Sell_Stock_IV();
        obj.maxProfit(2, new int[]{2, 3, 5, 1, 6, 7});
    }

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] operation = new int[k + 1][len];//基于n天股票价格的k次操作,第m次分别基于第m-1次,进行计算
        for (int i = 1; i <= k; i++) {
            //c[i,j]=max(c[i,j−1], max(c[i−1,t]−p[t])+p[j]),0≤t<j -->其中，c[i,j]表示在t时刻共i次交易产生的最大收益。
            //localMax初期值代表买了第0天的stock,预备将来卖出,当然,卖出的时候,至多是因为不合适,可以保留收益为零,而不是一定要卖的
            //后面还会对这个值进行更新,因为初期是负值,后面也都是考虑在i-1次交易的基础上再买一次第j的股票,
            // 但是要在旧tmpMa和这个操作间保留收益最大
            //localMax means the maximum profit of just doing at most i-1 transactions,
            // using at most first j-1 prices, and buying the stock at price[j] - this is used for the next loop.
            System.out.println("i: " + i);
            int localMax = -prices[0];//operate[0][0]-prices[0]
            System.out.print("localMax with operation: " + localMax + " with 0");

            for (int j = 1; j < len; j++) {
                //t[i][x] 意味着第j-1天,的第i次交易的收益,
                //gives us the maximum price when we sell at this price;
                //要么是基于前一天的第i次操作,要么就是基于当天卖出价的操作
                operation[i][j] = Math.max(operation[i][j - 1], prices[j] + localMax);
                //gives us the value when we buy at this price and leave this value for prices[j+1].
                //要么维持原来的价格位置,要么就是基于发生在第j天的第i-1次操作的收益-当天的价格
                localMax = Math.max(localMax, operation[i - 1][j] - prices[j]);
                System.out.print(", " + localMax + " with " + operation[i][j]);
            }
            System.out.println();
        }
        return operation[k][len - 1];
    }


    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}
