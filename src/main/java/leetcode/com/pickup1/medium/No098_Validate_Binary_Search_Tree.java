package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 10/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/validate-binary-search-tree/
 * ****************************************************
 * Description:
 * <p>
 * Assume a BST is defined as follows:
 * <p>
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
 * ****************************************************
 * Ref:
 * 复杂度
 * 时间 O(N) 空间 O(H) 递归栈空间
 * <p>
 * 思路
 * 递归的判断每个节点是否满足BST的要求，需要注意的是BST的要求不只是左节点小于根节点小于右节点，还有个隐含的条件是，
 * 左子树里所有节点都要小于根节点，而右子树里所有节点都要大于根节点。所以我们要把这个上限和下限代入递归中。
 * <p>
 * 注意
 * 这里的结构和不同的二叉树遍历一样，如果到空节点就返回，否则递归遍历左节点和右节点。唯一不同是加入了max和min，
 * 所以要在递归之前先判断是否符合max和min的条件。
 * 不用再额外判断左右节点和根节点的关系，因为我们加入了max和min，如果左节点大于根节点的话，下一轮就过不了max的检查，右节点和min同理。
 * <p>
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No098_Validate_Binary_Search_Tree {
    public static void main(String[] args) {
        No098_Validate_Binary_Search_Tree obj = new No098_Validate_Binary_Search_Tree();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(20);
        obj.isValidBST(root);
    }

    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    public boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;

        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

}
