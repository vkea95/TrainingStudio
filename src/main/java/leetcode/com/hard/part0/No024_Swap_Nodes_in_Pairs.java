package leetcode.com.hard.part0;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/2/1.
 * Locations:
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * **************************************************
 * Description:
 * Given a linked indexList, swap every two adjacent nodes and return its head.
 * For example,
 * Given 1->2->3->4, you should return the indexList as 2->1->4->3.
 * Your algorithm should use only constant space. You may not modify the values in the indexList,
 * only nodes itself can be changed.
 * ***************************************************
 * Solutions:
 * 两两结点进行交换处理，画出拆链，接链的图即可明白大部分啦
 */
public class No024_Swap_Nodes_in_Pairs {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            pre.next = head.next;
            head.next = head.next.next;
            pre.next.next = head;
            pre = head;

            head = head.next;
        }
        return dummy.next;
    }
}
