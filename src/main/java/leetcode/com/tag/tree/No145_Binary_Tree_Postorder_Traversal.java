package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by JianZhang on 9/13/17.
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [3,2,1].
 * <p>
 * Note: Recursive solution is trivial, could you do it iteratively?
 * Bugs:
 * 1. 完全fail,没有思路
 * 2. 借用原来的解法
 * <p>
 * 非递归的方法略难，需要curr和prev两个variable来作为指示，进行区分是否为兄弟伙父子关系，
 * (prev,left==curr||prev.right==curr):前一节点是否为当前节点的父亲？
 * (curr.left==prev)?当前节点是否为前一节点的父亲
 * 每次入栈，只push一个节点，每次先peek，当prev和curr为同一个结点的时候，再pop将结点加入结果集
 */
public class No145_Binary_Tree_Postorder_Traversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode curr = root;

        if (root == null) return rst;

        stack.push(root);

        while (!stack.isEmpty()) {
            curr = stack.peek();
            //traverse down the tree
            //此时的判断条件时,当前节点曾经是前一个被处理节点的左孩子或右孩子,则代表该节点还可以入栈。。。
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) {
                //traverse up the tree from the left
                if (curr.right != null) stack.push(curr.right);
            } else {
                rst.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }
        return rst;

    }
}
