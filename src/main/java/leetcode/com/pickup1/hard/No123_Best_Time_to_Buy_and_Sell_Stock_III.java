package leetcode.com.pickup1.hard;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by tclresearchamerica on 6/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * ****************************************************
 * Description:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * ****************************************************
 * Thoughts:
 * 理解为求数组中某元素两侧的最大值和最小值,相当于将easy那道题拼成2道题-->运行时间长,TLE
 * ****************************************************
 * Correct thought:
 * 分别从一个节点的两侧去扫描寻找某节点2测得最大获益,用的是DP
 * ****************************************************
 * Pass: NG
 * Time: 50 mins
 * Beats: 11%
 * Bug: 1
 * ****************************************************
 */
public class No123_Best_Time_to_Buy_and_Sell_Stock_III {
    public static void main(String[] args) {
        No123_Best_Time_to_Buy_and_Sell_Stock_III obj = new No123_Best_Time_to_Buy_and_Sell_Stock_III();
        int[] prices = new int[]{1, 4, 2};
//        obj.maxProfit(prices);
    }

    public int maxProfit_wrong(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int result = 0;
        int n = prices.length;
        int[] leftArray = new int[n];
        int[] rightArray = new int[n];

        //from left to right
        int min = prices[0];
        leftArray[0] = 0;
        for (int j = 1; j < n; j++) {
            if (min > prices[j]) {
                min = prices[j];
            }
            leftArray[j] = Math.max(leftArray[j - 1], prices[j] - min);
        }

        int max = prices[n - 1];
        rightArray[n - 1] = 0;
        for (int j = n - 2; j >= 0; j--) {
            if (prices[j] > max) {
                max = prices[j];
            }
            rightArray[j] = Math.max(rightArray[j + 1], max - prices[j]);
        }
        for (int j = 0; j < n; j++) {
            int temp = leftArray[j] + rightArray[j];
            result = result > temp ? result : temp;
        }
        return result;
    }

    public int maxProfit_wrong_thoughts(int[] prices) {
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        if (prices == null) return 0;

        //bug1:maxProfit最小值该是0,而非integer的最小值
        int maxProfit = 0;

        int i = 0, j = 1;
        int result = 0;
        boolean saveFlg = false;
        queue.offer(result);
        queue.offer(result);

        while (j < prices.length) {
            if (prices[j] > prices[i]) {
                if (j + 1 < prices.length && prices[j] < prices[j + 1]) {
                    saveFlg = false;

                } else {
                    result = prices[j] - prices[i];
                    queue.offer(result);
                    saveFlg = true;
                    j++;
                    i = j;

                }
            } else {
                i = j;
            }
            j++;
        }


        if (!saveFlg) {
            queue.offer(result);

        }
        maxProfit = queue.poll() + queue.poll();
        return maxProfit;
    }

    public int maxProfit_TLE(int[] prices) {
        int maxProfit = 0;
        for (int j = 1; j < prices.length - 1; j++) {
            int result = maxProfitHelper(prices, 0, j) + maxProfitHelper(prices, j, prices.length);
            maxProfit = maxProfit > result ? maxProfit : result;
        }
        return maxProfit;
    }

    public int maxProfitHelper(int[] prices, int start, int end) {
        if (prices == null) return 0;

        //bug1:maxProfit最小值该是0,而非integer的最小值
        int maxProfit = 0;

        int i = start;
        for (int j = i + 1; j < end; j++) {
            if (prices[j] > prices[i]) {
                int result = (prices[j] - prices[i]);
                maxProfit = maxProfit > result ? maxProfit : result;
            } else {
                i = j;
            }

        }

        return maxProfit;
    }
}
