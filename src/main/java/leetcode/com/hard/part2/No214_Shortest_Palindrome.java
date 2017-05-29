package leetcode.com.hard.part2;

/**
 * Created by tclresearchamerica on 5/22/16.
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
 * Analysis:
 * 1.思路:
 * A.完全不对称的话,就是只要简单拷贝就好了
 * B.S本身就是对称的话,例如:aaaaa,那么就不需要增加字符串
 * C.S本身是部分对称:aab->baab, abcb->bcbabcb
 * 2.网络答案:KMP算法,完全看不明白,不知道为什么这么搞法......啥事KMP算法啊,怎么玩的呢?
 * ***************************************************************
 * ***************************************************************
 */
public class No214_Shortest_Palindrome {

    public static void main(String[] args) {
        No214_Shortest_Palindrome obj = new No214_Shortest_Palindrome();
        obj.shortestPalindrome("abcdefgh");
        obj.shortestPalindrome("abba");
    }

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

    public String shortestPalindrome_slow(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);

        //get the maximum palin part in s starts from 0
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    public int[] getTable(String s) {
        //get lookup table
        int[] table = new int[s.length()];

        //pointer that points to matched char in prefix part

        int index = 0;
        //skip index 0, we will not match a string with itself
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(index) == s.charAt(i)) {
                //we can extend match in prefix and postfix
                table[i] = table[i - 1] + 1;
                index++;
            } else {
                //match failed, we try to match a shorter substring

                //by assigning index to table[i-1], we will shorten the match string length, and jump to the
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
