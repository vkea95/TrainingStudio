package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/25/17.
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Solutions:
 * 1. 一定要找到叶子节点的路径长度,其余的只有一个分支的节点,可以通过Integer.MAX_VALUE来进行过滤
 */
public class No111_Minimum_Depth_of_Binary_Tree {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;


        return helper(root);
    }

    private int helper(TreeNode root) {

        if (root == null) return Integer.MAX_VALUE;

        if (root.left == null && root.right == null) return 1;

        int left = helper(root.left);
        int right = helper(root.right);


        return 1 + Math.min(left, right);
    }
}
