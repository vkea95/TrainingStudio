package leetcode.com.medium.part11;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/3/28.
 * Location：
 * https://leetcode.com/problems/insertion-sort-list/
 * *******************************************************
 * Description:
 * Sort a linked list using insertion sort.
 * *******************************************************
 * Solution:
 * 将head的结点放入node的链表中，因为head的链表属于无序状态，所以，每次都要从node的第一项开始循环判断
 */
public class No147_Insertion_Sort_List {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        // 这个dummy的作用是，把head开头的链表一个个的插入到dummy开头的链表里
        // 所以这里不需要dummy.next = head;

        while (head != null) {
            ListNode node = dummy;
            while (node.next != null && node.next.val < head.val) {
                node = node.next;
            }

            ListNode temp = head.next;
            head.next = node.next;
            node.next = head;
            head = temp;
        }

        return dummy.next;

    }

}
