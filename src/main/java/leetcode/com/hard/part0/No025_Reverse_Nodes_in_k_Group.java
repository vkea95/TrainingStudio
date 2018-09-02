package leetcode.com.hard.part0;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/2/1.
 * Locations:
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * ********************************************************
 * Description:
 * Given a linked indexList, reverse the nodes of a linked indexList k at a time and return its modified indexList.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example,
 * Given this linked indexList: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 * ********************************************************
 * Solutions:
 * 保留上次处理后的尾部结点，在处理后续的k个结点，并置倒序，然后再链接上个尾部结点，依次处理即可
 * ********************************************************
 * Tips：
 * 源代码中的reverse方法，理解起来有点困难，看看注释就明白多了，还是要自己再写一遍才会彻底理解
 */
public class No025_Reverse_Nodes_in_k_Group {
    //reverse head->n1->..->nk->next..
    //to head->nk->..->n1->next..
    //return n1
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        head = dummy;
        while (head.next != null) {
            head = reverseNextK(head, k);
        }

        return dummy.next;
    }

    private ListNode reverseNextK(ListNode head, int k) {
        //check if there is enough node to reverse
        ListNode next = head;//next is not null
        for (int i = 0; i < k; i++) {
            if (next.next == null) {
                return next;
            }
            next = next.next;
        }
        //reverse
        ListNode n1 = head.next;
        ListNode prev = head;
        ListNode curt = n1;
        for (int i = 0; i < k; i++) {
            ListNode temp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = temp;
        }
        n1.next = curt;
        head.next = prev;
        return n1;
    }
}
