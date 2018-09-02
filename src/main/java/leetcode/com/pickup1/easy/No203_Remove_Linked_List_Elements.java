package leetcode.com.pickup1.easy;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 6/20/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/remove-linked-list-elements/
 * ****************************************************
 * Description:
 * Remove all elements from a linked indexList of integers that have value val.
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4 --> 5
 * ****************************************************
 * Time: 5 mins
 * Beat: 3%
 * Bug: 0
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
public class No203_Remove_Linked_List_Elements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);

        dummy.next = head;
        ListNode prev = dummy;


        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
            } else {
                prev = head;
            }
            head = head.next;
        }

        return dummy.next;
    }
}
