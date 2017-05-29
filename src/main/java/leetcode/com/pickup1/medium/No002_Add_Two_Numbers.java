package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 8/10/16.
 *
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/add-two-numbers/
 * ****************************************************
 * Description:
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and
 * each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * ****************************************************
 * Thoughts:
 * 1.Key Point:A.可以进位 B.链表长度可以不一致,C.循环接受后,需要链接和 考虑再次进位的问题
 * ****************************************************
 * Time: 15 mins
 * Beat:6% -> 33%
 * Bug:0
 * ****************************************************
 * Hindsight:
 * 1.在处理的时候,其实不需要一个中间的ListNode作为节点,然后处理他们,直接用next处理就好很多了
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No002_Add_Two_Numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        dummy.next = l1;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int temp = l1.val + l2.val + carry;
            //opt1:去除中间的无用节点调用
//            ListNode tmp = new ListNode(temp % 10);
//            prev.next = tmp;
//            prev = tmp;
            prev.next = new ListNode(temp % 10);
            prev = prev.next;
            carry = temp / 10;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int temp = l1.val + carry;
            //opt1:去除中间的无用节点调用
//            ListNode tmp = new ListNode(temp % 10);
//            prev.next = tmp;
//            prev = tmp;

            prev.next = new ListNode(temp % 10);
            prev = prev.next;
            l1 = l1.next;
            carry = temp / 10;
        }

        while (l2 != null) {
            int temp = l2.val + carry;
            //opt1:去除中间的无用节点调用
//            ListNode tmp = new ListNode(temp % 10);
//            prev.next = tmp;
//            prev = tmp;

            prev.next = new ListNode(temp % 10);
            prev = prev.next;
            l2 = l2.next;
            carry = temp / 10;
        }

        if (carry != 0) prev.next = new ListNode(carry);
        return dummy.next;

    }
}