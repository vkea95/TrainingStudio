package leetcode.com.tag.unionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by JianZhang on 10/2/17.
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * <p>
 * Your algorithm should run in O(n) complexity.
 */
public class No128_Longest_Consecutive_Sequence {
//    Credit:
//    这道题利用HashSet的唯一性解决，能使时间复杂度达到O(n)。首先先把所有num值放入HashSet，
// 然后遍历整个数组，如果HashSet中存在该值，就先向下找到边界，找的同时把找到的值一个一个从set中删去，然后再向上找边界，同样要把找到的值都从set中删掉。所以每个元素最多会被遍历两边，时间复杂度为O(n)。


    public int longestConsecutive(int[] nums) {
        if (nums == null) return 0;
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);

        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                continue;
            }
            int down = num - 1;
            while (set.contains(down)) {
                set.remove(down--);
            }
            int up = num + 1;
            while (set.contains(up)) {
                set.remove(up++);
            }
            max = max > (up - down - 1) ? max : (up - down - 1);
        }
        return max;
    }
}
