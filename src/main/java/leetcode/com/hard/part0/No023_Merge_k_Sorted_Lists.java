package leetcode.com.hard.part0;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/2/1.
 * Locations:
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * *******************************************************
 * Descriptions:
 * Jian_Merge k sorted linked lists and return it as one sorted indexList. Analyze and describe its complexity.
 */
public class No023_Merge_k_Sorted_Lists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = (start + end) / 2;
        ListNode left = mergeHelper(lists, start, mid);
        ListNode right = mergeHelper(lists, mid + 1, end);
        return mergeTwoList(left, right);
    }

    private ListNode mergeTwoList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                tail = left;
                left = left.next;
            } else {
                tail.next = right;
                tail = right;
                right = right.next;
            }
        }
        if (left != null) {
            tail.next = left;
        } else {
            tail.next = right;
        }
        return dummy.next;
    }
}
