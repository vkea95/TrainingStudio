package leetcode.com.pickup1.easy;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 6/29/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reverse-linked-list/
 * ****************************************************
 * Description:
 * Reverse a singly linked list.
 * ****************************************************
 * Time: 7 mins
 * Beat: 4%
 * Bug: 0
 * ****************************************************
 * Thought:
 * 忘记怎么搞了,稍微画图了,才明白要一个临时变量才可以
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No206_Reverse_Linked_List {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
//        ListNode newHead = new ListNode(0);

        while (head != null) {
            ListNode tmp = head.next;
            head.next = dummy.next;

            dummy.next = head;
            head = tmp;
        }
        return  dummy.next;
    }
}
