package leetcode.com.medium.part21;

import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * Created by tclresearchamerica on 4/28/16.
 * Location:
 * https://leetcode.com/problems/implement-stack-using-queues/
 * ************************************************************************************
 * Description:
 * Implement the following operations of a stack using queues.
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Notes:
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size,
 * and is empty operations are valid.
 * Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque
 * (double-ended queue), as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 * <p>
 * ************************************************************************************
 * Analysis:
 * 想象2个队列,总是有一个为空,然后push的时候,push的时候push到空的队列中,然后,将另一个队列的数据再弄过来
 * ************************************************************************************
 */
public class No225_Implement_Stack_using_Queues {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
    }
}

class MyStack {

    LinkedList<Integer> queue1 = new LinkedList<>();
    LinkedList<Integer> queue2 = new LinkedList<>();


    // Push element x onto stack.
    public void push(int x) {
        if (queue1.isEmpty()) {
            push(queue1, queue2, x);
        } else {
            push(queue2, queue1, x);
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (queue1.isEmpty()) {
            queue2.pop();
        } else {
            queue1.pop();
        }

    }

    // Get the top element.
    public int top() {

        return queue1.isEmpty() ? queue2.peek() : queue1.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        //bug4:optimization:优化 精简代码
        return queue1.isEmpty() && queue2.isEmpty();
    }

    private void push(LinkedList<Integer> q1, LinkedList<Integer> q2, int x) {
        q1.push(x);
        Stream<Integer> stream = q2.stream();

        //bug1 : don't know how to use stream: q::push
        //bug3 : push --> offer
        stream.forEach(q1::offer);
        //bug2 : forget to clear the old one
        q2.clear();
    }
}