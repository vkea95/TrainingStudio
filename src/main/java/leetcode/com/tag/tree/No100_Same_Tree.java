package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/13/17.
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
public class No100_Same_Tree {

    public static void main(String[] args) {
        No100_Same_Tree obj = new No100_Same_Tree();
        obj.isSameTree(new TreeNode(0), new TreeNode(0));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q==null) return true;
        if (p==null || q==null) return false;
//        if (p != q) return false; //bug1:不能用 != 判断两个object
//        if (p == null) return true;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
