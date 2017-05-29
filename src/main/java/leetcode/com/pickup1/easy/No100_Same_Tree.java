package leetcode.com.pickup1.easy;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 9/5/16.
 */
public class No100_Same_Tree {
    public static void main(String[] args) {
        No100_Same_Tree obj = new No100_Same_Tree();
        TreeNode p = new TreeNode(10);
        p.left = new TreeNode(5);
        p.right = new TreeNode(15);
        TreeNode q = new TreeNode(10);
        q.left = new TreeNode(5);
        q.right = new TreeNode(15);


    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        //bug2:需要加入s1.isEmpty(),否则在处理叶子节点的时候,会出现问题
        while ((p != null || q != null || !s1.isEmpty())) {
            if (p == null && q == null) {
                //bug1:叶子节点会不断地压入栈中，进入无限循环
                // p=s1.pop();
                // q=s2.pop();
                p = s1.pop().right;
                q = s2.pop().right;
            } else if (p == null || q == null) {

                return false;
            } else {
                if (p.val != q.val) return false;
                s1.push(p);
                s2.push(q);
                p = p.left;
                q = q.left;
            }
        }
        return true;
    }

    public boolean isSameTree_rec(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        if (isSameTree(p.left, q.left)) return isSameTree(p.right, q.right);
        else return false;

    }
}
