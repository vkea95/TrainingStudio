package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/11/17.
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * //BST的特性是,左孩子的value小于父,右孩子的value大于父?
 * //recursive的参数如果是放parent和child的话,其实没有办法区分左右,
 * 所以还是要利用class的成员存储里面的value,
 * 理论上首先发现的那个元素,就是比父节点大的左孩子,然后再遍历的就都是相对于刚发现的那个节点的右侧,或是上侧了,理论上该比它大,如果比它小那也是问题节点
 */
public class No099_Recover_Binary_Search_Tree {
    TreeNode preNode = new TreeNode(Integer.MIN_VALUE);
    TreeNode firstNode = null;
    TreeNode secondNode = null;

    public void recoverTree(TreeNode root) {
        recursive(root);
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;

    }

    private void recursive(TreeNode node) {
        if (node == null) return;

        recursive(node.left);
        if (firstNode == null && node.val < preNode.val) {
            firstNode = preNode;//bug1--->
        }
        if (firstNode != null && node.val < preNode.val) {
            secondNode = node;//bug2:此时不能停止递归,防止错误地替换节点,
            //后面的条件node.val < preNode.val 是说,被放到右侧的那个略小值,肯定是要小于之前刚被遍历过的节点
        }
        preNode = node;
        recursive(node.right);

    }

//      以下这个解法,不能应对根节点错误的case,
//    TreeNode left = null;
//    TreeNode right = null;

//    public void recoverTree(TreeNode root) {
//        recursive(null, root, false);
//        int tmp = left.val;
//        left.val = right.val;
//        left.val = tmp;
//    }
//
//    private void recursive(TreeNode parent, TreeNode child, boolean isLeft) {
//        if (child == null) return;
//
//        if (parent != null) {
//            if (isLeft && child.val >= parent.val) {
//                left = child;
//            } else if (isLeft == false && child.val <= parent.val) {
//                right = child;
//            }
//        }
//        recursive(child, child.left, true);
//        recursive(child, child.right, false);
//
//    }
}
