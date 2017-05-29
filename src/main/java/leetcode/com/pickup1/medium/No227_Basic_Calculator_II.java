package leetcode.com.pickup1.medium;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 8/28/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/basic-calculator-ii/
 * ***************************************************************
 * Description:
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * ***************************************************************
 * Bug1:没有处理space,需要将space处理掉哦
 * Bug2:
 * "100000000/1/2/3/4/5/6/7/8/9/10"
 * ->原因是,没有在可以开始的时候,就进行计算,所以导致后面部分的计算结果为0,然后非法计算啦
 * Bug3:
 * "1-1+1"  --> output is -1
 * 原因在于计算的时候,还是没有遵从先计算的原则,将所有+/-操作都放到了最后再做处理,导致计算过程中,后面的优先进行了计算。
 * -->
 * 解法:将减法操作符当做 数字的符号 进行处理,就可以避免这个问题啦
 * ===>
 * 这2个bug,才是我关于自动计算处理这类问题的症结所在啊。
 * 首先,要建立起数字的符号的概念,也就是sign,这个sign要和数字关联,也要和计算绑定在一起
 * ***************************************************************
 * Time: 40 min
 * Beat: 87
 * Bug: 3
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No227_Basic_Calculator_II {
    public static void main(String[] args) {
        No227_Basic_Calculator_II obj = new No227_Basic_Calculator_II();
//        obj.calculate("1 + 1");
        obj.calculate("1-1+1");
    }

    public int calculate(String s) {
        s += "#";//结尾字符,保证最后一个数字被压入栈
        char[] chars = s.toCharArray();
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {  ///--->注意使用library提供的标准方法
                num = num * 10 + chars[i] - '0';
            } else if (chars[i] == ' ') {
                continue;
            } else {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '-') {
                    stack.push(num);
                } else {
                    stack.push(operate(stack.pop(), num, sign));
                }
                sign = chars[i];
                num = 0;
            }
        }
        int result = 0;
        for (int n : stack) {
            result += n;
        }
        return result;
    }

    public int calculate_wrong(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operators = new Stack<>();
        numStack.push(0);
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num = num * 10 + chars[i] - '0';
            } else if (chars[i] == ' ') {
                continue;
            } else {
                if (!operators.isEmpty()) {
                    if (operators.peek() == '*' || operators.peek() == '/') {
                        numStack.push(operate(numStack.pop(), num, operators.pop()));
                    } else {
                        numStack.push(num);
                    }
                } else {
                    numStack.push(num);
                }
                operators.push(chars[i]);

                num = 0;
            }
        }
        numStack.push(num);
        while (!operators.isEmpty()) {
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            numStack.push(operate(num1, num2, operators.pop()));
        }

        return numStack.pop();
    }

    private int operate(int number1, int number2, char operator) {
        if (operator == '+') {
            return number1 + number2;
        } else if (operator == '-') {
            return number1 - number2;
        } else if (operator == '*') {
            return number1 * number2;
        } else {
            return number1 / number2;
        }

    }
}
