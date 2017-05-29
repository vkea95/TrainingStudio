package leetcode.com.medium.part11;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jason on 2016/3/25.
 * Solution:
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * *****************************************************************
 * Description:
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,2,3].
 * Note: Recursive solution is trivial, could you do it iteratively?
 * ******************************************************************
 * Solution:
 * 提示不让用递归，那么想想看就只有堆栈可以轻易替换递归了
 */
public class No144_Binary_Tree_Preorder_Traversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root == null) return rst;

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            rst.add(node.val);

            //bug:将root与node弄混
            if (node.right != null) stack.push(node.right);

            if (node.left != null) stack.push(node.left);
        }
        return rst;

    }
}
