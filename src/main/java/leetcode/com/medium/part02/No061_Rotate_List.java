package leetcode.com.medium.part02;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/2/23.
 * Location:
 * https://leetcode.com/problems/rotate-list/
 * ********************************************
 * Description:
 * Given a indexList, rotate the indexList to the right by k places, where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * **********************************************
 * Solution:
 * 基本思路就是搞2个指针，中间差值为k，当最右侧指针循环到尾部的时候，即可交换指针纸箱即可
 * 但是 因为k可以大于链表长度，所以需要算长度，然后用mod的方法找到
 */
public class No061_Rotate_List {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        int length = getLength(head);
        int n = k % length;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int i = 0;
        while (i < n) {
            head = head.next;
            i++;
        }
        ListNode newHead = dummy.next;

        while (head.next != null) {
            head = head.next;
            newHead = newHead.next;
        }

        head.next = dummy.next;
        dummy.next = newHead.next;
        newHead.next = null;

        return dummy.next;

    }

    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }

}
