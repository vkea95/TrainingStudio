package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/13/17.
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * Thoughts:
 * 1. 完全没有想法,应该是没有看明白题目,搞清思路
 */
public class No101_Symmetric_Tree {
    public boolean isSymmetric(TreeNode root) {

        return (root == null) || helper(root.left, root.right);
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return (left.val == right.val) && helper(left.left, right.right) && helper(left.right, right.left);
    }

}
