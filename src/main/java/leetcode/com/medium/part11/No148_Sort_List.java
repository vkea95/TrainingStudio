package leetcode.com.medium.part11;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 5/16/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/sort-list/
 * **************************************************************
 * Description:
 * Sort a linked indexList in O(n log n) time using constant space complexity.
 * **************************************************************
 * Analysis:
 * 时间复杂度为O(n log n),就必然会有树?不对!应该会有归并排序或快速排序
 * 1.网络答案:快速排序和merge排序
 * 快速排序,需要left,right & pivot
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No148_Sort_List {
    public ListNode sortList(ListNode head) {
        //bug4:forget to check null
        if (head == null) return null;

        ListNode pivodHead = head;
        ListNode leftHead = new ListNode(-1);
        ListNode rightHead = new ListNode(-1);
        ListNode left = leftHead;
        ListNode right = rightHead;
        ListNode pivot = pivodHead;
        //bug1: curt=head -> curt=head.next
        ListNode curt = head.next;

        if (curt == null) return head;

        while (curt != null) {
            if (curt.val < pivot.val) {
                left.next = curt;
                left = left.next;
            } else if (curt.val > pivot.val) {
                right.next = curt;
                right = right.next;
            } else {
                pivot.next = curt;
                pivot = pivot.next;

            }
            curt = curt.next;
        }
        //bug2:clean the useless connection
        left.next = null;
        right.next = null;
        pivot.next = null;
        //bug3:recursive sort left & right
        left = sortList(leftHead.next);
        right = sortList(rightHead.next);

        pivot.next = right;
        //save the head of the left indexList
        ListNode re = left;

        while (left != null && left.next != null) {
            left = left.next;
        }

        if (left == null)
            re = pivodHead;
        else
            left.next = pivodHead;
        return re;


    }


    public ListNode sortList_mergeSort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode prev = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;
        ListNode firstHalf = sortList_mergeSort(head);
        ListNode secondHalf = sortList_mergeSort(slow);
        //经过递归处理后,firstHalf,secondHalf已经变得有序啦
        return merge(firstHalf, secondHalf);

    }


    private ListNode merge(ListNode firstHalf, ListNode secondHalf) {
        ListNode dummy = new ListNode(-1);
        ListNode curt = dummy;

        while (firstHalf != null && secondHalf != null) {
            if (firstHalf.val < secondHalf.val) {
                curt.next = firstHalf;
                firstHalf = firstHalf.next;
            }else {
                curt.next=secondHalf;
                secondHalf=secondHalf.next;
            }
            curt=curt.next;
        }

        //此时只需要判断其中一个节点即可啦
        if (firstHalf!=null){
            curt.next=firstHalf;
        }else {
            curt.next=secondHalf;
        }
        return dummy.next;
    }
}
