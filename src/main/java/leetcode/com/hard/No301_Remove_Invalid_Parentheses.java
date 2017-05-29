package leetcode.com.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/26/16.
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
 * Thoughts:
 * 1.the minimum number of invalid parentheses->如果挪出一个括号后,可以完成任务,那么就挪一个好了.
 * 2.问题转化的思考,DFS?BFS?Stack?
 * 3.根据copy的答案来讲,选用的是DFS
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
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
        obj.removeInvalidParentheses("()(()))");
    }

    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j)
                //Q4:如果第j个字符是右括号,且,它是问题字符或是它前面的字符不是右括号,那么就可以删掉进行重试了
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    //Q2:切掉问题的部分,然后拼出来一个新的字符串.
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        //Q1:为什么要reverse呢?原因是为了保证左右括号是对称分布的.
        String reversed = new StringBuilder(s).reverse().toString();
        //Q3:逆序处理
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }
}
