package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 8/19/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/shortest-palindrome/
 * ***************************************************************
 * Description:
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * For example:
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No214_Shortest_Palindrome {


    public String shortestPalindrome(String s) {
        if (s.length() <= 1)
            return s;
        char[] c_str = s.toCharArray();
        // the next array stores the failure function of each position.
        int[] next = new int[c_str.length];
        next[0] = -1;
        int i = -1, j = 0;
        while (j < c_str.length - 1) {
            if (i == -1 || c_str[i] == c_str[j]) {
                i++;
                j++;
                next[j] = i;
                if (c_str[j] == c_str[i])
                    next[j] = next[i];
            } else i = next[i];
        }
        // match the string with its reverse.
        i = c_str.length - 1;
        j = 0;
        while (i >= 0 && j < c_str.length) {
            if (j == -1 || c_str[i] == c_str[j]) {
                i--;
                j++;
            } else {
                j = next[j];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (i = c_str.length - 1; i >= j; i--) sb.append(c_str[i]);
        for (char c : c_str) sb.append(c);
        return sb.toString();
    }

    private int[] getTable(String s) {
        //get lookup table
        int[] table = new int[s.length()];

        //pointer that points to mached char in prefix part

        int index = 0;

        //skip index 0, we will not match a string with itself
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(index) == s.charAt(i)) {
                //we can extend match in prefix and postfix
                table[i] = table[i - 1] + 1;
                index++;
            } else {
                //match failed,we try to match a shorter substring
                //by assigning index to table[i-1],
                //we will shorten the match string length
                //and jump to the
                //prefix part that we used to match postfix ended at i - 1
                index = table[i - 1];


                while (index > 0 && s.charAt(index) != s.charAt(i)) {
                    //we will try to shorten the match string length until we revert to the beginning of match (index 1)
                    index = table[index - 1];
                }

                //when we are here may either found a match char or we reach the boundary and still no luck
                //so we need check char match
                if (s.charAt(index) == s.charAt(i)) {
                    //if match, then extend one char
                    index++;
                }

                table[i] = index;
            }
        }
        return table;
    }
}
