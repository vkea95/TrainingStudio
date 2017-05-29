package leetcode.com.medium.part11;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/3/23.
 * Location:
 * https://leetcode.com/problems/linked-list-cycle/
 * ***************************************************
 * Description:
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 * ****************************************************
 * Solution:
 * 根据题意：猜想估计是一个比较巧妙的思路来解决问题，想到用HashMap来fix
 * 但因为要求不能用额外的存储空间，所以也没有什么好办法
 * 网络答案：龟兔跑步，如果快的追上了慢的，那么就是一个环，想法太牛了！！！
 */
public class No141_Linked_List_Cycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast, slow;
        fast = head.next;
        slow = head;
        while (fast != slow) {
            //bug1:forget to judge (fast==null)
            if (fast == null || fast.next == null)
                return false;
            fast = fast.next.next;
            slow = slow.next;

        }
        return true;
    }
}
