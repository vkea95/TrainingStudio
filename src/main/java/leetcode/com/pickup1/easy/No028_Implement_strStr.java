package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/implement-strstr/
 * ****************************************************
 * Description:
 * Implement strStr().
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * ****************************************************
 * Time:20mins
 * Beat:50
 * Bug:2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No028_Implement_strStr {

    public static void main(String[] args) {
        No028_Implement_strStr obj = new No028_Implement_strStr();
        obj.strStr("mississippi", "pi");
    }

    public int strStr(String haystack, String needle) {


        int len = haystack.length() - needle.length();
        //bug1: i的取值范围该是<= 而不是 <
        for (int i = 0, j = 0; i <= len; i++) {
            j = 0;
            for (; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) break;
            }
            //bug2:在此处判断是否j为needle的长度可以直接避免判断needle是否为""等等.
            if (j == needle.length()) return i;
        }
        return -1;
    }


    public int strStr_embed(String haystack, String needle) {

        int result = -1;
        if (haystack == null || needle == null) return result;

        result = haystack.indexOf(needle);

        return result;
    }
}
