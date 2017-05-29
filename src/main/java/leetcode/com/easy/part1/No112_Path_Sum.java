package leetcode.com.easy.part1;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/13.
 * Location：
 * http://www.jiuzhang.com/solutions/path-sum/
 * *********************************************
 * Description：
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
 * along the path equals the given sum.
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
 * ************************************************
 * Solution:
 * 递归，然后需要将sum作为参数传进去。若leafNode（左右子树为null）的val等于传进来的sum则返回为true
 */
public class No112_Path_Sum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        if (root.left == null && root.right == null)
            return sum == root.val;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
