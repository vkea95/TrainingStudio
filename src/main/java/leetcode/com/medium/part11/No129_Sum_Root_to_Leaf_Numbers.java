package leetcode.com.medium.part11;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/20.
 * Location:
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * *******************************************
 * Description:
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 * 1
 * / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 repres
 * *******************************************
 * Solution:
 * 使用递归完成。我们要做的是 把左右子树的path加起来。
 * base case: root是叶子节点时，直接计算path。我们用pre保留上一层计算出来的数字的值。那么到了当前根节点应该就是
 * 把上一层的值*10再加上root的值。
 */
public class No129_Sum_Root_to_Leaf_Numbers {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int pre) {
        if (root == null) return 0;

        int cur = pre * 10 + root.val;
        if (root.left == null && root.right == null) {
            return cur;
        }
        return dfs(root.left, cur) + dfs(root.right, cur);
    }
}
