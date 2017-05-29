package leetcode.com.easy.part1;

import leetcode.com.util.ListNode;

/**
 * Created by jason on 2016/3/30.
 * Location:
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * ************************************************************************
 * Description:
 * ************************************************************************
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 * A:          a1 → a2
 * ↘
 * c1 → c2 → c3
 * ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 * ************************************************************************
 * Solution:
 * 1.曾经想过用交叉比较的方式，但是忽略了重复元素的出现位置，所以失败了
 * 2.看了网络答案才意识找到第一个链表的末尾，再和第二个链表链接，如果是有交叉元素的话，那么会形成环形链表
 * 用环形链表的快慢指针就可以实现了，当然这个fast和slow的联系还需要再自己推导
 * *************************************************************************
 * Hints:
 * 快慢指针的问题，要再消化消化
 * 证明文章：http://blog.csdn.net/l294265421/article/details/50478818  --->按照几何问题将其证明
 * Reference：http://blog.sina.com.cn/s/blog_624ca80801011u6m.html
 */
public class No160_Intersection_of_Two_Linked_Lists {
    public static void main(String[] args) {
        No160_Intersection_of_Two_Linked_Lists obj = new No160_Intersection_of_Two_Linked_Lists();

        int i = 1;
        ListNode dummy = new ListNode(-1);
        ListNode head = new ListNode(i++);
        dummy.next = head;
        ListNode circuleNode = null;
        for (; i < 98; i++) {
            head.next = new ListNode(i);

            if (i == 33) circuleNode = head.next;
            System.out.print("  " + head.val);
            head = head.next;
        }
        head.next = circuleNode;
        System.out.print("  " + head.val);
        System.out.println();
        ListNode slow = dummy.next;
        ListNode fast = dummy.next;

        StringBuffer slowSb = new StringBuffer();
        StringBuffer fastSb = new StringBuffer();
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                System.out.print("slow==fast: " + slow.val);
                break;
            }
            slowSb.append(slow.val);
            slowSb.append(" ");
            fastSb.append(fast.val);
            fastSb.append(" ");
            System.out.print(fast.val + " ");

        }
        slowSb.append(" ||| ");
        slowSb.append(" ");
        fastSb.append(" ||| ");
        fastSb.append(" ");
        slowSb.append(slow.val);
        slowSb.append(" ");
        fastSb.append(fast.val);
        fastSb.append(" ");
        System.out.println();

        System.out.println("slow: " + slowSb.toString());
        System.out.println("fast: " + fastSb.toString());

        slowSb.append(" ||| ");
        slowSb.append(" ");
        fastSb.append(" ||| ");
        fastSb.append(" ");

        slowSb = new StringBuffer();
        fastSb = new StringBuffer();
        slow = dummy.next;
//        fast=fast.next;
        while (slow != fast) {
            slowSb.append(slow.val);
            slowSb.append(" ");
            fastSb.append(fast.val);
            fastSb.append(" ");

            slow = slow.next;
            fast = fast.next;
        }
        slowSb.append(slow.val);
        slowSb.append(" ");
        fastSb.append(fast.val);
        fastSb.append(" ");


        System.out.println("slow: " + slowSb.toString());
        System.out.println("fast: " + fastSb.toString());
        System.out.println("slow: " + slow == null ? "null" : slow.val);
        System.out.println("fast: " + fast == null ? "null" : fast.val);

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        //get the tail of listA
        ListNode node = headA;
        while (node.next != null) {
            node = node.next;
        }
        //connect A & B
        node.next = headB;
        ListNode rst = listCycleII(headA);
        node.next = null;
        return rst;
    }

    private ListNode listCycleII(ListNode head) {
        ListNode slow = head, fast = head;
        do {
            //非环形链表
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;

        } while (slow != fast);
        //make sure the head of the circle
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
