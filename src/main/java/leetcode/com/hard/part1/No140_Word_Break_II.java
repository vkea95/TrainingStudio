package leetcode.com.hard.part1;

import java.util.*;

/**
 * Created by jason on 2016/3/22.
 * Location:
 * https://leetcode.com/problems/word-break-ii/
 * **********************************************
 * Description:
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is
 * a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 * **********************************************
 * Solution:
 * 完全没有思路啊！！！
 * 网络答案：递归处理，凡是找到存在的字符串，就继续寻找呗，然后累计，如果
 */
public class No140_Word_Break_II {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        return helper(s, wordDict, map);
    }

    private List<String> helper(String s, Set<String> wordDict, Map<String, List<String>> map) {

        //bug1: contains -> containsKey
        if (map.containsKey(s)) return map.get(s);

        List<String> result = new ArrayList<>();

        //bug3: forget to deal with s.length<=0
        if (s.length() <= 0) return result;

        //bug2: s.length -> s.length()
        for (int i = 1; i <= s.length(); i++) {

            String subStr = s.substring(0, i);

            //bug5:s.contains(subStr) -> wordDict.contains(subStr)
            if (wordDict.contains(subStr)) {

                if (i == s.length()) {
                    //bug4: result.add(s) -> result.add(subStr)
                    result.add(subStr);

                } else {

                    String suffixStr = s.substring(i);
                    List<String> items = helper(suffixStr, wordDict, map);

                    for (String item : items) {
                        result.add(subStr + " " + item);
                    }
                }
            }
        }

        map.put(s, result);
        return result;
    }
}
