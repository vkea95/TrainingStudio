package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

import java.util.*;

/**
 * Created by tclresearchamerica on 6/21/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * ****************************************************
 * Description:
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * For example:
 * Given binary tree [1,null,2,3],
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,3,2].
 * Recursive solution is trivial, could you do it iteratively?
 * ****************************************************
 * Time: 20 mins
 * Beats: failed
 * Bug:3
 * ****************************************************
 * Thoughts:
 * 其实就是一路向左,不断push,然后选择右侧,再一路向左,我把思路弄得复杂了
 * 所以,inorder的循环做法需要理解的是,一路向左,然后所有的树都是相似的,所以就这么干就好啦
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No094_Binary_Tree_Inorder_Traversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            list.add(stack.peek().val);
            root = stack.pop().right;
        }

        return list;
    }
    public List<Integer> inorderTraversal_ref(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        // inorderTraversalRecursive(root, result);
        inorderTraversalIterative(root, result);
        return result;
    }

    private void inorderTraversalIterative(TreeNode root, List<Integer> result) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.offerLast(curr);
                curr = curr.left;
            } else {
                result.add(stack.getLast().val);
                curr = stack.getLast().right;
                stack.pollLast();
            }
        }
    }

    private void inorderTraversalRecursive(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursive(root.left, result);
        result.add(root.val);
        inorderTraversalRecursive(root.right, result);
    }
}
