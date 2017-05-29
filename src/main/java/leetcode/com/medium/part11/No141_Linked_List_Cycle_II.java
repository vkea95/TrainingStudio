package leetcode.com.medium.part11;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/3/23.
 * Location:
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * **********************************************************
 * Description:
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Note: Do not modify the linked list.
 * Follow up:
 * Can you solve it without using extra space?
 * **********************************************************
 * Solution:
 * 根据龟兔跑步结果，找到环上的某个点（fast==slow），从这个链表的头部开始遍历，若某点在在这个环上，那么它就是起点，
 * 但是网络上的那个略简单的解决方案不是快很明白:fast 走2步，slow走一步，结果就是slow走到了中点

 * ListNode fast, slow;
 * fast = head.next;
 * slow = head;
 * while (fast != slow) {
 * if(fast==null || fast.next==null)
 * return null;
 * fast = fast.next.next;
 * slow = slow.next;
 * }
 * <p>
 * while (head != slow.next) {
 * head = head.next;            *********为什么呢***********
 * slow = slow.next;            *********不太明白***********
 * }
 * return head;
 */
public class No141_Linked_List_Cycle_II {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode fast = head.next;
        ListNode slow = head;
        ListNode circleHead = head;

        while (fast != slow) {
            if (fast == null || fast.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        while (circleHead != fast || fast != slow) {
            fast = fast.next;
            if (fast == circleHead)
                break;
            if (fast == slow) {
                circleHead = circleHead.next;
            }

        }
        return circleHead;
    }
}
