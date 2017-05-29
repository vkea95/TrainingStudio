package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/7/25.
 */
public class No082_Remove_Duplicates_from_Sorted_List_I {

    public ListNode deleteDuplicates_II(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy, backup = null;

        while (head != null) {
            if (head.next != null) {
                if (head.val == head.next.val) {
                    head.next = head.next.next;
                    backup = head;
                } else {
                    if (head == backup) {
                        prev.next = head.next;
                        // backup =null;
                    } else {
                        prev = head;
                    }
                    head = head.next;

                }
            } else {
                if (head == backup) {
                    prev.next = null;
                }
                //bug1:没有迁移head,否则会出现TLE
                head = head.next;
            }

        }
        return dummy.next;
    }

}
