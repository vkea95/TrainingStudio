package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/25/17.
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
 * along the path equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class No112_Path_Sum {
    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root, sum);
    }

    private boolean helper(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.left == null && root.right == null) return  (sum-root.val) == 0;

        return helper(root.left, sum - root.val) || helper(root.right, sum - root.val);
    }
}
