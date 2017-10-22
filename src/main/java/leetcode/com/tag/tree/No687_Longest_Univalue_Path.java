package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 10/22/17.
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root.
 * Solutions:
 * 1. 类似于算最长路径,那个helper函数的用法,当时没有想到,需要记一下
 */
public class No687_Longest_Univalue_Path {

    //Credit:http://www.cnblogs.com/hellowooorld/p/7636155.html
    public int longestUnivaluePath(TreeNode root) {

        if (root == null) return 0;

        int leftPath = longestUnivaluePath(root.left);
        int rightPath = longestUnivaluePath(root.right);
        int sub = Math.max(leftPath, rightPath);
        return Math.max(sub, helper(root.left, root.val) + helper(root.right, root.val));
    }

    private int helper(TreeNode node, int value) {
        if (node == null || node.val != value) return 0;
        return 1 + Math.max(helper(node.left, node.val), helper(node.right, node.val));
    }
}
