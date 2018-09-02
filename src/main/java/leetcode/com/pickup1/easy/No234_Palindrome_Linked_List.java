package leetcode.com.pickup1.easy;

import leetcode.com.util.ListNode;

/**
 * Created by tclresearchamerica on 7/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/palindrome-linked-list/
 * ****************************************************
 * Description:
 * Given a singly linked indexList, determine if it is a palindrome(回文).
 * ****************************************************
 * Thoughts:
 * 1.palindrome->回文,难道是循环链表吗?
 * ****************************************************
 * Hindsight:
 * 1.不知道为什么现在解不开题目啦,虽然是easy的题目,但是一直没有解法呢
 * 2.判断回文,就是从两头开始的字符对应相等即可.
 * 3.这里用到了链表,所以会牵扯到一个逆序,然后再比较的过程
 * ref:https://discuss.leetcode.com/topic/33376/java-easy-to-understand
 * 4.另一个大神的解法,貌似是在寻找中的的过程中,就把链表给逆序了
 * https://discuss.leetcode.com/topic/18293/11-lines-12-with-restore-o-n-time-o-1-space/2
 * 2016/09/09
 * 1.和晓波讨论了一下,认为将方法模块化,是比较好的一种方法,此题可拆解为:寻找中点,翻转链表和回文check三个模块
 * 2.还是要画图解决问题,fast&slow问题,多画几个图就好了
 *
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No234_Palindrome_Linked_List {

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode midNode = findMiddleNode(head);
        midNode = reverse(midNode);
        return chkPalindrome(head, midNode);

    }

    private ListNode findMiddleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(-1);
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }

    private boolean chkPalindrome(ListNode head, ListNode midNode) {
        while (midNode != null && midNode.val == head.val) {
            midNode = midNode.next;
            head = head.next;
        }
        return midNode == null ? true : false;
    }

    public boolean isPalindrome_1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // if(fast != null) slow = slow.next;

        slow = reverse(slow);
        while (slow != null && head.val == slow.val) {
            head = head.next;
            slow = slow.next;
        }
        return slow == null;
    }

    private ListNode reverse_1(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
