package leetcode.com.easy.part1;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/2/24.
 * Location:
 * ******************************************
 * Description:
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 * ******************************************
 * Solution:
 * 递归调用，循环处理
 */
public class No100_Same_Tree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return check(p, q);
    }

    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        return (p == null || q == null) && (p.val != q.val) && check(p.left, q.left) && check(p.right, q.right);
    }

}
