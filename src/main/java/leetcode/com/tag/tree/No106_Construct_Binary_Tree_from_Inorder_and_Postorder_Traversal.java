package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/25/17.
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * Bugs:
 * 1. 如果套用105的做法,那么就必须要单独处理这个postorder的start和end位置
 */
public class No106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    int postEnd = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postEnd = postorder.length - 1;
        return helper(inorder, postorder, 0, inorder.length - 1);
    }


    private TreeNode helper(int[] inorder, int[] postorder, int inStart, int inEnd) {
        if (inStart > inEnd) return null;

        TreeNode node = new TreeNode(postorder[postEnd--]);

        int positon = findIndex(inorder, inStart, inEnd, node.val);

        node.right = helper(inorder, postorder, positon + 1, inEnd);
        node.left = helper(inorder, postorder, inStart, positon - 1);

        return node;
    }

    private int findIndex(int[] inorder, int start, int end, int target) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == target) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        No106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal obj = new No106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal();
        obj.buildTree(new int[]{1, 3, 2}, new int[]{3, 2, 1});
    }
}
