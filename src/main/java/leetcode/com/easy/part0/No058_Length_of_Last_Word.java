package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/2/16.
 * Location:
 * https://leetcode.com/problems/length-of-last-word/
 * *****************************************************
 * Description:
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example,
 * Given s = "Hello World",
 * return 5.
 * ******************************************************
 * Solutions:
 * 我的思路是从最后一个非空格字符开始循环至出现空格或字符循环完毕，
 * 网上答案：思路类似，比较不一样的是他选择了char数组，这样方便循环
 */
public class No058_Length_of_Last_Word {
    public int lengthOfLastWord(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        char[] chars = s.trim().toCharArray();
        int len = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] != ' ') {
                len++;
            }else {
                break;
            }
        }
        return len;
    }
}
