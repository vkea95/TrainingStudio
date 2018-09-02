package leetcode.com.pickup1.easy;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 7/19/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/implement-queue-using-stacks/
 * ****************************************************
 * Description:
 * Implement the following operations of a queue using stacks.
 * <p>
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and
 * is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a indexList or
 * deque (double-ended queue), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 * ****************************************************
 * Tims: 15 mins
 * Beat: 36%
 * Bug: 0
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No232_Implement_Queue_using_Stacks {
}

class MyQueue {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    // Push element x to the back of queue.
    public void push(int x) {
        if (stack1.isEmpty()) {
            swap(stack2, stack1);
            stack2.push(x);
            swap(stack1, stack2);

        } else {
            swap(stack1, stack2);
            stack2.push(x);
            swap(stack2, stack1);

        }
    }

    private void swap(Stack<Integer> s1, Stack<Integer> s2) {
        while (!s1.isEmpty())
            s2.push(s1.pop());
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (stack2.isEmpty()) stack1.pop();
        else stack2.pop();
    }

    // Get the front element.
    public int peek() {
        if (stack2.isEmpty()) return stack1.peek();
        else return stack2.peek();

    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack2.isEmpty() && stack1.isEmpty();

    }
}