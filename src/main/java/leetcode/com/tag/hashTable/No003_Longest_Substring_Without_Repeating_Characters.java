package leetcode.com.tag.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JianZhang on 10/6/17.
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke",
 * with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Bugs:
 * 1. 两个bug
 */
public class No003_Longest_Substring_Without_Repeating_Characters {

    //    用数组模拟HashMap
    public int lengthOfLongestSubstring_2(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] hm = new int[128];
        int maxLen = 0;

        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            //第一次读到该字符
            if (hm[s.charAt(i)] == 0) {
                hm[s.charAt(i)] = i + 1;
//                bug1: 这个计算必须应对各个相关值更新的状况,所以要外移动
//                maxLen = Math.max(maxLen, hm[s.charAt(i)] - start);
            } else {
                int index = hm[s.charAt(i)];
//                bug2: 这个地方要存index+1,否则会和基本判断条件==0混淆,且和前半部分的判断不符
//                hm[s.charAt(i)] = i;
                hm[s.charAt(i)] = i + 1;
                if (start < index) start = index;
            }
            maxLen = Math.max(maxLen, hm[s.charAt(i)] - start);

        }
        return maxLen;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int minIndex = 0;
        Map<Character, Integer> map = new HashMap<>();

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {

//                bug1: 判断条件和minIndex的更新条件错误
                if (map.get(chars[i]) >= minIndex)
                    minIndex = map.get(chars[i]) + 1;

//            } else {
//                bug2: 此处的计算,该挪到外面去,因为minIndex的值被更新了,
//                maxLen = Math.max(maxLen, i - minIndex + 1);
            }
            map.put(chars[i], i);
            maxLen = Math.max(maxLen, i - minIndex + 1);
        }

        return maxLen;
    }
}
