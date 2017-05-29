package leetcode.com.medium.part01;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tclresearchamerica on 6/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * ****************************************************
 * Description:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must
 * be a substring, "pwke" is a subsequence and not a substring.
 * ****************************************************
 * Thoughts:
 * 网络答案:
 * fast版:新的字符,如果不是第一次出现,则将index & i的较大值赋给i,供j-i计算最大值使用
 *
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No003_Longest_Substring_Without_Repeating_Characters {

    //beat 80%
    public int lengthOfLongestSubstring_fast(String s) {

        int max = 0, i = 0, j = 0, index;
        int[] hm = new int[128];
        while (j < s.length()) {
            if (hm[s.charAt(j)] == 0) {
                hm[s.charAt(j)] = j + 1;
                j++;
                if ((j - i) > max)
                    max = j - i;
            } else {
                //将其值初期化成0同时计算index和i直接的最大值
                index = hm[s.charAt(j)];
                hm[s.charAt(j)] = 0;
                if (i < index)
                    i = index;
            }
        }
        return max;

    }

    //Beats:48%
    public int lengthOfLongestSubstring_dp(String s) {
        if (s.length() == 0) return 0;
        //keep a hashmap which stores the characters in string as keys and their positions as values
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        for (int i = 0, j = 0; i < s.length(); ++i) {
            if (map.containsKey(s.charAt(i))) {
                //if found in hashmap that value,
                // then update j i.e pointer to the right of the same character last found.
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            //else put in map and get max pointer update with the current longest string
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;

    }


    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int length = 0;
        int startPos = 0;
        Map<String, Integer> res = new HashMap<String, Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (res.containsKey(s.substring(i, i + 1))) {
                if (startPos <= (res.get(s.substring(i, i + 1)))) {
                    length = i - startPos;
                    startPos = res.get(s.substring(i, i + 1)) + 1;
                    maxLength = maxLength > length ? maxLength : length;
                }
            }
            res.put(s.substring(i, i + 1), i);

        }
        length = s.length() - startPos;
        return maxLength > length ? maxLength : length;
    }

}
