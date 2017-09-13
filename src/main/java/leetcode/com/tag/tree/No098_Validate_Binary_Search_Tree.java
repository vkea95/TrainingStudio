package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/10/17.
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * 2
 * / \
 * 1   3
 * Binary tree [2,1,3], return true.
 * Example 2:
 * 1
 * / \
 * 2   3
 * Binary tree [1,2,3], return false.
 * Thoughts:
 * 1. 递归遍历,只要违反大小限制条件就返回false
 * 2.
 * Bugs:
 * 1. 没有考虑到就是,右子树的左孩子要大于根节点,小于右子树
 */
public class No098_Validate_Binary_Search_Tree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
//        root.right = new TreeNode(3);
        No098_Validate_Binary_Search_Tree obj = new No098_Validate_Binary_Search_Tree();
       System.out.print( obj.isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
        return recursive(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }


    private boolean recursive(TreeNode root, long min, long max) {
        if (root == null) return true;

        if (root.val >= max || root.val <= min) return false;

        return recursive(root.right, root.val, max) && recursive(root.left, min, root.val);

    }
}
