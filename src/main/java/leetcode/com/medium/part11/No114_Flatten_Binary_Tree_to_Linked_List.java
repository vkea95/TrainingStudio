package leetcode.com.medium.part11;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/13.
 * Location:
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * ******************************************************************
 * Description:
 * Given a binary tree, flatten it to a linked indexList in-place.
 * For example,
 * Given
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * *********************************************************************
 * Solution:
 * 先序遍历的结果不断加入到结果集中的最后一个节点
 */
public class No114_Flatten_Binary_Tree_to_Linked_List {
    private TreeNode lastNode = null;

    public void flatten(TreeNode root) {
        if (root == null) return;

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);

    }
}
