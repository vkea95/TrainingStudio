package leetcode.com.medium.part21;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 4/14/16.
 * Location:
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * ********************************************************
 * Description:
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level
 * are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * ********************************************************
 * Analysis:
 * Steps to solve this problem:
 * 1) get the height of left-most part
 * 2) get the height of right-most part
 * 3) when they are equal, the # of nodes = 2^h -1
 * 4) when they are not equal, recursively get # of nodes from left&right sub-trees
 * ********************************************************
 * Hints:
 * 1.计算2的n次方,可以用<<操作符来实现了,但要注意-1操作
 * 2.在计算二叉树的节点个数的时候,一定要先知道树的高度
 */
public class No222_Count_Complete_Tree_Nodes {
    public static void main(String[] args) {
        System.out.println("2<<2" + (2 << 2));
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int left = getLeftHeight(root);
        int right = getRightHeight(root);
        if (right == left) {
            //bug1:不知道2<<(left-1)为啥-1
            return (2 << (left - 1)) - 1;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }

    public int getLeftHeight(TreeNode n) {
        if (n == null) return 0;
        int height = 1;
        while (n.left != null) {
            height++;
            n = n.left;
        }
        return height;
    }

    public int getRightHeight(TreeNode n) {
        if (n == null) return 0;

        int height = 1;
        while (n.right != null) {
            height++;
            n = n.right;
        }
        return height;
    }
}
