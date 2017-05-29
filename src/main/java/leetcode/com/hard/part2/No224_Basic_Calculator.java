package leetcode.com.hard.part2;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 4/26/16.
 * Location:
 * https://leetcode.com/problems/basic-calculator/
 * ************************************************
 * Description:
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
 * non-negative integers and empty spaces .
 * <p>
 * You may assume that the given expression is always valid.
 * <p>
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 * ************************************************
 * Solutions:
 * 1.网络答案:
 * 想法 awesome!, 递归的方式只处理括号里面的东西,用数组留存递归走过的位置
 * 2.我认为interviewer想考的还是操作符的优先级顺序,逆波兰式的操作方法
 * 此题的C++解释:
 * http://www.rudy-yuan.net/archives/193/
 * ************************************************
 * ************************************************
 * ************************************************
 * ************************************************
 */
public class No224_Basic_Calculator {

    public static void main(String[] args) {
        No224_Basic_Calculator obj = new No224_Basic_Calculator();
        obj.calculate_fastest("1+1+2");
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(s.charAt(i))) {
                //c-'0'不用显示的转换为int类型,取消强制转换后,效率提高3%
                number = 10 * number +  (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

            }
        }
        if (number != 0) result += sign * number;
        return result;
    }


    public int calculate_fastest(String s) {
        if (s.length() == 0) return 0;
        s = "(" + s + ")";
        int[] p = {0};
        return eval(s, p);
    }

    // calculate value between parentheses
    private int eval(String s, int[] p) {
        int val = 0;
        int i = p[0];
        int oper = 1; //1:+ -1:-
        int num = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            switch (c) {
                case '+':
                    val = val + oper * num;
                    num = 0;
                    oper = 1;
                    i++;
                    break;// end of number and set operator
                case '-':
                    val = val + oper * num;
                    num = 0;
                    oper = -1;
                    i++;
                    break;// end of number and set operator
                case '(':
                    p[0] = i + 1;
                    val = val + oper * eval(s, p);
                    i = p[0];
                    break; // start a new eval
                case ')':
                    p[0] = i + 1;
                    return val + oper * num; // end current eval and return. Note that we need to deal with the last num
                case ' ':
                    i++;
                    continue;
                default:
                    num = num * 10 + c - '0';
                    i++;
            }
        }
        return val;
    }

}
