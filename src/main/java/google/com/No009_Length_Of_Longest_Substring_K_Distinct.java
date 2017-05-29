package google.com;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tclresearchamerica on 4/15/16.
 * *****************************************
 * Description:
 * 给定一个字符串,找到最长的包含最多K个不同字符的子串,输出最长字串的长度即可
 * Example:
 * 给出字符串"eceba", k = 2
 * 输出答案3,最长包含最多2个不同字符的子串为"ece"
 * *****************************************
 * Analyst:
 * 这一类题目是强化班讲到的经典2个指针中的前向型
 * *****************************************
 * Hints:
 * 根据网络答案,精确的算法是基于对数据结构的深度理解上的,Map这样的数据结构可以将时间上的问题投射为空间上的问题
 */
public class No009_Length_Of_Longest_Substring_K_Distinct {

    public int lengthOFLongestSubstringKDistince(String s, int k) {
        int maxLen = 0;

        //key: letter; value: the number of occurrences
        Map<Character, Integer> map = new HashMap<>();
        int i, j = 0;
        char c;

        for (i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                c = s.charAt(j);
                if (map.containsKey(c)) {

                    map.put(c, map.get(c) + 1);
                } else {
                    //保证至多含有k个不同字符,若超出,则重新计算9
                    if (map.size() == k)
                        break;
                    map.put(c, 1);
                }
                j++;
            }

            maxLen = Math.max(maxLen,j-i);
            c=s.charAt(i);
            if (map.containsKey(c)){
                if (map.get(c)>1){
                    map.put(c,map.get(c)-1);
                }else {
                    map.remove(c);
                }
            }
        }


        return maxLen;
    }
}
