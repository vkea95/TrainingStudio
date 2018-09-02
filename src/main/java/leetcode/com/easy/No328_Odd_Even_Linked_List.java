package leetcode.com.easy;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/2/8.
 * Locations:
 * https://leetcode.com/problems/odd-even-linked-list/
 * ***************************************************************
 * Descriptions:
 * Given a singly linked indexList, group all odd nodes together followed by the even nodes. Please note here we are
 * talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 * *****************************************************************
 * Solutions:
 * 因为要奇数，偶数元素要分开，所以每次循环的时候要判断2个元素，然后要在循环判断下次次个元素是否为null
 */
public class No328_Odd_Even_Linked_List {
    public ListNode oddEvenList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode odd = new ListNode(0);
        ListNode oddHead = odd;

        if (head == null)
            return null;

        while (head != null && head.next != null) {
            oddHead.next = head.next;
            oddHead = oddHead.next;
            if (head.next.next == null)
                break;
            head.next = head.next.next;
            head = head.next;
        }
        oddHead.next = null;
        head.next = odd.next;
        return dummy.next;

    }
}
