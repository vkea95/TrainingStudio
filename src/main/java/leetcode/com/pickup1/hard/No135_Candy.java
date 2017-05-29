package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 6/27/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/candy/
 * ****************************************************
 * Description:
 * There are N children standing in a line. Each child is assigned a rating value.
 * <p>
 * You are giving candies to these children subjected to the following requirements:
 * <p>
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * ****************************************************
 * Thoughts:
 * 问题在于:如何分配这个糖果的问题,每个人都有一个的话,那么先给谁加1个呢,给最大的加了1个后,次大的怎么加?所以策略上有问题
 * 那么是DFS?BFS?
 * 木有思路呢啊!
 * 看了答案才明白,其实很简单,就是从左到右的扫描一遍啊!!!
 * 估计解第一遍题目的时候,是刚做完某种题型,所以思路比较争取!!!现在完全懵了啊!!!
 * ****************************************************
 * Time: 40 mins:
 * Beat: 80%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No135_Candy {
    public int candy(int[] ratings) {

        int[] count = new int[ratings.length];
        for (int i = 1; i < count.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                //bug1:Not count[i]++ but count[i]=count[i-1]+1
                // count[i]++;
                count[i] = count[i - 1] + 1;
            }
        }
        for (int i = count.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i] && count[i - 1] <= count[i]) {
                count[i - 1] = count[i] + 1;
            }
        }
        int rst = ratings.length;
        for (int i : count) {
            rst += i;
        }
        return rst;

    }
}
