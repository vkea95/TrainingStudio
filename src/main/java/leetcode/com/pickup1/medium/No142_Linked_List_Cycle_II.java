package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 6/27/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/linked-list-cycle-ii/
 * ****************************************************
 * Description:
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * <p>
 * Note: Do not modify the linked list.
 * ****************************************************
 * Thoughts:
 * 1.好像是,一个走1步,一个走2步,相遇的地方就是cycle开始的地方-->理解错误
 * 2.解释:假设环的长度为l，从开始到两个指针第一次相遇总共走了m步，那么Fast指针走了2*m步，Slow指针走了m步，Fast比Slow多走了m步，
 * Fast比Slow多走了x圈，那么有x*l==m，l是m的一个因子，再走m步，两个指针每次走一步，Fast移到开头位置，Slow走了m步停在第一次相遇位置，
 * Fast因为也是每次走一步，所以也会停在相遇位置，而且可以看出来，一旦Fast进入环,Fast和Slow结点就保持相对静止了
 * 所以,如果相遇点距离环形入口有n个点,那么走m-n步必然会相遇于环形入口点.
 * ****************************************************
 * Thoughts:
 * fast节点必然要选择head.next
 * Time: 30 mins:
 * Beat: 18%
 * Bug: 0
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No142_Linked_List_Cycle_II {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null)
                return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = dummy;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;

    }
}
