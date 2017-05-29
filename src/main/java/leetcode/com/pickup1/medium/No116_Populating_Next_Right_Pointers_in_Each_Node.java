package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeLinkNode;
import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 7/23/16.
 * *****************************************************************************
 * Location:
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * *****************************************************************************
 * Description:
 * Given a binary tree
 * struct TreeLinkNode {
 * TreeLinkNode *left;
 * TreeLinkNode *right;
 * TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer
 * should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two
 * children).
 * For example,
 * Given the following perfect binary tree,
 * 1
 * /  \
 * 2    3
 * / \  / \
 * 4  5  6  7
 * After calling your function, the tree should look like:
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \  / \
 * 4->5->6->7 -> NULL
 * Subscribe to see which companies asked this question
 * *****************************************************************************
 * Thoughts:
 * 1.前提条件:A.完美二叉树 B.使用常量级空间
 * 2.遍历方式&DS选择,因为要指向同级的右侧兄弟或堂兄弟.考虑,将兄弟节点作为参数穿进去
 * *****************************************************************************
 * Time: 12 mins
 * Beat: 86%
 * Bug:0
 * *****************************************************************************
 * Hindsight:
 * 1.这次的做法,完全是自己构思出来的,和第一版的解法进行比较,发现思路和解法完全不一样的。
 * 2.这个方法只适用于完美二叉树,要保证,一个节点如果左侧兄弟无children,则他肯定也不会有!!!
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 */
public class No116_Populating_Next_Right_Pointers_in_Each_Node {
    public void connect(TreeLinkNode root) {
        helper(root, null);

    }

    private void helper(TreeLinkNode root, TreeLinkNode brother) {
        if (root == null) return;
        root.next = brother;
        helper(root.left, root.right);
        helper(root.right, brother == null ? null : brother.left);

    }


    public void connect_easyUnderstand(TreeLinkNode root) {

        while (true) {
            TreeLinkNode leftMost = null;
            TreeLinkNode previous = null;
            while (root != null) {
                if (root.left != null) {
                    if (leftMost == null)
                        leftMost = root.left;
                    if (previous == null)
                        previous = root.left;
                    else {
                        previous.next = root.left;
                        previous = root.left;
                    }
                }
                if (root.right != null) {
                    if (leftMost == null)
                        leftMost = root.right;
                    if (previous == null)
                        previous = root.right;
                    else {
                        previous.next = root.right;
                        previous = root.right;
                    }
                }
                root = root.next;
            }
            if (leftMost != null)
                root = leftMost;
            else
                return;
        }
    }
}
