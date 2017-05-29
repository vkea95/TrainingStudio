package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 6/26/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/length-of-last-word/
 * ****************************************************
 * Description:
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * For example,
 * Given s = "Hello World",
 * return 5.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No058_Length_of_Last_Word {

    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.trim();
        //   StringBuilder sb = new StringBuilder();
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break;
            } else {
                len++;
            }
        }
        return len;

    }
}
