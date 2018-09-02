package leetcode.com.medium.part02;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/1/23.
 * Locations:
 * https://leetcode.com/problems/partition-list/
 * **********************************************
 * Descriptions:
 * Given a linked indexList and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * **********************************************
 * Solutions:
 * 将list分为独立的两个list，然后再链接在一起
 */
public class No086_Partition_List {
    public ListNode partition(ListNode head, int x) {
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode left = leftDummy;
        ListNode right = rightDummy;
        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = left.next;
            } else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }
        right.next = null;
        left.next = rightDummy.next;
        return leftDummy.next;
    }
}
