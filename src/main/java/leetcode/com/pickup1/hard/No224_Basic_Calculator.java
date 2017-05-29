package leetcode.com.pickup1.hard;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 7/16/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/basic-calculator/
 * ****************************************************
 * Description:
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
 * non-negative integers and empty spaces .
 * You may assume that the given expression is always valid.
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 * ****************************************************
 * Thoughts:
 * 1.逆波兰式?,只有+-(),然后,需要数字转换的方法
 * 2.可视化:先考虑没有括号的情况,假设数字转换模块已经封装,如果一定要体现括号的作用的话,就要用2个堆栈,一个存符号,一个存数字
 * ****************************************************
 * Hindsight:
 * Ref:https://segmentfault.com/a/1190000003796804
 * 很多人将该题转换为后缀表达式后（逆波兰表达式）求解，其实不用那么复杂。题目条件说明只有加减法和括号，由于加减法是相同顺序的，
 * 我们大可以直接把所有数顺序计算。难点在于多了括号后如何处理正负号。我们想象一下如果没有括号这题该怎们做：
 * 因为只有加减号，我们可以用一个变量sign来记录上一次的符号是加还是减，这样把每次读到的数字乘以这个sign就可以加到总的结果中了。
 * 有了括号后，整个括号内的东西可一看成一个东西，这些括号内的东西都会受到括号所在区域内的正负号影响（比如括号前面是个负号，
 * 然后括号所属的括号前面也是个负号，那该括号的符号就是正号）。但是每多一个括号，都要记录下这个括号所属的正负号，而每当一个括号结束，
 * 我们还要知道出来以后所在的括号所属的正负号。根据这个性质，我们可以使用一个栈，来记录这些括号所属的正负号。这样我们每遇到一个数，
 * 都可以根据当前符号，和所属括号的符号，计算其真实值。
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No224_Basic_Calculator {

    public int calculate(String s) {

        s = "(" + s + ")";
        return evaluate(s, new int[]{0});
    }

    private int evaluate(String s, int[] position) {
        int val = 0;
        int i = position[0];
        int sign = 1;
        int num = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            switch (c) {
                case '+':
                    val += sign * num;
                    num = 0;
                    sign = 1;
                    i++;
                    break;
                case '-':
                    val += sign * num;
                    num = 0;
                    sign = -1;
                    i++;
                    break;
                case '(':
                    position[0] = i + 1;
                    //bug3:忘记sign* evaluate啦
                    val += sign * evaluate(s, position);
                    i = position[0];
                    break;
                case ')':
                    position[0] = i + 1;
                    return val + sign * num;
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

    public int calculate_slow(String s) {
        //去掉所有的空格
        //bug1:去掉空格要重新赋值
        s = s.replace(" ", "");
        Stack<Integer> stack = new Stack<>();
        //先压入一个1进栈,可以理解为有个大括号在外面
        stack.push(1);
        int i = 0, res = 0, sign = 1;
        while (i < s.length()) {
            char c = s.charAt(i);
            //遇到正号,将当前的符号变为正号
            if (c == '+') {
                sign = 1;
                i++;
            } else if (c == '-') {
                sign = -1;
                i++;
                //遇到左括号,计算当前所属的符号,压入栈中
                //计算方法,当前符号乘以当前所属括号的符号
            } else if (c == '(') {
                stack.push(sign * stack.peek());
                sign = 1;
                i++;
                //遇到右括号,当前括号结束,出栈
            } else if (c == ')') {
                stack.pop();
                i++;
                // 遇到数字，计算其正负号并加入总结果中
            } else {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                res += num * sign * stack.peek();
            }

        }
        return res;

    }

}
