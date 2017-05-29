package leetcode.com.pickup1.easy;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 6/3/16.
 * ****************************************************
 * in Hindsight
 * 1.面对链表问题,还是要搞个在head前面的dummy节点比较好,这样可以方便处理干掉头节点的问题,看题的时候,
 * 就想到了这个问题,不过没有意识到会出现bug,以及相关的解法等等
 * ****************************************************
 * Time: 25 mins   Beats: 6%
 * ****************************************************
 */
public class No019_Remove_Nth_Node_From_End_of_List {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (int i = 0; i < n - 1; i++) {
            if (head.next == null) return null;
            head = head.next;
        }
        ListNode preNode = dummy;
        while (head.next != null) {
            head = head.next;
            preNode = preNode.next;
        }
        preNode.next = preNode.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd_failed(ListNode head, int n) {
        if (n == 0) return head;
        ListNode tail = head;
        int i = 0;
        while (tail.next != null && i < n) {
            tail = tail.next;
            i++;
        }
        //bug2:这个地方要对指针进行一次判断，决定是否可以返回了，毕竟移动过的指针，需要确认下是否到了尾部
        if (tail.next == null) return head.next;
        ListNode targetPre = head;
        while (tail.next != null) {
            tail = tail.next;
            targetPre = targetPre.next;
        }
        //bug1:只有一个元素的时候，还要删除1个那么就返回null，否则也会异常
        targetPre.next = targetPre.next == null ? null : targetPre.next.next;
        return head;
    }
}
