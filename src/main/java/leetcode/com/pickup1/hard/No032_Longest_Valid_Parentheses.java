package leetcode.com.pickup1.hard;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 6/19/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/longest-valid-parentheses/
 * ****************************************************
 * Description:
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 * parentheses substring.
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 * ****************************************************
 * Thoughts:
 * 这是个经典问题:
 * 1.需要一个判断条件,即一旦确认是无效的括号组合,则重新开始计算.有一种情况,即右括号出现,但是无法配对,则需要重新计算,否则都可以算是合法的
 * 2.如何判断是合法的括号呢,即左右匹配呗
 * 3.因为是需要处理成字符,所以需要String转化成字符数组的方法s.toCharArray().
 * 4.关于数据结构:选择栈是比较合理的,因为我们需要动态调整solution方案,所以选择栈
 * 5.扫描一遍只能得到有效匹配的pair,但是找不到最长的连续括号.所以需要再次扫描一个data structure,获取连续括号的集合
 * ****************************************************
 * Bug:TLE,问题出现在判断条件的选择上,没有抽象出比较合适的判断条件,人家的做法,比我高级在对方直接推送位置入栈,然后通过计算便宜量,
 * 算长度,这里需要区分栈是否为空的情况.如果为空的话,有两种可能,一种是需要重新计算,另一种是需要继续计算
 * bug2:按照 网络答案来搞的话,也是TLE,怀疑是在数组化得时候出了问题
 * 后来发现,即使在逻辑完全一样的情况下,只有按照目前的将部分变量放入循环内进行初始化方式,才可以避免TLE问题,不清楚是为什么
 * ****************************************************
 * Time: 80 mins
 * Beats: 40%
 * Bug:4
 * ****************************************************
 * KeyPoint:
 * 1按照下标计算长度,直接明了,需要特殊处理stack为空的情况
 * ****************************************************
 * ****************************************************
 */
public class No032_Longest_Valid_Parentheses {
    public static void main(String[] args) {
        No032_Longest_Valid_Parentheses obj = new No032_Longest_Valid_Parentheses();
        obj.longestValidParentheses("()()");
    }

    public int longestValidParentheses(String s) {
        int max = 0, accumedLen = 0;
        Stack<Integer> stack = new Stack<>();
//        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    //Optimization
                    if (accumedLen > 0) {

                        accumedLen = 0;
                    }
                } else {
                    //bug3:forget to add the 1
                    int matchedPos = stack.pop();
                    int solutionLen = i - matchedPos + 1;
                    //bug4:forget to accumulate the value of accumedlen

                    if (stack.isEmpty()) {
                        accumedLen += solutionLen;
                        solutionLen = accumedLen;
                    } else {
                        solutionLen = i - stack.peek();
                    }
                    max = max > solutionLen ? max : solutionLen;
                }
            }
        }
        return max;

    }


    public int longestValidParentheses_TLE(String s) {
        int solution = 0, max = 0;
        Stack<Character> stack = new Stack<>();
        Stack<Integer> validCnt = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty()) {
                    validCnt.push(-1);
                } else if (stack.peek() == '(') {
                    solution = 0;
                    stack.pop();
                    while (validCnt.peek() > 0) {
                        solution += validCnt.pop();
                    }
                    validCnt.pop();
                    solution += 2;
                    //bug1:没有处理合并两个连续的括号序列的状况
                    while (!validCnt.isEmpty() && validCnt.peek() > 0) {
                        solution += validCnt.pop();
                    }
                    validCnt.push(solution);
                }
            } else if (c == '(') {
                stack.push(c);
                validCnt.push(0);
            }

        }
        while (!validCnt.isEmpty()) {
            solution = validCnt.pop();
            max = max > solution ? max : solution;
        }
        return max;
    }
}
