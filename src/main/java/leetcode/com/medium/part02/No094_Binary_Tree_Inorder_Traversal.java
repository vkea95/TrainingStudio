package leetcode.com.medium.part02;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jason on 2016/3/11.
 * Location:
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * **************************************************************
 * Description:
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,3,2].
 * Note: Recursive solution is trivial, could you do it iteratively?
 * *******************************************************************
 * Solution:
 * inOrder即中序遍历，左子树-》根-》右子树
 * 用堆栈来实现，treeNode不断的取left，同时压栈，直到其为null，然后出栈，放入结果集，取其右子树做同样处理
 * 栈这种数据结构，刚好作为记录来使用，那么后序遍历估计也没问题
 */
public class No094_Binary_Tree_Inorder_Traversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curt = root;
        while(curt!=null || !stack.empty()){
            while(curt!=null){
                stack.push(curt);
                curt=curt.left;
            }
            curt=stack.pop();
            rst.add(curt.val);
            curt = curt.right;

        }
        return rst;
    }
}
