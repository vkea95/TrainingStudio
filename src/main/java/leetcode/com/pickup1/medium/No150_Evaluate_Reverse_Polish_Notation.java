package leetcode.com.pickup1.medium;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 6/8/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * ****************************************************
 * Description:
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * ****************************************************
 * Thoughts:
 * 完全不记得做过这个题目
 * 看这个样子只能是用栈了,估计里面要放个东西才好
 * ****************************************************
 * Stack solution:
 * Time: 10 mins
 * Beats: 60%
 * Bug: 0
 * Stack array solution:
 * Time:-
 * Beats:90%
 * Bug:-
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No150_Evaluate_Reverse_Polish_Notation {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE).length());
    }
    public int calc(int op1, int op2, String operator) {
        switch (operator) {
            case "/":
                return op1 / op2;
            case "*":
                return op1 * op2;
            case "+":
                return op1 + op2;
            default:
                return op1 - op2;
        }
    }

    public static boolean isOperator(String item) {
        return (item.equals("/") || item.equals("*") || item.equals("+") || item.equals("-"));
    }

    public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = -1;

        for (int i = 0; i < tokens.length; ++i) {
            String item = tokens[i];
            if (isOperator(item)) {
                int op2 = stack[top--];
                int op1 = stack[top--];
                stack[++top] = calc(op1, op2, item);
            } else {
                stack[++top] = Integer.parseInt(item);
            }
        }

        return stack[top--];
    }

    public int evalRPN_slow(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        int first, second, result;
        for (String token : tokens) {
            switch (token) {
                case "+":
                    result = stack.pop() + stack.pop();
                    stack.push(result);
                    break;
                case "-":
                    first = stack.pop();
                    second = stack.pop();
                    result = second - first;
                    stack.push(result);
                    break;
                case "*":
                    result = stack.pop() * stack.pop();
                    stack.push(result);
                    break;
                case "/":
                    first = stack.pop();
                    second = stack.pop();
                    result = second / first;
                    stack.push(result);
                    break;
                default:
                    stack.push(Integer.valueOf(token));

            }

        }
        return stack.pop();
    }
}
