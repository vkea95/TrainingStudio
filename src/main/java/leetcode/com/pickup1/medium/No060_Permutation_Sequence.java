package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 8/11/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/permutation-sequence/
 * **************************************************************
 * Description:
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * <p>
 * Note: Given n will be between 1 and 9 inclusive.
 * **************************************************************
 * Reference:https://segmentfault.com/a/1190000003766760
 * 复杂度
 * 时间 O(N) 空间 O(1)
 * <p>
 * 思路
 * 由于我们只要得到第K个全排列，而不是所有全排列，我们不一定要将所有可能都搜索一遍。根据全排列顺序的性质，我们可以总结出一个规律：
 * 假设全排列有n个数组成，则第k个全排列的第一位是k/(n-1)!。为了更形象一点，举例如下：
 * <p>
 * 123
 * 132
 * 213
 * 231
 * 312
 * 321
 * 在这种情况下，第一个数字每2!=2个情况就改变一次，假设求第6个排列，我们先将其减1，方便整除运算，然后5/2=2。对于第一位，
 * 我们有三种可选数字1、2、3，所以5/2=2意味着我们选择第3个数字，也就是3（如果商是s，则选第s+1个数字）。
 * %2得到1，这个1就是下一轮的k。
 * <p>
 * 注意
 * 这里有一个技巧，就是用一个列表将1到n存起来，每选用一个数就是移出那个数，就能保证不选重复数字的同时，其顺序也是一样的。
 * **************************************************************
 * Hindsight:
 * 1. 忘记该如何处理第K个排列的问题,根据题意推算出来
 * 2. 回想起来,还是要学会从题目给的条件中,发现线索,解决问题,k&(n-1)!的问题
 * **************************************************************
 * Time: 30 mins
 * Beat: 26~78
 * Bug: 1
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No060_Permutation_Sequence {

    public String getPermutation_slow(int n, int k) {
        int mod = 1;
        List<Integer> candidates = new ArrayList<>();
        // 先得到n!和候选数字列表
        for (int i = 1; i <= n; i++) {
            mod *= i;
            //bug1:
            candidates.add(i);
        }

        // 将k先减1方便整除
        k -= 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            mod /= (n - i);
            // 得到当前应选数字的序数
            int first = k / mod;
            // 得到用于计算下一位的k
            k %= mod;
            sb.append(candidates.get(first));
            // 在列表中移出该数字
            candidates.remove(first);


        }
        return sb.toString();

    }

    public String getPermutation_fast(int n, int k) {

        StringBuffer sb = new StringBuffer();
        boolean[] used = new boolean[n];

        //KeyPoint1:k=k-1 根据数组的下标为零,所以k-1
        k = k - 1;
        //KeyPoint2:1~n-1的阶乘
        int factor = 1;
        for (int i = 1; i < n; i++) {
            factor *= i;
        }

        for (int i = 0; i < n; i++) {
            //算应该哪个数字排在n!的结果的第i位
            int index = k / factor;
            //求余,为下个循环的处理做准备.
            k = k % factor;
            for (int j = 0; j < n; j++) {
                //寻找没有被使用过的数组元素.
                if (used[j] == false) {
                    //如果index=0,那么就使用第j位的数字哦
                    if (index == 0) {
                        used[j] = true;
                        //因为j从零开始,所以使用j+1,的位移
                        sb.append((char) ('0' + j + 1));
                        break;
                    } else {
                        //第j位不满足要求,则index--,移到下一个位置
                        index--;
                    }
                }
            }
            if (i < n - 1) {
                //由(n-1)!-->(n-2)!
                factor = factor / (n - 1 - i);
            }
        }

        return sb.toString();
    }
}
