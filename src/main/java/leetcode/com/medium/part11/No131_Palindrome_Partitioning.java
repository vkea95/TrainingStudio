package leetcode.com.medium.part11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 5/16/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/palindrome-partitioning/
 * **************************************************************
 * Description:
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * **************************************************************
 * Analysis:
 * 1.完全没有思路,对于这个题和序列那样的题,完全没有概念.
 * **************************************************************
 */
public class No131_Palindrome_Partitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> output = new ArrayList<List<String>>();
        List<String> cur = new ArrayList<String>();

        if (s == null || s.length() == 0) {
            return output;
        }

        helper(output, cur, s.toCharArray(), 0);

        return output;
    }


    public void helper(List<List<String>> output, List<String> cur, char[] sChar, int index) {
        if (index >= sChar.length) {
            output.add(new ArrayList<String>(cur));
            return;
        }

        //save the char
        char temp = sChar[index];
        int tempIndex = index;

        while (tempIndex < sChar.length) {
            //bug1:if not equal, then it's not a palindrome string
            if (sChar[tempIndex] == temp) {
                if (isPalindrome(sChar, index, tempIndex)) {
                    String s = "";
                    for (int i = index; i <= tempIndex; i++) {
                        s += sChar[i];
                    }
                    cur.add(s);
                    helper(output, cur, sChar, tempIndex + 1);
                    //打扫战场,保证curt,在从一个新的position开始的时候,它又是全新的
                    cur.remove(cur.size() - 1);
                }
            }
            tempIndex++;
        }
    }


    private boolean isPalindrome(char[] sChar, int start, int end) {
        while (start <= end) {
            if (sChar[start++] != sChar[end--]) {
                return false;
            }
        }

        return true;
    }
}
