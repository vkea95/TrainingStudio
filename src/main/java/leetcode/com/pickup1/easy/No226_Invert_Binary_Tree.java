package leetcode.com.pickup1.easy;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 6/29/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/invert-binary-tree/
 * ****************************************************
 * Description:
 * Invert a binary tree.
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * to
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 * Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a
 * whiteboard so fuck off.
 * ****************************************************
 * Time: 10 mins:
 * Beat: 18%
 * Bug:0
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No226_Invert_Binary_Tree {

    public TreeNode invertTree(TreeNode root) {
        helper(root);
        return root;
    }

    private void helper(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        helper(root.left);
        helper(root.right);
    }
}
