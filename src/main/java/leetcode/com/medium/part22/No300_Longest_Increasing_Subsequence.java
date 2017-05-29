package leetcode.com.medium.part22;

import java.util.ArrayList;

/**
 * Created by tclresearchamerica on 4/26/16.
 * Location:
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * ****************************************************************
 * Description:
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 * Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * <p>
 * Your algorithm should run in O(n2) complexity.
 * <p>
 * Follow up: Could you improve it to O(n log n) time complexity?
 * ****************************************************
 * Analysis:
 * 1.按照初始的思路,这个会一个一个扫描后,再比较等等,类似于背包问题,每个数字的出现都可能改变结果,所以推测用动态规划来解决问题.
 * 2.动态规划的初始化和动态规划的方程式,并不是特别的清楚.感觉时间复杂度很高.看了网路答案后,发现时间复杂度确实很高.
 * 先把算法实现了再说吧,即设置一个Max的数组,存的就是当前这个元素的作为最大值所在的最长子序列.所以每个元素多要和之前的元素比大小,
 * 然后,取长度最长的值存入数组. 时间复杂度为O(n^2)
 * 3.设置这样一个list,但是网络的这个答案没有看明白,为什么会用到二分法呢,答案:认为的添加元素导致其为递增序列,...
 * 而且,保证后面再出现略大的数字,也会被放入原来的序列中,这样保证了长度的不变,而且一旦有更大的数字出来,也完全可以cover,
 * 这个和我的最原始思路吻合,完成了我没有完成的解法实体化
 * for each num in nums
 * if(list.size()==0)
 * add num to list
 * else if(num > last element in list)
 * add num to list
 * else
 * replace the element in the list which is the smallest but bigger than num
 * ****************************************************
 */
public class No300_Longest_Increasing_Subsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] max = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            //bug1: 没有初期化,每个数自己也算是序列中的一个,所以漏算的话,长度永远少1
            max[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max[i] = Math.max(max[i], max[j] + 1);
                }
            }
        }
        int result = 0;
        for (int n : max) {
            result = Math.max(result, n);
        }
        return result;
    }


    public int lengthOfLIS_II(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums) {
            if (list.size() == 0) {
                list.add(num);
            } else if (num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                int i = 0;
                int j = list.size() - 1;

                //bug1:二分法的用法,在于寻找比它大的,但是最小的那个数字,
                while (i < j) {
                    int mid = (i + j) / 2;
                    if (list.get(mid) < num) {
                        i = mid + 1;
                    } else {
                        j = mid;
                    }
                }

                list.set(j, num);
            }
        }

        return list.size();
    }
}
