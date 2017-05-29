package leetcode.com.pickup1.medium;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by tclresearchamerica on 6/1/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/h-index/
 * *******************************************
 * Description:
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to
 * compute the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have
 * at least h citations each, and the other N − h papers have no more than h citations each."
 * <p>
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them
 * had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each
 * and the remaining two with no more than 3 citations each, his h-index is 3.
 * <p>
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 * *******************************************
 * bug1:
 * 没有考虑单个元素的数组
 * bug2:
 * 没有判断null和size等于零的case
 * bug3:
 * result的算法有问题,result=不能等于论文的值,
 * *******************************************
 * *******************************************
 */
public class No274_H_Index_I {
    public static void main(String[] args) {
        No274_H_Index_I obj = new No274_H_Index_I();
        int[] test1 = new int[]{0, 0, 4, 4};
        int[] test2 = new int[]{100};
        obj.hIndex(test1);
    }

    public int hIndex(int[] citations) {

        int n = citations.length;
        int[] hIndex = new int[n + 1];
        for (int val : citations) {
            hIndex[val >= n ? n : val]++;

        }
        int sum = 0;
        for (int i = n; i > 0; i--) {
            sum += hIndex[i];
            if (i <= sum) {
                //bug4:sum-hIndex[i]没有写出来
                sum = Math.max(i, sum - hIndex[i]);
                break;
            }
        }
        return sum;
    }
}
