package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/23/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * ****************************************************
 * Description:
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * ****************************************************
 * Time: 10 mins
 * Beat: 70%
 * Bug: 2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No151_Reverse_Words_in_a_String {
    public String reverseWords(String s) {

        String[] arr = s.trim().split(" ");

        StringBuffer sb = new StringBuffer();
        for (int i = arr.length - 1; i >= 0; i--) {
            //bug1:没有处理被切分出来的空格，所以会有多个空格
            //bug3:此处不必triem
//            if (arr[i].trim().length() != 0) {
            if (!arr[i].equals("")) {
                sb.append(arr[i]);
                sb.append(" ");
            }
        }
        //bug2:此处需要判断length的大小,防止输入字符的长度为零时,发生越界错误
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}
