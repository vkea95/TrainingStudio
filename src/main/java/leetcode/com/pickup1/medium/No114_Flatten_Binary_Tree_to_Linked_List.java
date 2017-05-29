package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 7/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * ****************************************************
 * Description:
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example,
 * Given
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * ****************************************************
 * Thoughts:
 * 非常典型的一个Stack处理方式,每次都把需要处理的节点,非空右节点push到栈中,处理完root后,递归调用左节点
 * 当碰到叶子节点的时候,就对堆栈进行处理
 * 有点tricky的是,如何对堆栈进行调用和处理,何时调用
 * ****************************************************
 * Time: 14 mins
 * Beat: 1%
 * Bug:0
 * ****************************************************
 * Hindsight:
 * 1.画图解决想象问题,然后,就是对于叶子节点,需要单独的处理方式,比如要给叶子节点指定右子树,但是又要判断stack非空
 * 2.第一遍的做法,更巧妙,用的是recursive,但是我估计做题的时候,想不到那么巧妙的方法,抄一遍就算熟悉好了
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No114_Flatten_Binary_Tree_to_Linked_List {
    private TreeNode lastNode = null;


    public void flatten(TreeNode root) {
        if (root == null) return;

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }

    public void flatten_slow(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return;
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) stack.push(node.right);
            if (node.left != null) {
                stack.push(node.left);
                node.right = node.left;
                node.left = null;
            } else {
                if (!stack.isEmpty()) node.right = stack.peek();
            }
        }


    }

    private void helper(TreeNode root) {

    }
}
