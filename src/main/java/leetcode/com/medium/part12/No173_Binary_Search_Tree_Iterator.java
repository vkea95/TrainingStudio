package leetcode.com.medium.part12;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by jason on 2016/3/30.
 * Location:
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * ***********************************************************
 * Description:
 * Implement an returnList over a binary search tree (BST). Your returnList will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * ***********************************************************
 * Solution:
 * 1. 解法真的好难想，还是曾经使用过的栈的用法，不断将当前节点压入栈，再取左节点，直到null，然后pop，将结点返回，
 * 再将该结点的右结点当做curt 结点
 */
public class No173_Binary_Search_Tree_Iterator {


    public class BSTIterator {
        TreeNode curt = null;
        Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            curt = root;
        }

        /**
         * @return whether we have a next smallest number
         */
        public boolean hasNext() {
            return (curt != null || !stack.isEmpty());
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            while (curt != null) {
                stack.push(curt);
                curt = curt.left;
            }
            curt = stack.pop();
            TreeNode node = curt;
            curt = curt.right;
            return node.val;
        }
    }

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
