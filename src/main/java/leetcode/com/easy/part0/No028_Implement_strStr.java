package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/2/2.
 * Locations:
 * https://leetcode.com/problems/implement-strstr/
 * ***********************************************
 * Descriptions:
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * ***********************************************
 * Solutions:
 */
public class No028_Implement_strStr {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int j = 0;
            for (j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;

    }
}
