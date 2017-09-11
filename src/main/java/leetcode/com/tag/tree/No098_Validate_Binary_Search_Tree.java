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
 */
public class No098_Validate_Binary_Search_Tree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        No098_Validate_Binary_Search_Tree obj = new No098_Validate_Binary_Search_Tree();
        obj.isValidBST(root);
    }

    public boolean isValidBST(TreeNode root) {
        return recursive(root);
    }


    private boolean recursive(TreeNode root) {
        if (root == null) return true;

        if (root.left != null) {
            if (root.left.val >= root.val) return false;
        }
        if (root.right != null) {
            if (root.right.val <= root.val) return false;
        }
        return recursive(root.right) && recursive(root.left);

    }
}
