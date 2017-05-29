package leetcode.com.pickup1.easy;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 7/16/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/path-sum/
 * ****************************************************
 * Description:
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
 * along the path equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree and sum = 22,              5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * ****************************************************
 * Thoughts:
 * 1.先序遍历
 * 2.
 * ****************************************************
 * Time: 20 mins
 * Beat: 1%
 * Bug:2
 * ****************************************************
 * Hindsight:
 * 1.没有搞清楚叶子节点的所指,即题目弄明白了,但是在代码程度上的体现依然不足啊,叶子节点是指左右子树都为空,
 * 2.递归算法中,只要递归就可以了,因为用到了||操作,所以即使一个节点返回false,程序依然会递归调用下去,直到结束,或是返回为true
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No112_Path_Sum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return sum == root.val;

        //此处保证只要有个叶子节点的值满足条件,即为真,就可以返回啦
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public boolean hasPathSum_zj(TreeNode root, int sum) {
        if (root == null) return false;
        return helper(root, sum);

    }

    public boolean helper(TreeNode root, int sum) {
        //bug1:root==nul 不是判断叶子节点的唯一标准,如果一个root,有左节点,没有右节点,那么到它的左右节点的时候,根据root==null就要判断啦
        //但实际上是不正确的,还是要根据左右节点是否为空来判断
//        if (root == null) {
//            if (sum == 0) return true;
//            else return false;
//        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        if (root.left != root && helper(root.left, sum - root.val))
            return true;
        if (root.right != root && helper(root.right, sum - root.val))
            return true;
        return false;

    }


}
