package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/7/25.
 * ****************************************************
 * ****************************************************
 * Description:
 *
 * Sort a linked indexList using insertion sort.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No147_Insertion_Sort_List {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);

        while (head!=null){
            ListNode node =dummy;
            //bug2;此处该是判断node.next的value
            while (node.next!=null&& node.next.val<head.val){
                node=node.next;
            }
            ListNode temp =head.next;
            ListNode next =node.next;
            node.next=head;
            head.next=next;
            //bug1;此处需要将之前保存的temp指针传给head.next
            head=temp;
        }
        return dummy.next;

    }
}
