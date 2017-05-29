package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 7/11/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 * ****************************************************
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * ****************************************************
 * Thoughts:
 * 1.看到买卖股票就歇了,这次是k次买卖,是那个只准买卖2次的加强版
 * ****************************************************
 * Ref:https://discuss.leetcode.com/topic/8984/a-concise-dp-solution-in-java/4
 * DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
 * t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax) gives us the maximum price when we sell at this price;
 * tmpMax = Math.max(tmpMax, t[i - 1][j] - prices[j]) gives us the value when we buy at this price and leave this
 * value for prices[j+1].
 * ****************************************************
 * Time: 50mins
 * Beat: 10%
 * Bug: -
 * ****************************************************
 * Ref: explain
 * https://segmentfault.com/a/1190000004513614
 * 首先分析k与len的关系，如果k > len / 2，相当于比每两天交易一次（昨天买今天卖，明天买后天卖）还要多，
 * 那么就存在在同一天买入并卖出的情况。那么就相当于股票交易II中的做法，在所有上涨的情况下都做了交易，返回sum。
 * 然后分析非上述情况的k值：建立两个数组，数组local[i][j]表示在第i天正好做完第j次交易的收益，
 * 数组global[i][j]表示在第i天已经做完第j次交易的收益。
 * global[i][j]是二次优化收益，之前做过的交易总数可能等于j，也可能大于j。
 * local[i][j]是当次优化收益，分为两种情况：
 * A. i-1天做j-1次交易的二次优化收益global[i-1][j-1]，加上一次优化交易的利润：
 * 取第i-1天买入、第i天卖出的利润和0的较大者（0表示今日买入并卖出的情况），以达成第j次交易；
 * B. i-1天做j次交易的当次优化收益local[i-1][j]，加上一次等效交易的利润：
 * 即第i-1天完成j次交易等效于第i天完成j次交易的情况：假设第i天股票价格继续上涨，就相当于第i-1天卖出后继续买入，第i天又卖出，
 * 相当于第i-1天没有交易操作，第i天卖出才完成了第j次交易。
 * local[i][j]最后取上述两种情况的较大值。
 * ****************************************************
 * Ref2:http://liangjiabin.com/blog/2015/04/leetcode-best-time-to-buy-and-sell-stock.html
 * 此处的解释是原始出处
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No188_Best_Time_to_Buy_and_Sell_Stock_IV {
    public static void main(String[] args) {
        No188_Best_Time_to_Buy_and_Sell_Stock_IV No188 = new No188_Best_Time_to_Buy_and_Sell_Stock_IV();

        No188.maxProfit(2, new int[]{1, 3, 5, 1, 1, 2});
    }

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (prices == null || len == 0) return 0;
        if (k + k > len) {
            int sum = 0;
            for (int i = 1; i < len; i++) {
                sum += prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
            }
            return sum;
        }
        int[][] local = new int[len][k + 1];
        int[][] global = new int[len][k + 1];
        for (int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0),
                        local[i - 1][j] + diff);
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        return global[len - 1][k];

    }

}
