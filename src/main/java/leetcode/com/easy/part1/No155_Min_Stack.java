package leetcode.com.easy.part1;

import java.util.Stack;

/**
 * Created by jason on 2016/1/17.
 * Locations:
 * https://leetcode.com/problems/min-stack/
 * http://www.jiuzhang.com/solutions/min-stack/
 * ********************************************************
 * Descriptions:
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack
 */
public class No155_Min_Stack {

    private Stack<Integer> minStack = new Stack<>();
    private Stack<Integer> stack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(minStack.peek(), x));
        }
    }

    public void pop() {
        minStack.pop();
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
