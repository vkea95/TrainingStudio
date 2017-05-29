package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 7/19/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/palindrome-partitioning/
 * ****************************************************
 * Description:
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * For example, given s = "aab",
 * Return
 * <p>
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * ****************************************************
 * Thoughts:
 * 1.形成文字回文,所以呢,单个字符肯定是回文,那么就是说recursive调用好了
 * 2.然后,再追加一个判断是否为回文的function就可以啦。
 * ****************************************************
 * Time: 20 mins
 * Beat: 95%
 * Bug:0
 * ****************************************************
 * Hindsight:
 * 1.看到题目,还是有点无措地,仔细分析过后,就发现有现成的套路可以使用
 * 2.套路:递归模板+check方法=解决方案
 * 3.此题属于subset的变种
 * ****************************************************
 * ****************************************************
 */
public class No131_Palindrome_Partitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> solution = new ArrayList<>();
        if (s == null || s.length() == 0) return result;
        helper(result, solution, s.toCharArray(), 0);
        return result;
    }

    private void helper(List<List<String>> result, List<String> solution, char[] chars, int start) {
        if (start == chars.length) {
            result.add(new ArrayList<>(solution));
            return;
        }

        for (int i = start; i < chars.length; i++) {
            int left = start, right = i;
            if (isPlaindrome(chars, left, right)) {
                solution.add(String.valueOf(chars, start, right - left + 1));
                helper(result, solution, chars, i + 1);
                solution.remove(solution.size() - 1);
            }
        }
    }

    private boolean isPlaindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left++] != chars[right--]) {
                return false;
            }
        }
        return true;
    }
}
