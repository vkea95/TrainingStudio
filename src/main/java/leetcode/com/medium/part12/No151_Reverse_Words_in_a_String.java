package leetcode.com.medium.part12;

/**
 * Created by tcl on 2016/3/29.
 * Location:
 * https://leetcode.com/problems/reverse-words-in-a-string/
 * ********************************************************
 * Description:
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 */
public class No151_Reverse_Words_in_a_String {
    public static void main(String[] args) {
        No151_Reverse_Words_in_a_String object = new No151_Reverse_Words_in_a_String();
        object.reverseWords("   a   b ");
    }

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        String[] separates = s.split(" ");
        StringBuffer sb = new StringBuffer();

        //bug2: forget the length of an array
        for (int i = separates.length - 1; i >= 0; i--) {
            sb.append(separates[i]);
            if (!"".equals(separates[i]))
                sb.append(" ");
        }
        //bug1: forget the length of the StringBuffer object
        //bug3: forget to judge the length ==0
        //bug4: the end index of an string should be sb.length -1
        return sb.length() == 0 ? "" : sb.toString().substring(0, sb.length() - 1);
    }
}
