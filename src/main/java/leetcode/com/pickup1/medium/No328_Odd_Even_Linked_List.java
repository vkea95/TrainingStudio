package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

import java.util.List;

/**
 * Created by tclresearchamerica on 6/27/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/odd-even-linked-list/
 * ****************************************************
 * Descriptioin:
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are
 * talking about the node number and not the value in the nodes.
 * <p>
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 * <p>
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No328_Odd_Even_Linked_List {

    public ListNode oddEvenList(ListNode head) {
        //boundary check
        if (head == null || head.next == null) return head;
        ListNode oddHead = new ListNode(-1);
        ListNode evenHead = new ListNode(-1);
        //bug1:此时even应取evenHead的值,而不是设个null给他
        ListNode even = evenHead;
        oddHead.next = head;
        ;

        while (head.next != null) {
            //bug2:此时even应取evenHead的值,而不是设个null给他
            even.next = head.next;
            even = even.next;
            head.next = head.next.next;
            if (head.next == null) break;
            head = head.next;

        }
        even.next = null;
        head.next = evenHead.next;
        return oddHead.next;

    }
}
