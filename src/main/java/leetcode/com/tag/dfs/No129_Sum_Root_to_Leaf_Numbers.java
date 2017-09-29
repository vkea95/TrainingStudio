package leetcode.com.tag.dfs;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/27/17.
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
 */
public class No129_Sum_Root_to_Leaf_Numbers {
    public int sumNumbers(TreeNode root) {
//        return Integer.valueOf(helper(root));
        return helper(root, 0);
    }

    //    credit:http://www.cnblogs.com/yrbbest/p/4438737.html
//    求二叉树路径和。依然是DFS，leaf的左右子节点均为空。
    //    就是每次进去的时候,要把父亲的sum带进去再乘10
    private int helper(TreeNode root, int sum) {
        if (root == null) return 0;
        sum = sum * 10 + root.val;
        if (root.left == null && root.right == null) return sum;
        return helper(root.left, sum) + helper(root.right, sum);
    }

//以下的方法会在Integer.valueOf 的时候,发生溢出错误
//    private String helper(TreeNode root) {
//        if (root == null) return "";
//        StringBuilder lsb = new StringBuilder();
//        StringBuilder rsb = new StringBuilder();
//        lsb.append(root.val);
//        lsb.append(helper(root.left));
//        rsb.append(root.val);
//        rsb.append(helper((root.right)));
//
//
//        int sum = Integer.valueOf(lsb.toString()) + Integer.valueOf(rsb.toString());
//        return String.valueOf(sum);
//    }
}
