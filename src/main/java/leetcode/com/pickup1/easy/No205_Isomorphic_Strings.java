package leetcode.com.pickup1.easy;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by tclresearchamerica on 10/6/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/isomorphic-strings/
 * ****************************************************
 * Description:
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * <p>
 * For example,
 * Given "egg", "add", return true.
 * <p>
 * Given "foo", "bar", return false.
 * <p>
 * Given "paper", "title", return true.
 * <p>
 * Note:
 * You may assume both s and t have the same length.
 * ****************************************************
 * Bug1:
 * 只考虑了单向的map存储问题,双向的没有考虑。
 * 虽然尝试使用了hashset可是会超时,所以只好放弃,改用hashmap
 * ****************************************************
 * Hindsight:
 * 1.换成了数组的实现方式,发现题目中的测试用例除了字母外,还有数字,所以就把数组长度从26,扩展到256了,
 * 但是初期化的值是'_',这个可能会根据测试用例的值被影响到
 * -->但是执行效率上还是蛮高的,直接干到97%,可见数组的连续访问还是很好的啦
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No205_Isomorphic_Strings {
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        char[] sMap = new char[256];
        char[] tMap = new char[256];
        Arrays.fill(sMap, '_');
        Arrays.fill(tMap, '_');

        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        for (int i = 0; i < sChar.length; i++) {

            if (sMap[sChar[i]] == '_') {
                if (tMap[tChar[i]] != '_') {
                    return false;
                }
                sMap[sChar[i]] = tChar[i];
                tMap[tChar[i]] = sChar[i];
            } else {
                if (sMap[sChar[i]] != tChar[i])
                    return false;
            }
        }

        return true;
    }
}
