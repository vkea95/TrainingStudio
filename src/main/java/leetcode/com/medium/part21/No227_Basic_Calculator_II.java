package leetcode.com.medium.part21;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 4/26/16.
 * Location:
 * https://leetcode.com/problems/basic-calculator-ii/
 * ***************************************************
 * Description:
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero
 * You may assume that the given expression is always valid.
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * ***************************************************
 * Analysis:
 * 重复走了下自己设计实现的方式,发现要处理很多比较复杂的情况,到头来还是逆波兰式来的比较轻松
 * 自我分析:
 * 没有按照逆波兰式的思维方法进行思考和设置,走了不少弯路,耽误了很多时间.
 * 后来按照逆波兰式的想法进行实践,发现结果不正确,想来是缺乏必要的推导所致.
 * 耗时2.5小时
 * 效率太低啦!!!!!!!!
 * ***************************************************
 * Bugs:
 * 1.在做逆波兰式处理的时候,没有将相应的运算符入栈,导致结果不正确
 * 2.目前的想法是,对式子进行预处理,按照逆波兰式的方式将数据栈建立起来,结果式子建立后,发现结果不正确,最后的经过预处理的逆波兰式,
 * 里面是不含有* / 这样的符号,只有+ -,这样的符号,但是问题是按照栈的pop的方式,算出来的结果可能和正确结果为相反数,
 * 此时需要按照循环的方式,进行计算,即从栈的头部进行计算.看了网友的部分答案,才明白的啊.
 * ***************************************************
 * Points:
 * 原来栈也是可以进行Iterator进行遍历的,并非只有push和pop两种方式
 * ***************************************************
 * 网络答案:
 * 看了网上的答案,有些比较新鲜的做法,就是这个运算因为没有括号等操作,所以实践起来很简单,同时还可以将-和数字结合成负数,
 * 这样就只有+ * / 三个操作符了,可立即算出结果,
 */
public class No227_Basic_Calculator_II {

    public static void main(String[] args) {
        No227_Basic_Calculator_II obj = new No227_Basic_Calculator_II();
        System.out.println(obj.calculate("1-1+1"));
    }

    public int calculate_Web(String s) {
        if (s == null) return 0;
        String temp = s.replaceAll(" ", "");
        temp += "#";
        if (temp.length() == 0) return 0;

        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int number = 0;
        for (int i = 0; i < temp.length(); i++) {
            if (Character.isDigit(temp.charAt(i))) {
                //Valuable point: need to remember the ways
                number = 10 * number + temp.charAt(i) - '0';
            } else {

                if (sign == '+') {
                    stack.push(number);
                } else if (sign == '*') {
                    stack.push(number * stack.pop());
                } else if (sign == '/') {
                    stack.push(stack.pop() / number);
                } else if (sign == '-') {
                    stack.push(-number);
                } else {
                    stack.push(number);
                }
                sign = temp.charAt(i);
                number = 0;
            }

        }
        int result = 0;
        for (int n : stack)
            result += n;

        return result;

    }

    public int calculate(String s) {
        if (s == null) return -1;
        //bug1: remove the useless space with empty character
        String temp = s.replaceAll(" ", "");
        temp += "e";
        char[] array = temp.toCharArray();
        Stack<String> stack = new Stack<>();
        char operator = '#';
        int result = 0;
        //bug2:forget to setup a stringBuilder
        StringBuilder sb = new StringBuilder();
        for (char c : array) {
            if (c >= '0' && c <= '9') {
                sb.append(c);
            } else if (c == ' ') {

            } else {
                if (c == 'e' && operator == '#') {
                    stack.push(String.valueOf(sb.toString()));

                } else if (operator == '#') {
                    operator = c;
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                } else if (operator == '-') {
                    stack.push(String.valueOf(operator));
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                } else if (operator == '+') {
                    stack.push(String.valueOf(operator));
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                } else if (operator == '*') {
                    result = Integer.valueOf(stack.pop()) * Integer.valueOf(sb.toString());
                    sb = new StringBuilder();
                    stack.push(String.valueOf(result));
                } else if (operator == '/') {
                    result = Integer.valueOf(stack.pop()) / Integer.valueOf(sb.toString());
                    sb = new StringBuilder();
                    stack.push(String.valueOf(result));
                }

                if (c == 'e') {
                    result = Integer.parseInt(stack.get(0));
                    for (int i = 1; i < stack.size(); i++) {

                        if (stack.get(i).equals("+")) {
                            result += Integer.parseInt(stack.get(++i));
                        } else if (stack.get(i).equals("-")) {
                            result -= Integer.parseInt(stack.get(++i));
                        }
                    }

                }


                operator = c;
            }
        }


        return result;

    }
}
