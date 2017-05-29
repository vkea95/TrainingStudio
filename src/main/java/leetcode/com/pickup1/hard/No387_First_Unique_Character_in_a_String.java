package leetcode.com.pickup1.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tclresearchamerica on 8/28/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * ***************************************************************
 * Description:
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * Examples:
 * s = "leetcode"
 * return 0.
 * s = "loveleetcode",
 * return 2.
 * ***************************************************************
 * Thought:
 * 1.需要判断是否出现,出现后该如何处理,这里又有一个顺序的问题
 * 2.Set可以处理是否会有出现的可能
 * 3.可以用一个2维数组,来解决问题,最后要再遍历一次,但是这个里面的初期值是0,会和index的0,产生歧义,需要额外的初期化处理
 * 4.需要这样的数据结构,记录字符首次出现的位置(从零开始),出现的次数
 * 5.搞个hashSet,再加上写额外的处理好了,如果只出现一次,那么就记录下标,如出现多次,则将下标设为-1
 * ***************************************************************
 * Time:20min
 * Beat:-
 * Bug:0
 * ***************************************************************
 * Hindsight:
 * 1.看到题,没有一下子发现key point,但是逐步分析后,还是可以解开题目的,执行效率略低,78ms
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No387_First_Unique_Character_in_a_String {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (map.containsKey(c)) {
                map.put(c, -1);
            } else {
                map.put(c, i);
            }
        }
        int result = s.length();

        for (Character c : map.keySet()) {
            result = (map.get(c) != -1 && map.get(c) < result) ? map.get(c) : result;

        }
        return result == s.length() ? -1 : result;
    }
}
