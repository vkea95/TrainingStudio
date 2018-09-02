package leetcode.com.pickup1.hard;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 6/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * ****************************************************
 * Description:
 * Given a linked indexList, reverse the nodes of a linked indexList k at a time and return its modified indexList.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example,
 * Given this linked indexList: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 * ****************************************************
 * Key points:
 * 1.reverse
 * 2.constant memory
 * 3.left-out nodes in the end should remain as it is
 * 4.may not alter the value
 * ****************************************************
 * Reactions:
 * 1.need a dummy node for the
 * 2.还需要另一个dummy负责栓挂要倒序的节点串,
 * 3.因为倒序,所以可能会用到stack,但是因为有constant的要求,所以还是作罢
 * ****************************************************
 * Thoughts:
 * 1.这是一个组合题,先处理整体需要倒序的一个串,提取出必要的元素,然后再考虑在不满足k的情况下,如何对串进行复原(要对计数器进行判断),
 * 同时还要对prev节点进行一次必要处理
 * ****************************************************
 * Time: 35 mins
 * Beats: 20%
 * Bug: 0
 * ****************************************************
 */
public class No025_Reverse_Nodes_in_kGroup {
    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || k <= 1) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode dummy2 = new ListNode(0);
        ListNode tail = null;

        int i = 0;
        while (head != null) {
            i = 0;
            while (head != null && i < k) {
                if (dummy2.next == null) {
                    dummy2.next = head;

                    tail = head;
                    head = head.next;
                    tail.next = null;
                } else {
                    ListNode tmp = head.next;
                    head.next = dummy2.next;
                    dummy2.next = head;
                    head = tmp;
                }
                i++;
            }
            if (i == k) {
                //perfect match
                prev.next = dummy2.next;
                prev = tail;
                dummy2.next = null;

            }

        }
        if (i > 0 && i < k) {
            prev.next = null;
            //left-out node deals
            while (dummy2.next != null) {
                ListNode tmpNode = dummy2.next.next;
                dummy2.next.next = prev.next;
                prev.next = dummy2.next;
                dummy2.next = tmpNode;

            }
        }
        return dummy.next;
    }
}
