package leetcode.com.easy;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 7/16/16.
 * ************************************************
 * Location:
 * https://leetcode.com/problems/same-tree/
 * ************************************************
 * Description:
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 * ************************************************
 * Time: 10 mins
 * Beat:11%
 * Bug: 0
 * ************************************************
 * Hindsight:
 * 如果两个根节点一个为空，一个不为空，或者两个根节点值不同，说明出现了不一样的地方，返回假。如果两个节点都是空，则是一样的，返回真。
 * 然后我们再递归它们的左右节点，如果递归结果中一个或两个是假，就返回假。否则，说明左右子树都是完全一样的。
 * ************************************************
 * ************************************************
 * ************************************************
 * ************************************************
 * ************************************************
 * ************************************************
 */
public class No100_Same_Tree {
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            if (p.val != q.val) return false;
            else return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else return false;
    }
}
