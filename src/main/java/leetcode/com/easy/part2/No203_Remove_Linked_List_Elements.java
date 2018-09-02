package leetcode.com.easy.part2;

import leetcode.com.util.ListNode;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by tclresearchamerica on 4/5/16.
 * Location:
 * https://leetcode.com/problems/remove-linked-list-elements/
 * ************************************************
 * Description:
 * Remove all elements from a linked indexList of integers that have value val.
 * Example
 * Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
 * Return: 1 --> 2 --> 3 --> 4ß --> 5
 * <p>
 * ************************************************
 * Solution:
 */
public class No203_Remove_Linked_List_Elements {
    public static void main(String[] args) {
        No203_Remove_Linked_List_Elements obj = new No203_Remove_Linked_List_Elements();
        System.out.print("aaaaa");
        getCurrentUTCTimestamp();
    }


    public static String getCurrentUTCTimestamp() {
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
//
//        final TimeZone utc = TimeZone.getTimeZone("America/Los_Angeles");
        final TimeZone utc = TimeZone.getTimeZone("PDT");
        dateFormatter.setTimeZone(utc);

        return dateFormatter.format(new java.util.Date());
    }


    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;

        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
            } else {
                pre = head;
            }
            head = head.next;
        }


        return dummy.next;

    }
}
