package leetcode.com.medium.part02;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/11.
 * Location:
 * https://leetcode.com/problems/validate-binary-search-tree/
 * ************************************************************
 * Description:
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees
 * *************************************************************
 * Solution:
 * Traverse: 遍历的很巧妙很有意思，关键要标出最左子节点
 */
public class No098_Validate_Binary_Search_Tree {
    private int lastVal = Integer.MIN_VALUE;
    private boolean firstNode = true;

    private TreeNode firstEle = null;
    private TreeNode secondEle = null;

    public void recoverTree(TreeNode root) {
        recursive(root);
        int tmp = firstEle.val;
        firstEle.val = secondEle.val;
        secondEle.val = tmp;
    }

    private void recursive(TreeNode root) {
        if (root == null) return;
        recursive(root.left);
        if (!firstNode && firstEle == null && lastVal >= root.val) {
            firstEle = root;
        }
        if (firstEle != null && lastVal >= root.val) {
            secondEle = root;
        }
        firstNode = false;
        lastVal = root.val;
        recoverTree(root.right);


    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;


        if (!(isValidBST(root.left))) return false;
        if (!firstNode && lastVal >= root.val) return false;


        firstNode = false;
        lastVal = root.val;
        return isValidBST(root.right);
    }
}
