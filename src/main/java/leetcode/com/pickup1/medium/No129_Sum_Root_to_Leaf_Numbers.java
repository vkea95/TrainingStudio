package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 8/10/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * ****************************************************
 * Description:
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * For example,
 * <p>
 * 1
 * / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * <p>
 * Return the sum = 12 + 13 = 25.
 * ****************************************************
 * Thought:
 * 1.首先确定是先序遍历
 * 2.考虑设个全局遍历存储返回结果
 * 3.在每个根部的时候,就要对返回结果进行处理,每次都要讲跟至自身节点的值进行累计
 * ****************************************************
 * <p>
 * Bug: 1题目要求只计算叶子节点,所以非叶子节点就不必计算的
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No129_Sum_Root_to_Leaf_Numbers {
    private int sum;

    public int sumNumbers(TreeNode root) {
        this.sum = 0;

        helper(root, 0);
        return this.sum;
    }
//
//    public int sumNumbers(TreeNode root) {
//        return dfs(root, 0);
//    }

    public int dfs(TreeNode root, int pre) {
        if (root == null) return 0;

        int cur = pre * 10 + root.val;
        if (root.left == null && root.right == null) {
            return cur;
        }
        return dfs(root.left, cur) + dfs(root.right, cur);
    }

    private void helper(TreeNode root, int pre) {
        if (root == null) {
//                this.sum += pre;
            return;
        }

        //注意:叶子节点会加2次,所以必须要做一个处理,比如说结果除2,或者是只判断一次
        if (root.left == null && root.right == null) {
            helper(null, root.val + pre * 10);
            this.sum += root.val + pre * 10;
        } else {
            helper(root.left, root.val + pre * 10);
            helper(root.right, root.val + pre * 10);
        }

    }
}
