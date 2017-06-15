package leetcode.com.easy.part0;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/1/31.
 * Locations:
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * ******************************************************
 * Descriptions:
 * Jian_Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * ******************************************************
 * Solutions:
 * ******************************************************
 * Tips:
 * 循环处理后还会有list没有循环完毕，需要单独循环一次
 */
public class No021_Merge_Two_Sorted_Lists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                lastNode.next = l1;
                l1 = l1.next;
            } else {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next;
        }
        if (l1 != null) {
            lastNode.next = l1;
        } else {
            lastNode.next = l2;
        }

        return dummy.next;

    }
}
