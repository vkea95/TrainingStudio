package leetcode.com.easy.part1;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/13.
 * Location:
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * **************************************************************
 * Description:
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * **************************************************************
 * Solution:
 * 必须画图推导解决
 * **************************************************************
 * Tips：
 * 要能够想到叶子节点需要单独拿出来处理的啊啊啊啊啊啊啊
 */
public class No111_Minimum_Depth_of_Binary_Tree {



    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        return getMin_rec(root);
    }

    public int getMin_rec(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        if (root.left == null && root.right == null) {
            //返回长度 1,让长度处于可计算的状态。
            return 1;
        }
        int left = getMin_rec(root.left);
        int right = getMin_rec(root.right);
        return 1 + Math.min(left, right);
    }


    public int getMin(TreeNode root) {

        //Bug1: 处理应对只有一个子节点的状况，让为null的结点返回maxvalue,保证最短路径出自非null结点
        if (root == null) return Integer.MAX_VALUE;

        //bug2:需要对叶子节点单独处理，若某结点只有一个子节点的话，那么这个结点就还要继续搜索下去
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftDeep = getMin(root.left);
        int rightDeep = getMin(root.right);


        return 1 + Math.min(leftDeep, rightDeep);
    }
}
