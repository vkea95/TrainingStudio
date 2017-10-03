package leetcode.com.tag.bfs;

import java.util.*;

/**
 * Created by JianZhang on 10/1/17.
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * Solutions:
 * 1.
 */
public class No301_Remove_Invalid_Parentheses {

//    队列的方法,比较容易懂,就是穷举,新的方法会比较麻烦,
    public List<String> removeInvalidParentheses_stack(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == par[1]) stack--;
            if (s.charAt(i) == par[0]) stack++;
            if (stack >= 0) continue;
            //走到此处说明截止到第i个位置,)多余左括号,此时需要进行剪枝?,从last_j一直循环到i,
            for (int j = last_j; j <= i; ++j)
                //重点1:
                // && 后面的条件,是为了处理连续右括号的情况,即只从连续的右括号中抹掉一个进入递归处理就好了,
//                 j == last_j 表示的是,从上次截断的地方开始处理。所以是这样的两个条件。
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
//        重点3:
//        这次的处理表示的是,倒着处理,此次删除的是(
        if (par[0] == '(') // finished left to right
            //重点2:
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    //  Credit: http://www.cnblogs.com/yrbbest/p/5049891.html
//    于是搬过来借鉴。方法是我们每次去掉一个"("或者")"，然后把新的string加入到Queue里，继续进行计算。
// 要注意的是需要设置一个boolean foundResult，假如在这一层找到结果的话，我们就不再继续进行下面的for循环了。
// 这里应该还可以继续剪枝一下，比如记录当前这个结果的长度len，当queue里剩下的string长度比这个len小的话，我们不进行验证isValid这一步。
//    --->略慢
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        boolean foundResult = false;
        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                result.add(s);
                foundResult = true;
            }
            if (foundResult) continue;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '(' || c == ')') {
                    String t = s.substring(0, i) + s.substring(i + 1);
                    if (!visited.contains(t)) {
                        queue.offer(t);
                        visited.add(t);
                    }
                }
            }
        }

        return result;
    }


    private boolean isValid(String s) {
        int leftCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') leftCount++;
            else if (c == ')') leftCount--;
            if (leftCount < 0) return false;
        }

        return leftCount == 0;
    }

}
