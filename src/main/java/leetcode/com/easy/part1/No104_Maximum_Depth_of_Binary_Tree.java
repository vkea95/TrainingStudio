package leetcode.com.easy.part1;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by jason on 2016/3/12.
 * Location:
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * **************************************************
 * Description:
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * **************************************************
 * Solution:
 * 使用标准的递归模板
 * **************************************************
 * Bugs：
 * 要设置临时变量，不要对左右两节点做重复扫描，要认识到递归函数的使用方法，它不是变量，没有记忆化搜索
 * **************************************************
 * Hindsight:
 * 9/17/16
 * 1.尝试进行非递归的做法,发现操作起来比较困难,原因在于使用stack进行状态操作的时候,如果用stack的size来进行计算的话,就会出错
 * **************************************************
 * **************************************************
 */
public class No104_Maximum_Depth_of_Binary_Tree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Integer.max(left, right);
    }


    public int maxDepth_rc(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Integer.max(left, right);
    }

    public int maxDepth_nonrecursive(TreeNode root) {
        if (root == null) return 0;

        int leftDeep = maxDepth_nonrecursive(root.left);
        int rightDeep = maxDepth_nonrecursive(root.right);

        return 1 + Math.max(leftDeep, rightDeep);

    }


}
