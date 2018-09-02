package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 7/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/rotate-list/
 * ****************************************************
 * Description:
 * Given a indexList, rotate the indexList to the right by k places, where k is non-negative.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * ****************************************************
 * Thoughts:
 * 相差k个节点的话,那么就是设置2个节点,fast节点领先k个节点即可。
 * 问题在于处理1个节点或者是k=1的时候,我们的处理有问题,要重新推导公式才可以,要讲fast节点推进到null才算完
 * 因为头节点也会被旋转,所以考虑从dummy节点开始算k
 * ****************************************************
 * Time:40min
 * Beat: 10%
 * Bug:4
 * ****************************************************
 * Hindsight:
 * 在第一遍时候的做法,最为简单明了,在i<n的时候,不断挪动k,然后再增加一个从head开始的指针,
 * 两个指针一起动,知道快速指针.next为null
 * ****************************************************
 */
public class No061_Rotate_List {

    public ListNode rotateRight(ListNode head, int k) {


        if (head == null || k == 0) return head;
        //bug1:需要对较大的k进行求余数操作
        k = k % getLength(head);
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        //bug2:算来算去,这个2个快慢指针的出发点还是该不一样,否则slow就会多走1不 十分重要
        ListNode fast = head;
        ListNode slow = dummy;
        ListNode prev = null;
        int count = 0;
        //looking for the k nodes
        while (fast != null) {
            prev = fast;
            fast = fast.next;
            if (count == k) {
                slow = slow.next;
            } else {
                count++;
            }
        }
        //old solution start
        //bug3:要判断slow.next是否为null,这个原因是为了保证节点个数少的情况下,工作正常
//        if (slow.next == null) return head;
//        fast = slow.next;
        //cut the listNode
//        slow.next = null;

//        slow = fast;

//        while (slow.next != null) slow = slow.next;
        //bug4:不能用dummy.next代替head,因为slow要是没有挪动地方的话,dummy.next就已经被切断了
//        slow.next = head;
        //old solution end
        prev.next = head;
        fast = slow.next;
        slow.next = null;
        return fast;
    }


    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }
}
