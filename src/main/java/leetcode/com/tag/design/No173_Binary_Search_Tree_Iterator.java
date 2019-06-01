package leetcode.com.tag.design;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by JianZhang on 10/7/17.
 * Implement an returnList over a binary search tree (BST). Your returnList will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * Solutions:
 * 1. 中序遍历,但是如何把这个数据放到iterator中呢?好好想想,总是在这个地方断片儿
 * 2. 原来还是BST的中序非递归方式,即栈来实现
 */
public class No173_Binary_Search_Tree_Iterator {

    TreeNode curtNode;
    Stack<TreeNode> stack;

    public No173_Binary_Search_Tree_Iterator(TreeNode root) {
        curtNode = root;
        stack = new Stack<>();
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return (!stack.isEmpty() || curtNode != null);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        while (curtNode != null) {
            stack.push(curtNode);
            curtNode = curtNode.left;
        }
        curtNode = stack.pop();
        TreeNode node = curtNode;
        curtNode = curtNode.right;
        return node.val;
    }
}
