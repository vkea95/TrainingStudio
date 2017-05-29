package leetcode.com.hard.part0;

/**
 * Created by jason on 2016/3/2.
 * Location:
 * https://leetcode.com/problems/minimum-window-substring/
 * ********************************************************
 * Description:
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T
 * in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 * ********************************************************
 * Solution:
 * 曾经以为会用到DP，后来才发现hash也可以解决问题，即挖256个坑对应256个字符，下标即字符的char的值，即找到target的字符的
 * 值都大于等于已经扫描的字符串的字符，即发现目标串为其子串。然后求长度最短
 * 换个思路而已，类似数学变换，但因为做了hash处理，所以可以保证准确性。看来hash处理还是十分有必要的
 * valid函数的所check的sourceHash的值随着j在不断变化，所以才要随时进行check
 */
public class No076_Minimum_Window_Substring {

    int initTargetHash(int[] targetHash, String target) {
        int targetNum = 0;
        for (char ch : target.toCharArray()) {
            targetNum++;
            targetHash[ch]++;
        }
        return targetNum;
    }

    boolean valid(int[] sourceHash, int[] targetHash) {
        for (int i = 0; i < 256; i++) {
            if (targetHash[i] > sourceHash[i])
                return false;
        }
        return true;
    }

    public String minWindow(String source, String target) {
        //queueing the position that matches the char in T
        int ans = Integer.MAX_VALUE;
        String minStr = "";

        int[] sourceHash = new int[256];
        int[] targetHash = new int[256];

        initTargetHash(targetHash, target);
//        int j = 0;
//        int i = 0;
        for (int i = 0, j = 0; i < source.length(); i++) {
            while (!valid(sourceHash, targetHash) && j < source.length()) {
                sourceHash[source.charAt(j)]++;
                if (j < source.length())
                    j++;
                else
                    break;
            }
            if (valid(sourceHash, targetHash)) {
                if (ans > j - i) {
                    ans = Math.min(ans, j - i);
                    minStr = source.substring(i, j);
                }
            }
            sourceHash[source.charAt(i)]--;
        }
        return minStr;
    }
}
