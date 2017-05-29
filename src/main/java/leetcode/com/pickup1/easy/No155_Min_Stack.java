package leetcode.com.pickup1.easy;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 6/4/16.
 * ****************************************************
 * Time: 6 mins
 * Beats: 40%
 * Bug: 1
 * ****************************************************
 * Thoughts:借鉴了别人的想法,只将必要的min放入stack,所以去stack的时很快
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No155_Min_Stack {
}

class MinStack {

    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;

    public MinStack() {

    }

    public void push(int x) {
        //bug1: x<=min就必须入栈,否则只用x<min会导致处理重复元素时候,返回的结果不正确.
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.peek() == min) {
            stack.pop();
            min = stack.pop();
        } else stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}