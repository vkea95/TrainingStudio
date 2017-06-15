package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 7/18/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/sort-list/
 * ****************************************************
 * Description:
 * Sort a linked list in O(n log n) time using constant space complexity.
 * ****************************************************
 * Thoughts:
 * 1.链表节点的特征就是只知道next,所以该如何处理呢。难道是2分法,分别有序然后再合并
 * 2.或者是类似于快速排序
 * ****************************************************
 * Time: 35 mins
 * Beat: 30%
 * Bug:2
 * ****************************************************
 * Hindsight:
 * 1.合并排序的思想和那个合并k个有序链表思想是一致的。
 * 2.再次熟悉了快速排序,需要注意的是,在处理和pivt相等的数值时候,用链表就很快啦
 * ****************************************************
 * ****************************************************
 */
public class No148_Sort_List {


    //快排
    //寻找哨兵,分别存左,右list, 保留哨兵,合并,返回
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pivotNode = head, curt = head.next,
                leftHead = new ListNode(-1), left = leftHead,
                rightHead = new ListNode(-1), right = rightHead, dummy = new ListNode(-1), prev = dummy;
        //bug2:TLE,原因是重复相等的节点很多,这个时候,考虑要将pivot的
        ListNode pivotHead = pivotNode;
        while (curt != null) {
//            prev = curt;
            if (curt.val < pivotNode.val) {
                left.next = curt;
                left = left.next;
            } else if (curt.val > pivotNode.val) {
                right.next = curt;
                right = right.next;
                //bug2:TLE,原因是重复相等的节点很多,这个时候,考虑要将pivot的
            } else {
                pivotNode.next = curt;
                pivotNode = pivotNode.next;
            }
            curt = curt.next;
//            prev.next = null;
        }
        //clean
        left.next = null;
        right.next = null;

        ListNode leftRst = sortList(leftHead.next);
        ListNode rightRst = sortList(rightHead.next);
        left = leftRst;
        //bug1:没有处理left节点为空的情况,
        //opt1:同时这个while循环写的很好,不用特意保留prev节点啦
        while (left != null && left.next != null) {
            left = left.next;
        }
        if (left != null) left.next = pivotHead;
        else leftRst = pivotHead;
        pivotNode.next = rightRst;
        return leftRst;

//        dummy.next = leftRst;
//        prev = leftRst;
//        while (leftRst != null) {
//            prev = leftRst;
//            leftRst = leftRst.next;
////        }
//        prev.next = pivotNode;
//        pivotNode.next = rightRst;

//        return dummy.next;
    }

    //Jian_Merge 排序
    public ListNode sortList_slow(ListNode head) {
        if (head == null || head.next == null) return head;
        //opt:去掉dummy节点,设置prev节点
//        ListNode dummy = new ListNode(-1);
//        dummy.next = head;
        //bug2:设置prev节点后,需将fast和slow的起点都设置成head
//        ListNode fast = head.next;
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = head;

        while (fast != null && fast.next != null) {
            //opt1:保留前置节点
            prev = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        //bug1: 要保留slow,并切断链表
//        ListNode temp = slow;
//        slow = slow.next;
//        temp.next = null;
        //opt1:
        prev.next = null;
        ListNode left = sortList_slow(head);
        ListNode right = sortList_slow(slow);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                prev.next = left;
                left = left.next;
            } else {
                prev.next = right;
                right = right.next;
            }
            prev = prev.next;
            prev.next = null;
        }
        if (left != null) prev.next = left;
            //opt2:至少有一个节点是否非null,或者直接设置成null,也没有关系
//        else if (right != null) prev.next = right;
        else prev.next = right;
        return dummy.next;
    }
}
