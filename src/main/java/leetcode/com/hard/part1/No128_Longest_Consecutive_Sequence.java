package leetcode.com.hard.part1;

import java.util.HashSet;

/**
 * Created by jason on 2016/3/20.
 * Location:
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * ************************************************************
 * Description:
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 * *************************************************************
 * Solution:
 * 不能排序，那么就随机找一个，然后+1，看看是否有，然后-1，看看是否有，都没有后，再up-down-1 算结果
 */
public class No128_Longest_Consecutive_Sequence {
    public int longestConsecutive(int[] nums) {
        int rst = 0;

        HashSet<Integer> hash = new HashSet<>();

        for (int num : nums) hash.add(num);

        for (int num : nums) {
            int down = num - 1;
            while (hash.contains(down)) {
                hash.remove(down);
                down--;
            }
            int up = num + 1;
            while (hash.contains(up)) {
                hash.remove(up);
                //bug2:up-- --> up++
                up++;
            }

            //bug1: up-down-2 --> up-down-1
            rst = Math.max(rst, up - down - 1);
        }
        return rst;

    }
}
