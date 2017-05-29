package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/1/30.
 * Locations：
 * https://leetcode.com/problems/longest-common-prefix/
 * http://www.jiuzhang.com/solutions/longest-common-prefix/
 * ****************************************************
 * Desriptions：
 * Write a function to find the longest common prefix string amongst an array of strings.
 * *****************************************************
 * Solutions：
 * 1. Method 1, start from the first one, compare prefix with next string, until end;
 * 2. Method 2, start from the first char, compare it with all string, and then the second char
 */
public class No014_Longest_Common_Prefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while(j < prefix.length() && j < strs[i].length() && strs[i].charAt(j) == prefix.charAt(j)){
                j++;
            }
            if (j == 0) {
                return "";
            }
            prefix = prefix.substring(0, j);
        }
        return prefix;
    }
    public String longestCommonPrefix_board(String[] strs) {
        //boundary check
        if(strs==null || strs.length==0) return "";
        String temp =strs[0];
        for(String str:strs){
            if(str.length()<temp.length()) temp=str;
        }

        //travse
        int index=0;
        for(String str: strs){
            int len= temp.length();
            index=0;
            while(index<len){
                if(temp.charAt(index)==str.charAt(index)){
                    index++;
                }else{
                    break;
                }
            }
            if(index==0) break;
            temp=temp.substring(0,index);
        }
        return temp.substring(0,index);
    }
}
