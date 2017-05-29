package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;
import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/11/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * ****************************************************
 * Description:
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w
 * as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * _______3______
 * /              \
 * ___5__          ___1__
 * /      \        /      \
 * 6      _2       0       8
 * /  \
 * 7   4
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
 * since a node can be a descendant of itself according to the LCA definition.
 * ****************************************************
 * Thoughts:
 * 1.首先想到有3种遍历方式,前序,中序和后序,要从中选择一个的话,
 * 2.找到LCA就几种情况,
 * A.左右子树各返回一个,那么本身就是答案,
 * B.本节点就是一个目标节点,然后再左,右子树其中的一个返回为一个那么,本节点就是答案
 * C.问题来了,需要设计一个合理的数据机构来返回这个LCA(单节点还是双节点)
 * ****************************************************
 * Time: 45mins
 * Beats: 8%
 * Bug: 1
 * ****************************************************
 * Optimization:
 * 1.简单清楚明了啊啊啊,拷贝的旧答案啊
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No236_Lowest_Common_Ancestor_of_a_Binary_Tree {
    public static void main(String[] args) {
        No236_Lowest_Common_Ancestor_of_a_Binary_Tree obj = new No236_Lowest_Common_Ancestor_of_a_Binary_Tree();
        TreeNode root = new TreeNode(1);
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(3);
        root.left = p;
        root.right = q;
        obj.lowestCommonAncestor(root, p, q);
    }

    public TreeNode lowestCommonAncestor_opt(TreeNode root, TreeNode p, TreeNode q) {
        //如果找到某个节点,那么停止搜索 & 返回
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        //标准的LCA
        if (left != null && right != null) return root;

        return left == null ? right : left;

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> list = new ArrayList<>();
        lcaHelper(root, p, q, list);
        for (TreeNode node : list) System.out.println(node.val);

        return list.get(list.size() - 1);
    }


    private boolean lcaHelper_opt(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> list) {
        if (root == null) return false;
        boolean rootRst = false;

        if (root == p || root == q) {
            list.add(root);
            rootRst = true;
        }
        boolean leftRst = lcaHelper(root.left, p, q, list);
        boolean rightRst = lcaHelper(root.right, p, q, list);


        //bug1: 判断条件不正确,导致返回的结果不正确,这里需要处理2中情况,就是
        // A.本节点是目标节点,2个孩子中有一个是,
        // B.本节点不是,但2个孩子其中一个是
        //
        if (list.size() == 2) {
            //左右孩子
            if (leftRst && rightRst) {
                list.add(root);
                return true;
            } else if ((leftRst || rightRst) && rootRst) {
                list.add(root);
            }
        }


        return rootRst || leftRst || rightRst;

    }

    private boolean lcaHelper(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> list) {
        if (root == null || list.size() == 3) return false;
        boolean rootRst = false;
        boolean leftRst = lcaHelper(root.left, p, q, list);
        boolean rightRst = lcaHelper(root.right, p, q, list);

        if (root == p || root == q) {
            list.add(root);
            rootRst = true;
        }
        //bug1: 判断条件不正确,导致返回的结果不正确,这里需要处理2中情况,就是
        // A.本节点是目标节点,2个孩子中有一个是,
        // B.本节点不是,但2个孩子其中一个是
        if (list.size() == 2) {
            //左右孩子
            if (leftRst && rightRst) {
                list.add(root);
                return true;
            } else if ((leftRst || rightRst) && rootRst) {
                list.add(root);
                return true;
            }
        }

        return leftRst || rightRst || rootRst;


    }
}
