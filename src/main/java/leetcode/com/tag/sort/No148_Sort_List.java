package leetcode.com.tag.sort;

import leetcode.com.util.ListNode;

import java.util.List;

/**
 * Created by JianZhang on 9/1/17.
 * Sort a linked list in O(n log n) time using constant space complexity.
 * Thought:
 * 1. 想到了用快速排序的方式
 * Solution:
 * 1. O(n log n) 需要对链表进行二分,那就可以考虑无环link的快慢指针寻找中点了,我的做法是任取第一点,根据数值进行二分,看来是有问题的。
 * 2. 分模块的处理,需要处理尾部 tail 等等,这个方法比较贴切
 */
public class No148_Sort_List {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        No148_Sort_List obj = new No148_Sort_List();
        obj.sortList(node);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMedian(head); // O(n)

        ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
        ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
        ListNode middleDummy = new ListNode(0), middleTail = middleDummy;
        while (head != null) {
            if (head.val < mid.val) {
                leftTail.next = head;
                leftTail = head;
            } else if (head.val > mid.val) {
                rightTail.next = head;
                rightTail = head;
            } else {
                middleTail.next = head;
                middleTail = head;
            }
            head = head.next;
        }

        leftTail.next = null;
        middleTail.next = null;
        rightTail.next = null;

        ListNode left = sortList(leftDummy.next);
        ListNode right = sortList(rightDummy.next);

        return concat(left, middleDummy.next, right);
    }

    private ListNode findMedian(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode concat(ListNode left, ListNode middle, ListNode right) {
        ListNode dummy = new ListNode(0), tail = dummy;

        tail.next = left;
        tail = getTail(tail);
        tail.next = middle;
        tail = getTail(tail);
        tail.next = right;
        tail = getTail(tail);
        return dummy.next;
    }

    private ListNode getTail(ListNode head) {
        if (head == null) {
            return null;
        }

        while (head.next != null) {
            head = head.next;
        }
        return head;
    }


    private ListNode sort_wrong(ListNode head) {
        ListNode leftHead = new ListNode(-1);
        ListNode left = null;
        ListNode rightHead = new ListNode(0);
        ListNode right = null;
        ListNode v = null;

        right = rightHead;
        left = leftHead;
        if (head == null) return head;

        v = head;
        v.next = null;
        head = head.next;
        while (head != null) {
            ListNode tmp = head.next;
            if (head.val < v.val) {
                left.next = head;
                left = left.next;
                left.next = null;
            } else {
                right.next = head;
                right = right.next;
                right.next = null;
            }
            head = tmp;
        }
        leftHead = sort_wrong(leftHead.next);
        rightHead = sort_wrong(rightHead.next);
        if (leftHead != null) //bug1: 忘记判断返回是否为null了
            v.next = leftHead.next;
        //bug2:leftHead 该是最靠近新的link的head位置,所以该循环搞定它先
        left = leftHead;
        while (left != null & left.next != null) {
            left = left.next;
        }
        if (left == null) {
            leftHead = v;
        } else {
            left.next = v;
        }
        v.next = rightHead;

        return leftHead;
    }
}
