package leetcode.com.easy.part2;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 4/28/16.
 * Location:
 * https://leetcode.com/problems/invert-binary-tree/
 * ****************************************************************************
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
 * ****************************************************************************
 */
public class No226_Invert_Binary_Tree {
    public TreeNode invertTree(TreeNode root) {
        invertHelper(root);
        return root;
    }

    private void invertHelper(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertHelper(root.left);
        invertHelper(root.right);
    }
}
