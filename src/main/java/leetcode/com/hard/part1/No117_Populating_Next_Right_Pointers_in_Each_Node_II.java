package leetcode.com.hard.part1;

import leetcode.com.util.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tcl on 2016/3/16.
 * Location:
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 * *********************************************************************************
 * Description:
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 * 1
 * /  \
 * 2    3
 * / \    \
 * 4   5    7
 * After calling your function, the tree should look like:
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \    \
 * 4-> 5 -> 7 -> NULL
 * ************************************************************
 * Solution:
 */
public class No117_Populating_Next_Right_Pointers_in_Each_Node_II {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        //bug2: don't the differences between offer and add
        //ref URL:https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html
        //The offer method inserts an element if possible, otherwise returning false.
        // This differs from the Collection.add method, which can fail to add an element only
        // by throwing an unchecked exception. The offer method is designed for use when failure is a normal,
        // rather than exceptional occurrence, for example, in fixed-capacity (or "bounded") queues.
//        The remove() and poll() methods remove and return the head of the queue. Exactly which element is removed
// fro m the queue is a function of the queue's ordering policy, which differs from implementation to implementation.

// The remove() and poll() methods differ only in their behavior when the queue is empty: the remove() method throws
// an exception, while the poll() method returns null.
//        The element() and peek() methods return, but do not remove, the head of the queue.
        //maybe the offer belong to queue interface
        //
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();

                //bug1: forget to judge the (size-1) and set null to next pointer
                node.next = i == size - 1 ? null : queue.peek();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

    }
}
