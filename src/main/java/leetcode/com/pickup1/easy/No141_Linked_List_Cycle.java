package leetcode.com.pickup1.easy;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 10/6/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/linked-list-cycle/
 * ****************************************************
 * Description:
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No141_Linked_List_Cycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow) return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }
}
