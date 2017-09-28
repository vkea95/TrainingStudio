package leetcode.com.tag.dfs;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by JianZhang on 9/27/17.
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * 2
 * / \
 * 1   3
 * Binary tree [2,1,3], return true.
 * Example 2:
 * 1
 * / \
 * 2   3
 * Binary tree [1,2,3], return false.
 * Credit:http://www.cnblogs.com/yuzhangcmu/p/4177047.html
 */
public class No098_Validate_Binary_Search_Tree {

    //1.采用栈的话，先寻找最左边的节点，把经过的节点都存入栈中，第一个被弹出来的为最左节点，
    // 那么访问其右子树，对右子树也像前面一样遍历，整个流程跟递归一样。
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curt = root;

        TreeNode pre = null;

        while (true) {
            //每个根节点的左节点循环压栈
            while (curt != null) {
                stack.push(curt);
                curt = curt.left;

            }

            if (stack.isEmpty()) {
                break;
            }

            curt = stack.pop();

            //先序遍历的结果,就是val小的值首先被搞出来
            //但是此时要判断下pre是否为空,因为首次操作时候,是没有pre的实体
            if (pre != null && pre.val >= curt.val) {
                return false;
            }

            pre = curt;
            //开始搞右侧的节点
            curt = curt.right;

        }
        return true;
    }

    //我们可以设置上下bound，递归左右子树时，为它们设置最大值，最小值，并且不可以超过。
    public boolean isValidBST_recursive_bound(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs(TreeNode root, long lowBound, long upBound) {
        if (root == null) return true;
        if (root.val >= upBound || root.val <= lowBound) return false;

        return dfs(root.left, lowBound, root.val) && dfs(root.right, root.val, upBound);
    }
}
