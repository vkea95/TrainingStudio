package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/1/31.
 * Locations:
 * http://www.jiuzhang.com/solutions/generate-parentheses/
 * *******************************************************
 * Descriptions:
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * *******************************************************
 * Solutions:
 * 递归解决，左右括号用left，right的数字来标识
 * *******************************************************
 * Tips：
 * 1.现在虽然有递归的基本思路了，但是实践起来还是没有办法完成细节
 * 2.左括号可以随时随便加，但是右括号必须在保证有左括号存在的时候，才能加，即left<right,否则输出的是非法括号等等
 */
public class No022_Generate_Parentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        helper(result, "", n, n);
        return result;
    }

    private void helper(List<String> result, String parent, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(parent);
            return;
        }
        if (left > 0) {
            helper(result, parent + "(", left - 1, right);
        }
        if (right > 0 && left < right) {
            helper(result, parent + ")", left, right - 1);
        }
    }
}
