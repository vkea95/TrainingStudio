package leetcode.com.medium.part11;

import java.util.Stack;

/**
 * Created by jason on 2016/3/29.
 * Location:
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * **************************************************
 * Description:
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * **************************************************
 * Solution:
 */
public class No150_Evaluate_Reverse_Polish_Notation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            if (str.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (str.equals("-")) {
                //bug2: 减法不适用于交换律，所以要缓存下
                int snd = stack.pop();
                stack.push(stack.pop() - snd);

            } else if (str.equals("*")) {
                stack.push(stack.pop() * stack.pop());

            } else if (str.equals("/")) {
                //bug1: 减法不适用于交换律，所以要缓存下
                int snd = stack.pop();
                stack.push(stack.pop() / snd);

            } else {
                stack.push(Integer.valueOf(str));
            }
        }
        return stack.pop();


    }
}
