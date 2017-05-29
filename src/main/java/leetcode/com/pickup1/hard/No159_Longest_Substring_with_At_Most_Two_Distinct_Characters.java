package leetcode.com.pickup1.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tclresearchamerica on 9/18/16.
 * Locations：
 * https://segmentfault.com/a/1190000003790746
 * ************************************************************************
 * Description:
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
 * For example, Given s = “eceba”,
 * T is "ece" which its length is 3.
 * <p>
 * ************************************************************************
 * 哈希表法
 * 复杂度
 * 时间 O(N) 空间 O(1)
 * <p>
 * 思路
 * 我们遍历字符串时用一个哈希表，但这个哈希表只记录两个东西，一个字母和它上次出现的时的下标，另一个字母和它上次出现时候的下标。
 * 这样，如果新来的字母已经存在与表中，或者表中现在少于两个字母，就没关系，我们只要更新下它新的下标就行了。如果不存在于表中，
 * 则找出表中两个字母中，靠前面的那个，然后把这个较前的字母去掉，再加入这个新的字母。这样，我们就能维护一个窗口，
 * 保证其中只有两种字母。每次加入新字母时，说明上一个子串已经达到最长了，这时候我们判断下时候要更新全局最长子串就行了
 * 。这个通过用哈希表记录字母上次出现的下标，来维护一个窗口的方法也可以用于Longest Substring Without Repeating Characters。
 * <p>
 * 注意
 * 遍历完成后还要有一次额外的更新最大长度的操作，以处理最长字符串在末尾的情况。
 * 新的子字符串的起点应该是重复元素的下标加一
 * 字符串问题要注意处理空字符串的特例
 * ************************************************************************
 * 后续 Follow Up
 * Q: 能否不用哈希表完成本题？ A：可以将哈希表换成和字符一一映射的数组。如果是ASCII字符集，可以初始化一个256个元素的数组，
 * 数组的下标对应于字符的ASCII码，而数组的内容则是每个字符上次出现的位置。
 * ************************************************************************
 * 类似:
 * 003,030,159
 * ************************************************************************
 * ************************************************************************
 * ************************************************************************
 * ************************************************************************
 * ************************************************************************
 * ************************************************************************
 * ************************************************************************
 * ************************************************************************
 * ************************************************************************
 */
public class No159_Longest_Substring_with_At_Most_Two_Distinct_Characters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        String longestSubstr = "";
        int maxLength = 0, start = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果表中已经有两个字母，且遇到了表中没有的新字母时，加入新字母
            if (map.size() >= 2 && !map.containsKey(c)) {
                int leftMost = s.length();
                // 先计算出新字母之前的子串的长度
                if (i - start > maxLength) {
                    longestSubstr = s.substring(start, i);
                    maxLength = i - start;
                }
                // 找出哪个字母更靠前，将其去除
                for (Character key : map.keySet()) {
                    if (map.get(key) < leftMost) {
                        leftMost = map.get(key);
                    }
                }
                // 更新加入新字母后子串的起始位置，这个位置是被去除字母最后一次出现的位置加1
                start = leftMost + 1;
                map.remove(s.charAt(leftMost));
            }
            // 更新该字母下标
            map.put(c, i);
        }
        // 处理最后一个子串
        if (s.length() - start > maxLength) {
            longestSubstr = s.substring(start, s.length());
            maxLength = s.length() - start;
        }
        return maxLength;
    }

    //寻找至多只含有2个不同字符的子串
    public int lengthOfLongestSubstringTwoDistinct_zj(String s) {
        //boundary chek
        if (s == null || s.length() == 0) return 0;
        int maxLength = 0;
        String longestStr = "";
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //检查子串是否已经含有2个字符 且新字符是否在子串中
            if (map.size() >= 2 && !map.containsKey(c)) {
                int leftMost = s.length();
                if (i - start > maxLength) {
                    longestStr = s.substring(start, i);
                    maxLength = i - start;
                }
                // 找出哪个字母更靠前，将其去除
                for (Character key : map.keySet()) {
                    leftMost = map.get(key) < leftMost ? map.get(key) : leftMost;
                }
                map.remove(s.charAt(leftMost));
                start = leftMost + 1;

            }


            //记录字符出现的位置
            map.put(c, i);
        }
        //处理最后一个字符
        if (s.length() - start > maxLength) {
            longestStr = s.substring(start, s.length());
            maxLength = s.length() - start;
        }
        return maxLength;
    }
}
