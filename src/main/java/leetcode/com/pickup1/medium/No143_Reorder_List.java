package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 6/30/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reorder-list/
 * ****************************************************
 * Description:
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * You must do this in-place without altering the nodes' values.
 * <p>
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * ****************************************************
 * Though:
 * 1.中点是第一要点,要先找到中点
 * 2.要将中点后的内容,切换为后半段内容要倒序
 * ****************************************************
 * Time: 30 mins
 * Beat: 28%
 * Bug: 2
 * ****************************************************
 * optimization
 * 1.fast的判断条件可以优化
 * 2.整个处理可以模块化:findMid,reverse & merge
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No143_Reorder_List {
    public void reorderList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode slow = head;
        ListNode fast = head;
        //looking for mid node
        while (true) {
            //将判断条件 fast.next!=null && fast.next.next!=null改为如下
            //可将整个处理整理成一个while
            if (fast != null && fast.next != null) {
                fast = fast.next.next;
            } else {
                break;
            }
            slow = slow.next;
        }
        //reversing node list
        fast = slow.next;
        slow.next = null;
        ListNode prev = new ListNode(-1);

        while (fast != null) {
            ListNode tmp = fast.next;

            fast.next = prev.next;
            prev.next = fast;
            fast = tmp;
        }
        head = dummy.next;
        //bug1:没有将头结点返还给fast,fast一直在最后一个结点上
        fast = prev.next;
        while (head != null && fast != null) {
            ListNode tmp = head.next;
            head.next = fast;
            fast = fast.next;
            //bug2:当只有3个结点的时候,fast的结点个数多于前半段的结点个数
            //考虑将fast和slow同时初始化到头节点,这样前半段结点个数永远不会少于后半段
            head.next.next = tmp;

            head = tmp;

        }

        head = dummy.next;
    }

    public void reorderList_slow(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode slow = head;
        ListNode fast = head.next;
        //looking for mid node
        while (true) {
            if (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
            } else {
                break;
            }
            slow = slow.next;
        }
        //reversing node list
        fast = slow.next;
        slow.next = null;
        ListNode backDummy = new ListNode(-1);
        ListNode prev = backDummy;

        while (fast != null) {
            ListNode tmp = fast.next;

            fast.next = prev.next;
            prev.next = fast;
            fast = tmp;
        }
        head = dummy.next;
        //bug1:没有将头结点返还给fast,fast一直在最后一个结点上
        fast = prev.next;
        while (head != null && fast != null) {
            ListNode tmp = head.next;
            head.next = fast;
            fast = fast.next;
            //bug2:当只有3个结点的时候,fast的结点个数多于前半段的结点个数
            //考虑将fast和slow同时初始化到头节点,这样前半段结点个数永远不会少于后半段
            if (tmp == null) {
                head.next.next = fast;
            } else {

                head.next.next = tmp;
            }
            head = tmp;

        }

        head = dummy.next;
    }
}
