package leetcode.com.hard.part1;

import java.util.Arrays;

/**
 * Created by jason on 2016/3/20.
 * Location:
 * https://leetcode.com/problems/candy/
 * ****************************************
 * Description:
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * ****************************************
 * Solution:
 * 关键条件是，其rating value的值会大于邻居值即可，
 * 所以，就从左侧扫描，凡是比【i】》【i-1】就给【i】=【i-1】+1，
 * 然后，从右侧扫描，凡是【i-1】》【i】，理论上该给【i-1】=【i】+1，但是如果count【i-1】》count【i】就不必加了，
 * 这样保证了minimum candies，
 */
public class No135_Candy {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int[] count = new int[ratings.length];
        //bug1:remember the fill method
        Arrays.fill(count, 1);

        int sum = 0;

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                //bug3: wrong count[i]++
                count[i] = count[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 1; i >= 1; i--) {
            sum += count[i];
            //bug2: count[i-1] <= count[i]
            if (ratings[i - 1] > ratings[i] && count[i - 1] <= count[i]) {
                //bug4: wrong: count[i-1]++
                count[i - 1] = count[i] + 1;
            }
        }
        sum += count[0];
        return sum;

    }
}
