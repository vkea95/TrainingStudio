package ctc.com.viii.ch01.arrays;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 6/5/16.
 */
public class No02 {

    //follow up: not allowed to use a temporary buffer
    public ListNode removeDuplicateNode(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        while (head != null) {
            int tmp = head.val;
            ListNode pointer = head.next;
            ListNode prev = head;
            while (pointer != null) {
                if (pointer.val == tmp) {
                    prev.next = pointer.next;
                } else {

                    prev = pointer;
                }
                pointer = pointer.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
