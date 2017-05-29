package leetcode.com.medium.part21;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 4/27/16.
 * *************************************************************************
 * Analysis
 * *************************************************************************
 * Solution:
 * 网络答案2:假定节点存在于树中,那么可以简化算法.代码超简单啊,二叉树的理解还要进一步的加强啊啊啊-_-!!
 * *************************************************************************
 */
public class No236_Lowest_Common_Ancestor_of_a_Binary_Tree_II {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果找到某个节点,那么停止搜索 & 返回
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //标准的LCA
        if (left != null && right != null) return root;

        return left == null ? right : left;

    }
}
