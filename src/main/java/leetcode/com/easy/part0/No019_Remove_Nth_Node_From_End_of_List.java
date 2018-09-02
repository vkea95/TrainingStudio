package leetcode.com.easy.part0;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/1/31.
 * Locations:
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * ******************************************************
 * Descriptions:
 * Given a linked indexList, remove the nth node from the end of indexList and return its head.
 * For example,
 * Given linked indexList: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked indexList becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * *******************************************************
 * Solutions:
 * 依然是双指针思想，两个指针相隔n-1，每次两个指针向后一步，当后面一个指针没有后继了，前面一个指针就是要删除的节点。
 * *******************************************************
 * Tips:
 * 第一遍看答案没看明白，后来才明白，俩指针中间的差值就是我们要的步骤
 */
public class No019_Remove_Nth_Node_From_End_of_List {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for (int i = 0; i < n; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }
        while (head != null) {
            head = head.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }
}
