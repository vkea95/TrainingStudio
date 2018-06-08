package leetcode.com.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by tclresearchamerica on 5/31/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/remove-duplicate-letters/
 * *******************************************
 * Description:
 * Given a string which contains only lowercase letters, remove dupNumber letters so that every letter appear
 * once and only once. You must make sure your result is the smallest in lexicographical order among
 * all possible results.
 * <p>
 * Example:
 * Given "bcabc"
 * Return "abc"
 * <p>
 * Given "cbacdcbc"
 * Return "acdb"
 * *******************************************
 * Solution:
 * 1.完全 https://leetcode.com/discuss/74373/java-2ms-two-pointers-solution-or-stack-simulation-beats-72%25
 * *******************************************
 * Beat:90%
 * *******************************************
 * *******************************************
 * *******************************************
 * *******************************************
 * *******************************************
 */
public class No316_Remove_Duplicate_Letters {
    public static void main(String[] args) {
        No316_Remove_Duplicate_Letters obj = new No316_Remove_Duplicate_Letters();
        obj.removeDuplicateLetters("bcabc");
    }

    public String removeDuplicateLetters(String s) {
        /*
         * First loop: use an array cnt[] to count the number of times
         * appeared for each letter in s.
         *
         * Second loop (Greedy): use a stack, pop() while (!stack.isEmpty()
         * && (sc = stack.peek()) >= c && cnt[sc] > 0)
         */

        int i, n = s.length();
        int[] cnt = new int[128];
        boolean[] inRes = new boolean[128]; // whether a char is in res[]
        char[] res = s.toCharArray(); // simulate a stack

        for (i = 0; i < n; i++)
            cnt[res[i]]++;

        char c, sc;
        int end = -1;
        // now cnt[c] means the remaining count of the char c
        for (i = 0; i < n; i++) {
            c = res[i];
            if (inRes[c]) {
                cnt[c]--;
                continue;
            }

            while (end >= 0 && (sc = res[end]) >= c && cnt[sc] > 0) {
                end--;
                inRes[sc] = false;
            }

            res[++end] = c;
            cnt[c]--;
            inRes[c] = true;
        }
        return String.valueOf(res).substring(0, end + 1);

    }

}
