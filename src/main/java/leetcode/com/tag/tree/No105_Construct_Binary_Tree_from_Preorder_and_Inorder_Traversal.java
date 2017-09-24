package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/15/17.
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Thoughts:
 * 1. 看了答案,根据根在inorder数组中的位置,算出该根下有多少个节点,左节点个数为(position-inStart)
 *      -->进而得出直接左节点的位置,和直接右节点的位置(如果位置存在的话,否则会导致inStart>inEnd)
 * 2. 通过
 */
public class No105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) return null;

        return helper(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);

        int inorderPosition = findInorderPos(inorder, inStart, inEnd, preorder[preStart]);
        int leftNodeNumber = inorderPosition - inStart;
        root.left = helper(preorder, inorder, preStart + 1, inStart, inorderPosition - 1);
        root.right = helper(preorder, inorder, preStart + (leftNodeNumber + 1), inorderPosition + 1, inEnd);
        return root;
    }

    private int findInorderPos(int[] inorder, int start, int end, int goal) {
        for (int i = start; i <= end; i++)
            if (inorder[i] == goal) return i;
        return -1;
    }
}
