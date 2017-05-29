package leetcode.com.easy.part0;

import java.util.Stack;

/**
 * Created by jason on 2016/1/31.
 * Locations:
 * https://leetcode.com/problems/valid-parentheses/
 * ************************************************
 * Descriptions:
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * ************************************************
 * Solutions:
 * 考虑用栈来解决问题，但是在判断有效性的问题上要稍微费些判断力，也就是说开括号只管入栈，
 * 闭括号要判断前一个字符是否和自己匹配
 * ************************************************
 * Tips:
 * 1.用栈的非空表示括号是否成对出现
 * 2.String-->Char  String.toCharArray(), char -> String: String.valueOf(char)
 * ************************************************
 * Bugs：
 *
 */
public class No020_Valid_Parentheses_My_Submissions_Question {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return true;
        Stack<Character> stack = new Stack<>();

        for (Character c : s.toCharArray()) {
            if ("({[".contains(String.valueOf(c))) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && isValid(stack.peek(), c)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isValid(char c1, char c2) {
        return (c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']');
    }

}
