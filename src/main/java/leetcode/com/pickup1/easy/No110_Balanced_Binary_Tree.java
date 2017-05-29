package leetcode.com.pickup1.easy;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 7/16/16.
 * ************************************************
 * Location:
 * https://leetcode.com/problems/balanced-binary-tree/
 * ************************************************
 * Description:
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees
 * of every node never differ by more than 1.
 * ************************************************
 * Thought:
 * 1.就是判断2个叶子节点间的高度是否超过1
 * 非平衡的条件是有两个叶子节点的深度相差大于1，最直接的想法就是把左子树和右子树的高度都算出来，
 * 如果相差大于1则说明不是平衡的。在递归中，从叶子结点开始一层层返回高度，叶子结点是1。我们返回-1代表非平衡，返回自然数代表有效的子树高度。
 * ************************************************
 * Time: 20 mins
 * Beat: 23%
 * Bug:1
 * ************************************************
 * Hindsight:
 * 理解基本的想法,但是没有办法将它付诸于代码程度,原来是想得,在叶子节点,进行比较,其实在根节点比较会更好,
 * 还是要打破假设,寻找最佳解法啦
 * ************************************************
 * ************************************************
 * ************************************************
 */
public class No110_Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        return helper(root) != -1;
    }

    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);

        if (Math.abs(left - right) > 1 || left == -1 || right == -1) {
            return -1;
        }

        //bug1:应该选取left和right中的max
        return Math.max(left, right) + 1;

    }
}
