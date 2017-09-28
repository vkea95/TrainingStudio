package leetcode.com.tag.dfs;

import leetcode.com.util.ListNode;
import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/27/17.
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 */
public class No109_Convert_Sorted_List_to_Binary_Search_Tree {
    public static void main(String[] args) {
        No109_Convert_Sorted_List_to_Binary_Search_Tree obj = new No109_Convert_Sorted_List_to_Binary_Search_Tree();
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        obj.sortedListToBST(head);
    }

    //    Credit:http://www.cnblogs.com/grandyang/p/4295618.html
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        //单个节点的处理
        if (head.next == null) return new TreeNode(head.val);
//        快慢指针处理listNode的中的问题
        ListNode fast = head;
        ListNode slow = head;
        //负责拆链
        ListNode slowBk = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slowBk = slow;
            slow = slow.next;
        }
        //用中点值生成根节点
        TreeNode root = new TreeNode(slow.val);
        //bug1:需要将slow.next保存起来,否则slowBk.next = null会把数据给覆盖掉
        fast = slow.next;
        //拆链儿
        slowBk.next = null;
        //需要处理下slow没有移动的case
        if (slow != head)
            root.left = sortedListToBST(head);
        root.right = sortedListToBST(fast);
        return root;
    }
}
