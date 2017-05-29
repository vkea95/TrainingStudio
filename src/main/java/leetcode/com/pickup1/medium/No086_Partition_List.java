package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 7/14/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/partition-list/
 * ****************************************************
 * Description:
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or
 * equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * ****************************************************
 * Thoughts:
 * 1.拆链表,然后重新搞,问题在于,如果只在一个链表上搞得话,总是出错。
 * 2.看了答案,发现还是要用2个链表,这个技能还是要掌握哦,临场应变能力还是要加强的。
 * ****************************************************
 * Time: 30 mins
 * Beat: 4%
 * Bug: -
 * ****************************************************
 * Hindsight:
 * 在单链表上推演的时候,没有问题,但是在运行中还是存在问题的。那个dummy会被指到很乱的位置。
 * 所以,说问题还是出现在对这个问题的推演不够,否则在推演过程中就可以发现问题啦
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No086_Partition_List {
    public static void main(String[] args) {
        No086_Partition_List obj = new No086_Partition_List();
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        obj.partition(head, 2);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        ListNode left = leftDummy;
        ListNode right = rightDummy;
        while (head!=null){
            if (head.val<x){
                left.next=head;
                left=left.next;
            }else {
                right.next = head;
                right=right.next;
            }
            head= head.next;
        }
        right.next=null;
        left.next=rightDummy.next;
        return leftDummy.next;
    }

    //bug:在单个链表上操作的话,会导致结果不正确指针乱指,原因并不清楚。
    public ListNode partition_failed(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode anchor = dummy;
        ListNode pre = dummy;
        while (head != null) {
            if (head.val < x) {
                ListNode tmp = head.next;
                pre.next = head.next;
                head.next = anchor.next;
                anchor.next = head;
                anchor = head;
                head = tmp;
            } else {
                pre = head;
                head = head.next;
            }
        }
        return dummy.next;
    }
}
