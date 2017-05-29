package leetcode.com.easy.part1;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/13.
 * Location:
 * https://leetcode.com/problems/balanced-binary-tree/
 * ******************************************************
 * Description:
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees
 * of every node never differ by more than 1.
 * *******************************************************
 * Solution:
 * 左右子树的height差值大于1，则为非平衡二叉树，左右子树中有一棵非平衡二叉树，则整棵子树为非平衡二叉树
 */
public class No110_Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        if (Math.abs(left - right) > 1 || right == -1 || left == -1)
            return -1;

        return Math.max(left, right) + 1;
    }
}
