package leetcode.com.medium.part21;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 4/27/16.
 * Location:
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * ******************************************************************************
 * Description:
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w
 * as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * _______6______
 * /              \
 * ___2__          ___8__
 * /      \        /      \
 * 0      _4       7       9
 * /  \
 * 3   5
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2,
 * since a node can be a descendant of itself according to the LCA definition.
 * ******************************************************************************
 * Analysis:
 * BST是有序分布,所以LCA的特征该是val位于p和q的中间,
 * 根据分析就是
 * 1.如果root.val大于两个节点,那么就到左子树取搜索,
 * 2.如果root.val小于两个节点,那么就到右子树搜索
 * 3.其余情况,就直接返回该节点即可.
 */
public class No236_Lowest_Common_Ancestor_of_a_Binary_Search_Tree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root.val>q.val && root.val>p.val){
            return lowestCommonAncestor(root.left,p,q);
        }else if(root.val<p.val && root.val<q.val) {
            return lowestCommonAncestor(root.right,p,q);

        }
        return root;
    }
}
