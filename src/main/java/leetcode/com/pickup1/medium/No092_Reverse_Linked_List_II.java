package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 7/6/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * ****************************************************
 * Description:
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * <p>
 * return 1->4->3->2->5->NULL.
 * ****************************************************
 * Thought:
 * 1.链表逆序的加强版,指定start,end
 * 2.思考之后:由种思路,一种是先找到尾部再倒序,另一种是直接倒序,我选择第二种
 * 3.在处理连接2段链表的时候,要额外处理下
 * ****************************************************
 * Bug1: 没考虑m和n都是1的情况,如果从第一个节点开始逆序的话,处理结果会有问题哦
 * ****************************************************
 * Time: 30 mins
 * Beat: 9%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No092_Reverse_Linked_List_II {
    public ListNode reverseBetween(ListNode head, int m, int n) {

        if(m==n) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;

        while (m != 1 && head != null) {
            prev = head;
            head = head.next;
            m--;
            n--;
        }
        ListNode prev2 = head;
        prev.next=null;

        while (n != 0 && head != null) {
            ListNode temp =head.next;
            head.next=prev.next;
            prev.next=head;
            head = temp;
            n--;
        }
        prev2.next=head;

        return dummy.next;

    }
}
