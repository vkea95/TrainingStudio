package leetcode.com.pickup1.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tclresearchamerica on 6/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * ****************************************************
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must
 * be a substring, "pwke" is a subsequence and not a substring.
 * ****************************************************
 * Thought:
 * 1.
 * ****************************************************
 * 这道题的解法没有反应到IntelIngentJ中,所以是copy网络答案搞的解法
 * Time: 30 mins
 * Beats: 90%
 * Bug:-
 * ****************************************************
 * Ref:https://segmentfault.com/a/1190000002986128
 * 哈希表双指针法 Hash Table with Two Pointers
 * 复杂度
 * 时间O(n) 空间O(1) 字符种类数有限
 * <p>
 * 思路
 * 根据最长不重复子字符串的定义，我们在遍历字符串时一旦遇到当前子字符串中已有字符，那么新的子字符串应该从这个重复点的下一位开始。
 * 所以为了在一次遍历中就能找出最大长度，我们需要记录两个东西。第一是某个字母是否在当前子字符串中出现过，
 * 第二是如果出现过那它在字符串中的位置是什么。而最简单的判断某个字符是否在当前子字符串中出现过的方法，
 * 就是看它上次出现的位置是在当前子字符串第一个字符的前面还是后面，所以我们只要记录该字符上次出现的位置就可以同时满足这两个要求。
 * 哈希表是最自然的想法。在遍历字符串时，我们先根据哈希表找出该字符上次出现的位置，如果大于等于子字符串首，便更新子字符串首。
 * 因为这里相当于上一个子字符串已经结束，我们还要更新最大长度。结束后，将该字符新的位置放入哈希表中。
 * <p>
 * 注意
 * 遍历完成后还要有一次额外的更新最大长度的操作，以处理最长字符串在末尾的情况。
 * 新的子字符串的起点应该是重复元素的下标加一
 * 字符串问题要注意处理空字符串的特例
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No003_Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring_ref(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> table = new HashMap<Character, Integer>();
        int lowerBound = 0, max = 1;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Integer last = table.get(c);
            if (last != null && last >= lowerBound) {
                max = Math.max(i - lowerBound, max);
                lowerBound = last + 1;
            }
            table.put(c, i);
        }
        return Math.max(s.length() - lowerBound, max);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        int[] hm = new int[128];
        int lowerBound = 0, curIndex = 0, index = 0, max = 0;
        while (curIndex < s.length()) {
            if (hm[s.charAt(curIndex)] == 0) {
                hm[s.charAt(curIndex)] = curIndex + 1;
                curIndex++;
                if (curIndex - lowerBound > max) max = curIndex - lowerBound;
            } else {
                index = hm[s.charAt(curIndex)];
                if (lowerBound < index)
                    lowerBound = index;
                hm[s.charAt(curIndex)] = 0;
            }
        }
        return max;
    }
}
