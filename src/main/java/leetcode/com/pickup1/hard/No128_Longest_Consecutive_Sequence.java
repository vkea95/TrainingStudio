package leetcode.com.pickup1.hard;

import sun.nio.cs.ext.MacHebrew;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tclresearchamerica on 8/2/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * ****************************************************
 * Description:
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * ****************************************************
 * Thought:
 * 1.题意:A.无序 B.O(n) 只能遍历一遍 C.求次数,而不是实际的number区间
 * 2.思绪:A.位操作? B.分别保存? C.虽然要求了时间复杂度,可是在每次遍历过程中,可以进行很多的操作呢 D.桶排序
 * 3.已知数据结构,再设计算法或是数据结构
 * ****************************************************
 * Solution:
 * 不能排序，那么就随机找一个，然后+1，看看是否有，然后-1，看看是否有，都没有后，再up-down-1 算结果
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No128_Longest_Consecutive_Sequence {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int rst =0;
        Set<Integer> hashSet = new HashSet<>();
        for (int num : nums) hashSet.add(num);

        for (int num : nums) {
            int up=num+1;
            while (hashSet.contains(up)){
                hashSet.remove(up);
                up++;

            }
            int down = num-1;
            while (hashSet.contains(down)){
                hashSet.remove(down);
                down--;
            }

            rst= Math.max(rst,up-down-1);
        }
        return rst;
    }
}
