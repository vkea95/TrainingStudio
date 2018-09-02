package leetcode.com.medium.part01;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/1/17.
 * Locations:
 * https://leetcode.com/problems/add-two-numbers/
 * *************************************************************
 * Descriptions:
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked indexList.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * *************************************************************
 * Solutions:
 * 因为是倒序，所以从第一个元素开始相加求和，超过10的话，就会有进位操作，将进位的值放到next结点一起相加即可
 * *************************************************************
 * Tips:
 * 看了N遍都没看懂题意，英语还是不行啊，看过网上答案才明白啊，就是两个非负整数，按照倒序放在两个链表中，然后要求求和
 * 如此看来，也可以进行N位数的相加求和处理。。。算是一种备选的解决方案
 */
public class No002_Add_Two_Sums {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + carry;
            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + carry;
            carry = sum / 10;
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            l2 = l2.next;
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
