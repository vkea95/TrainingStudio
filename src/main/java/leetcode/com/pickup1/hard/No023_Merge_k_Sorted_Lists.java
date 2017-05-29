package leetcode.com.pickup1.hard;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 7/16/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * ****************************************************
 * Description:
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * ****************************************************
 * Thoughts:
 * 1.merge 排序的算法,肯定是.
 * 2.一个一个的merge?会不会多跑很多趟呢?
 * Ref:
 * https://segmentfault.com/a/1190000002622574
 * 那么更加高效的办法就是对链表进行两两合并，两条链表的合并复杂度为O(l + k)，l和k分别代表了两条链表的元素个数，那么最终的复杂度为O(n)
 * （n为元素总数）。
 * ****************************************************
 * Time:25 mins
 * Beat:92%
 * Bug:1
 * ****************************************************
 * Hindsight:
 * 1.其实这道题也没那么的难,只是就是不断的合并,可是为什么要用二分合并呢,估计还是为了递归调用的方便
 * 2.如果不这么做的话,光是一个一个循环着来,因为每次遍历的长度就越来越长,那么会导致TLE
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No023_Merge_k_Sorted_Lists {
    public ListNode mergeKLists(ListNode[] lists) {
        //bug1:忘记判断是否为空了
        if (lists == null || lists.length == 0)
            return null;
        return helper(lists, 0, lists.length - 1);
    }

    public ListNode helper(ListNode[] lists, int start, int end) {
        if (start == end) {
            //当相等的时候,直接返回
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode left = helper(lists, start, mid);
        ListNode right = helper(lists, mid + 1, end);

        return merge(left, right);
    }


    public ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                prev.next = left;
                left = left.next;
                prev = prev.next;

            } else {
                prev.next = right;
                right = right.next;
                prev = prev.next;

            }
        }
//opt1:因为一个已经被循环完了,所以不必循环所有,直接连接即可
        if (left != null) {
            prev.next = left;
        }
        if (right != null) {
            prev.next = right;
        }
//        while (left != null) {
//            prev.next = left;
//            left = left.next;
//            prev = prev.next;
//
//        }
//
//        while (right != null) {
//            prev.next = right;
//            right = right.next;
//            prev = prev.next;
//
//        }
//        prev.next = null;
        return dummy.next;
    }

}
