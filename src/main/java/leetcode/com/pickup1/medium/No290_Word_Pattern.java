package leetcode.com.pickup1.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tclresearchamerica on 6/23/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/word-pattern/
 * ****************************************************
 * Description:
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern
 * and a non-empty word in str.
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters
 * separated by a single space
 * ****************************************************
 * Time: 10 mins
 * Beats: 20%
 * Bug: 0
 * ****************************************************
 * Thoughts:
 * 解法的基本思路和第一次做的时候是一样的.唯一要确保的就是pair的充分必要性,需要检查key所对应的value是否已经被处理过
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No290_Word_Pattern {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) return false;
        String[] strArray = str.split(" ");
        if (pattern.length() != strArray.length) return false;
        Set<Character> set = new HashSet<>();
        HashMap<String, Character> hashMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (!hashMap.containsKey(strArray[i])) {
                if (set.contains(pattern.charAt(i))) return false;
                set.add(pattern.charAt(i));
                hashMap.put(strArray[i], pattern.charAt(i));
            } else {
                if (pattern.charAt(i) != hashMap.get(strArray[i])) {
                    return false;
                }
            }

        }

        return true;
    }

}
