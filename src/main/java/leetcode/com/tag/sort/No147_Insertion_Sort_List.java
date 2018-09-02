package leetcode.com.tag.sort;

import leetcode.com.util.ListNode;

import java.util.List;

/**
 * Created by JianZhang on 8/31/17.
 * Sort a linked indexList using insertion sort.
 * 1554
 * Thoughts:
 * 1. what's the insertionSort?:
 * 1.1 ref: http://threezj.com/2016/03/13/%E7%AE%80%E5%8D%95%E6%8E%92%E5%BA%8F%E4%B9%8B%E5%86%92%E6%B3%A1%E3%80%81%E9%80%89%E6%8B%A9%E3%80%81%E6%8F%92%E5%85%A5%E3%80%81%E5%B8%8C%E5%B0%94%E8%AF%A6%E7%BB%86%E6%80%BB%E7%BB%93/
 * 1.2 插入排序就像打麻将抓牌,手里的牌是有序的,新来的牌比手里最大的牌大,那么就啥都不做,否则,就交换位置,直到不交换为,breka出for循环
 * Solution:
 * 1. ref: http://www.jiuzhang.com/solutions/insertion-sort-list/
 * 2. 链表除了使用当前节点外,还会用到dummy节点,以及各种next节点。
 * 3. 基本操作,就是拆链儿,装链儿,重新指向head
 */
public class No147_Insertion_Sort_List {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        while (head != null) {
            ListNode node = dummy;
            //step: 寻找新链表中,第一个比head的value大的节点
            while (node.next!=null && node.next.val<head.val){
                node=node.next;
            }
            //开始拆链儿,装链儿
            ListNode tmp = head.next;//备份
            head.next=node.next;//插入head 节点
            node.next=head;//拼装节点
            head=tmp;//重新设置head节点

        }
        return dummy.next;
    }
}
