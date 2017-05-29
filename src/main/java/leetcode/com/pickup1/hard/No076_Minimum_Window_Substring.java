package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 6/26/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/minimum-window-substring/
 * ****************************************************
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
 * ****************************************************
 * Thought:
 * 其实,还是没有想法,后来看了答案,也不是很明白,仔细阅读后才明白其中真谛.
 * 将256个字符,分别放在数组中,由各数组,一个是放target的,另一个是放source的,target的只有target本身的字符
 * ****************************************************
 * Time: 40 mins
 * Beat:30%
 * Bug: 2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No076_Minimum_Window_Substring {
    public String minWindow(String s, String t) {

        int[] targetArr = new int[256];
        int[] sourceArr = new int[256];
        initTarget(targetArr, t);
        String minRst = "";
        int minPath = s.length() + 1;

        //bug1:删除j<s.length()
//        for (int i = 0, j = 0; i < s.length() && j < s.length(); i++) {
        for (int i = 0, j = 0; i < s.length(); i++) {

            while (!isValid(targetArr, sourceArr) && j < s.length()) {
                sourceArr[s.charAt(j++)]++;
            }
            if (isValid(targetArr, sourceArr)) {
                if (j - i < minPath) {
                    minPath = j - i;
                    minRst = s.substring(i, j);
                }
            }
            sourceArr[s.charAt(i)]--;

        }

        return minRst;
    }

    private void initTarget(int[] targetArr, String target) {

        for (char c:target.toCharArray()) {
            targetArr[c]++;
        }
    }

    private boolean isValid(int[] targetArr, int[] sourceArr) {
        for (int i = 0; i < targetArr.length; i++) {
            if (sourceArr[i] < targetArr[i])
                return false;
        }
        return true;
    }
}
