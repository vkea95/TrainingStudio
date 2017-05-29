package leetcode.com.medium.part11;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/3/24.
 * Location：
 * https://leetcode.com/problems/reorder-list/
 * *************************************************
 * Description:
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * *************************************************
 * Solution:
 * 1.寻找中点
 * 2.将其后面的链表倒序
 * 3.merger前后两部分的链表形成一个整体
 * ************************************************
 * Tips:
 * Reverser 方法突然不知道怎么搞了，所以按照想好的代码演戏一下，发现设计有误，重新操演一次就明白许多
 * 被改变value或者说指针的放在左侧---明白这个就不会在链表操作中迷糊了
 * Findmid 用slow和fast两个指针去寻找中点的话，在偶数结点的时候，slow.next才是我们要的中点
 * 可通过用4个结点的链表来演示验证
 */
public class No143_Reorder_List {
    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        //bug1:don't know how to make the condition
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    public ListNode merge(ListNode head, ListNode tail) {
        ListNode newHead = head;
        head = head.next;
        while (head != null || tail != null) {
            if (tail != null) {
                newHead.next = tail;
                newHead = newHead.next;
                tail = tail.next;
            }
            if (head != null) {
                newHead.next = head;
                newHead = newHead.next;
                head = head.next;
            }
        }
        return newHead;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        //find middle node
        ListNode midNode = findMid(head);
        ListNode tail = reverse(midNode.next);
        midNode.next = null;
        merge(head, tail);
    }

}
