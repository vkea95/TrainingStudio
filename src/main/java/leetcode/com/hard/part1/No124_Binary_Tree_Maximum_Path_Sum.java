package leetcode.com.hard.part1;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/17.
 * Location:
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * ************************************************************
 * Description:
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.
 * For example:
 * Given the below binary tree,
 * 1
 * / \
 * 2   3
 * Return 6.
 * **************************************************************
 * Solution(http://www.cnblogs.com/yuzhangcmu/p/4172855.html):
 * 计算树的最长path有2种情况：
 * 1. 通过根的path.
 * (1)如果左子树从左树根到任何一个Node的path大于零，可以链到root上
 * (2)如果右子树从右树根到任何一个Node的path大于零，可以链到root上
 * 2. 不通过根的path. 这个可以取左子树及右子树的path的最大值。
 * 所以创建一个inner class:
 * 记录2个值：
 * 1. 本树的最大path。
 * 2. 本树从根节点出发到任何一个节点的最大path.
 * 注意，当root == null,以上2个值都要置为Integer_MIN_VALUE; 因为没有节点可取的时候，是不存在solution的。以免干扰递归的计算
 */
public class No124_Binary_Tree_Maximum_Path_Sum {
    private class ResultType {
        //singlePath: 从root往下走到任意点的最大路径，它可以不包含任何点
        //maxPath:从树中任意到任意点的最大路径，它至少包含一个点
        int singlePath, maxPath;

        ResultType(int singlePath, int maxPath) {
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    public ResultType helper(TreeNode root) {
        if (root == null) return new ResultType(0, Integer.MIN_VALUE);

        // Divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        // Conquer
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        //bug1: forget to max(singlePath, 0) --->有时singlePath为负值 需要单独处理哦
        singlePath = Math.max(singlePath, 0);
        //bug2:forget to count left and right
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
        return new ResultType(singlePath, maxPath);
    }


    public int maxPathSum(TreeNode root) {
        ResultType result = helper(root);
        return result.maxPath;
    }
}
