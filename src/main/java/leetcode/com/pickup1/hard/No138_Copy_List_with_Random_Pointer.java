package leetcode.com.pickup1.hard;

import leetcode.com.util.RandomListNode;

/**
 * Created by tclresearchamerica on 6/15/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * ****************************************************
 * Description:
 * A linked indexList is given such that each node contains an additional random pointer which could point to
 * any node in the indexList or null.
 * Return a deep copy of the indexList.
 * ****************************************************
 * Thoughts:
 * 问题在于如何处理这个random pointer,
 * 所以现在的做法就是说:先shallow copy,random pointer是啥先不要管,可以拷贝的啦
 * 如果要是利用这个random的话,因为它是乱指定的,所以我们必须借由原来的链表节点,完成对它的定位
 * ****************************************************
 * Time: 18 mins
 * Beat:63
 * Bug:0
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No138_Copy_List_with_Random_Pointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return head;

        RandomListNode dummy = new RandomListNode(-1);
        dummy.next = head;

        RandomListNode tmp = head;
        //step1:shallow copy
        while (head != null) {
            tmp = head.next;
            head.next = new RandomListNode(head.label);
            head.next.next = tmp;
            head = tmp;
        }
        //step2:modify random pointer
        head = dummy.next;
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            head = head.next.next;
        }
        //step3.recover links
        head = dummy.next;
        tmp = new RandomListNode(-1);
        RandomListNode newHead = tmp;
        while (head != null) {
            newHead.next = head.next;
            newHead = newHead.next;
            head.next = head.next.next;
            head = head.next;
        }

        return tmp.next;
    }

}
