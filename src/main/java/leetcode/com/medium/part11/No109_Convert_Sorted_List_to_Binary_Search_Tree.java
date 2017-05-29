package leetcode.com.medium.part11;

import leetcode.com.util.ListNode;
import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/12.
 * Location:
 * https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
 * ***********************************************************************
 * Description:
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * ***********************************************************************
 * Solution:
 * 套用二叉树的判读方法，这样的话总是从最左侧的子节点，子树开始，那么list中的数值也就刚好可以放到结点中
 */
public class No109_Convert_Sorted_List_to_Binary_Search_Tree {

    private ListNode curt;

    private int getListLength(ListNode head) {
        int size = 0;

        while (head != null) {
            size++;
            head = head.next;
        }

        return size;
    }

    private TreeNode sortedListToBSTHelper(int size) {
        if (size <= 0) {
            return null;
        }

        TreeNode left = sortedListToBSTHelper(size / 2);
        TreeNode root = new TreeNode(curt.val);
        curt = curt.next;
        TreeNode right = sortedListToBSTHelper(size - 1 - size / 2);

        root.left = left;
        root.right = right;
        return root;

    }

    public TreeNode sortedListToBST(ListNode head) {
        curt = head;
        int size = getListLength(head);
        return sortedListToBSTHelper(size);
    }

}
