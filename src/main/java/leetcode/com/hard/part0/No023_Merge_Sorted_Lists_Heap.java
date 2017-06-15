package leetcode.com.hard.part0;

import leetcode.com.util.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by jason on 2016/1/17.
 * Locations:
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * http://www.jiuzhang.com/solutions/merge-k-sorted-lists/
 * *****************************************************************
 * Descriptions:
 * Jian_Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * *****************************************************************
 * Solutions:
 * 感觉就是想象成N个数进行排序，排的细节需要按照两个listNode的list方式来进行处理，可用Divide&Conquer来处理，这样效率高些
 * 还有一个用到了heap的概念，不过会用到java特殊的数据结构，即priorityQueue，然后需要自己封装comparter，步骤如下：
 * A.将所有的数组元素放到heap中----->注意此时，heap已经完成一次排列，最小的元素在顶端
 * B.取出顶端元素，然后将该元素的next点放到heap中--->注意：放的同时又会进行一次排列，将新的最小元素放置顶端
 * C.直到heap为空，执行A->C
 * <p>
 * *****************************************************************
 * Tips：
 * 1。看到了网上给出的divide&Conquer的解决方案，有两点感触：
 * A.divide&conquer的解法生疏了
 * B.在处理两个listNode串的时候，需要放在整体中进行处理才好
 */
public class No023_Merge_Sorted_Lists_Heap {

    public Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode left, ListNode right) {
            if (left == null) {
                return 1;
            } else if (right == null) {
                return -1;
            }
            return left.val - right.val;
        }
    };

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        Queue<ListNode> heap = new PriorityQueue<>(lists.length, this.ListNodeComparator);

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.add(lists[i]);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if (head.next != null) {
                heap.add(head.next);
            }
        }
        return dummy.next;
    }
}
