package leetcode.com.tag.tree;

import edu.princeton.cs.algs4.In;
import leetcode.com.util.Interval;
import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by JianZhang on 9/10/17.
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * For example:
 * Given binary tree [1,null,2,3],
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,3,2].
 * Note: Recursive solution is trivial, could you do it iteratively?
 * Thoughts:
 * 1. 想到了stack,但是第一个解法,没有处理好无限循环的case,timelimited了,这个实在不应该
 * 2.  想清楚后,才明白需要不停地将当前节点及其左节点压栈,直至不能再压,然后出栈,然后取右节点,进入循环处理
 */
public class No094_Binary_Tree_Inorder_Traversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curt = root;
        while (curt != null || !stack.isEmpty()) {
            while (curt != null) {
                stack.push(curt);
                curt = curt.left;
            }
            curt = stack.pop();
            list.add(curt.val);
            curt = curt.right;
        }

        return list;
    }

    public List<Integer> inorderTraversal_zj_slowly(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node = root.left;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        while (!stack.isEmpty()) {
            node = stack.pop();
            list.add(node.val);

            node = node.right;
            if (node != null) {
                stack.push(node);
                node = node.left;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }

            }

        }
        return list;
    }
}
