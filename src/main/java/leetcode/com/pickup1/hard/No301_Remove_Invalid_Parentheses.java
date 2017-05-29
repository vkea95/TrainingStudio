package leetcode.com.pickup1.hard;

import java.util.*;

/**
 * Created by tclresearchamerica on 10/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * ****************************************************
 * Description:
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * ****************************************************
 * Thought:
 * 考虑使用BFS,但是似乎没有明确思路
 * ****************************************************
 * ****************************************************
 * Ref:https://discuss.leetcode.com/topic/28827/share-my-java-bfs-solution
 * BFS
 * ****************************************************
 * Ref: https://discuss.leetcode.com/topic/34875/easy-short-concise-and-fast-java-dfs-3-ms-solution/2
 * DFS:
 * For a better view see here
 * <p>
 * Key Points:
 * <p>
 * Generate unique answer once and only once, do not rely on Set.
 * Do not need preprocess.
 * Runtime 3 ms.
 * Explanation:
 * We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
 * The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative,
 * we have more ‘)’ than ‘(‘ in the prefix.
 * <p>
 * To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix.
 * However, if we remove any one, we will generate duplicate results, for example: s = ()),
 * we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first )
 * in a series of concecutive )s.
 * <p>
 * After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string.
 * However, we need to keep another information: the last removal position. If we do not have this position,
 * we will generate duplicate by removing two ‘)’ in two steps only with a different order.
 * For this, we keep tracking the last removal position and only remove ‘)’ after that.
 * <p>
 * Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
 * The answer is: do the same from right to left.
 * However a cleverer idea is: reverse the string and reuse the code!
 * Here is the final implement in Java.
 * <p>
 * <p>
 * ****************************************************
 * Hindsight:
 * 1.StringBuilder可以对String进行逆序处理
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No301_Remove_Invalid_Parentheses {
    public static void main(String[] args) {
        No301_Remove_Invalid_Parentheses obj = new No301_Remove_Invalid_Parentheses();
//        obj.removeInvalidParentheses("((()");
        obj.removeInvalidParentheses("())");
    }


    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_left, int last_right, char[] pair) {
        for (int stack = 0, i = last_left; i < s.length(); ++i) {
            if (s.charAt(i) == pair[0]) stack++;
            else if (s.charAt(i) == pair[1]) stack--;
            //此处的判断就是一旦发现右边的括号少于左边的括号,就代表要进行切割处理了,切割的目标就是舍掉部分(其实就一个而已)右括号
            //目前为止遍历过的所有的右括号都有可能被被舍掉的括号,
            if (stack >= 0) continue;
            for (int j = last_right; j <= i; ++j)
                //Q4:如果第j个字符是右括号,且,它是问题字符或是它前面的字符不是右括号,那么就可以删掉进行重试了
                // j== last_j ->第一个要处理的字符
                // s.charAt(j - 1) != par[1])  -> j的前一个字符不是)
                if (s.charAt(j) == pair[1] && (j == last_right || s.charAt(j - 1) != pair[1]))
                    //Q2:切掉问题的部分,然后拼出来一个新的字符串.
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, pair);
            return;
        }
        //Q1:为什么要reverse呢?原因是为了保证左右括号是对称分布的.
        //字符串:((((()是不会得到处理的,一直是count>=0,所以也就是没有答案
        //所以要把少的那部分字符挪到右侧来,再测试一遍
        //当翻转后的字符串满足要求后,会在后面判断是否已经翻转,如果已经翻转,则直接将再次翻转(即复原)的字符串加入备选集中
        String reversed = new StringBuilder(s).reverse().toString();
        //Q3:逆序处理
        if (pair[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    public List<String> removeInvalidParentheses_slow(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }

            if (found) continue;

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                //bug1:产生全部的子字符串
                //opt:又因为在queue中不断地进行循环处理,所以即使是子字符串也会被循环处理,结果也是没有问题的
                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }

        return count == 0;
    }
}

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList();
        if (s == null || s.length() == 0) return res;
        remove(s, res, 0, 0, new char[]{'(', ')'});
        return res;

    }

    private void remove(String s, List<String> ans, int last_left, int last_right, char[] pair) {
        //count the number of '(' & ')'
        for (int stack = 0, i = last_left; i <= s.length(); i++) {
            if (s.charAt(i) == pair[0]) stack++;
            if (s.charAt(i) == pair[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_right; j <= i; j++) {
                if (s.charAt(j) == pair[1] && (j == last_right || s.charAt(j - 1) != pair[1])) {
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, pair);
                }
            }
            return;
        }

        String reversed = new StringBuilder(s).reverse().toString();
        if (pair[0] == 'c') {
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        } else {
            ans.add(reversed);
        }
    }
}