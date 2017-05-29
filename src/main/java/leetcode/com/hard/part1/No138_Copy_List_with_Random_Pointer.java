package leetcode.com.hard.part1;

import leetcode.com.util.RandomListNode;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by tclresearchamerica on 5/18/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * ***************************************************************
 * Description:
 * A linked list is given such that each node contains an additional random pointer which could point to any node
 * in the list or null.Return a deep copy of the list.
 * ***************************************************************
 * Analysis:
 * 1.不考虑random pointer的话, 只有next pointer,那么只要循环遍历一次即可
 * 所以,需要一个可以辅助random pointer进行精准定位的data structure来完成操作.
 * 如果是按照顺序操作的话,那么就有queue,stack,按照key-value pair的话,有hashmap
 * 考虑用个hashMap,先用遍历的方式copy next pointer,同时放入hashmap,然后再iterator找个hashmap,操作random pointer
 * ***************************************************************
 * Solution:
 * 网络答案:beat 60%
 * * Algorithm
 * <p>
 * 1) Create the copy of node 1 and insert it between node 1 & node 2 in original Linked List, create the copy of 2
 * and insert it between 2 & 3.. Continue in this fashion, add the copy of N after the Nth node
 * <p>
 * 2) Now copy the random link in this fashion
 * <p>
 * original->next->random = original->random->next;
 * This works because original->next is nothing but copy of original and Original->random->next is nothing
 * but copy of random.
 * <p>
 * 3) Now restore the original and copy linked lists in this fashion in a single loop.
 * 4) Make sure that last element of original->next is NULL.
 *
 * 根据该答案的代码,貌似step2,3和4可以进行优化
 * ***************************************************************
 * Bug:
 * 1.如果是要复制的话,那么用B.next=A,如果A=new Random后,B.next就不再指向A了,;因为A的地址变了,儿B.next所指向的地址依然没变
 * ***************************************************************
 * ***************************************************************
 */
public class No138_Copy_List_with_Random_Pointer {
    public static void main(String[] args) {
        No138_Copy_List_with_Random_Pointer obj = new No138_Copy_List_with_Random_Pointer();
        RandomListNode node = new RandomListNode(-1);
        node.next = new RandomListNode(-1);
        obj.copyRandomList(node);
    }


    public RandomListNode copyRandomList(RandomListNode head) {

        if (head == null) return head;

        RandomListNode original = head;
        RandomListNode curOriginal = head;

        // step 1
        while (original.next != null) {
            RandomListNode copy = new RandomListNode(original.label);
            RandomListNode temp = original.next;
            original.next = copy;
            copy.next = temp;
            original = original.next.next;
        }
        original.next = new RandomListNode(original.label);

        //step 2
        while (curOriginal != null && curOriginal.next != null) {
            if (curOriginal.random != null) {
                curOriginal.next.random = curOriginal.random.next;
            }


            curOriginal = curOriginal.next.next;
        }

        //step 3 and 4
        RandomListNode orig = head;
        RandomListNode copyCur = head.next;
        RandomListNode copyHead = head.next;

        while (orig.next != null && copyCur.next != null) {
            orig.next = orig.next.next;
            copyCur.next = copyCur.next.next;
            orig = orig.next;
            copyCur = copyCur.next;
        }
        orig.next = null;

        return copyHead;
    }

    public RandomListNode copyRandomList_slower(RandomListNode head) {
        if (head == null) return null;

        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode newHead = dummy;

        HashMap<RandomListNode, RandomListNode> hashMap = new HashMap<>();
        while (head != null) {
            newHead.next = new RandomListNode(head.label);

            hashMap.put(head, newHead.next);
            head = head.next;
            newHead = newHead.next;
        }
        Iterator<HashMap.Entry<RandomListNode, RandomListNode>> it = hashMap.entrySet().iterator();

        while (it.hasNext()) {
            HashMap.Entry<RandomListNode, RandomListNode> entry = it.next();

            if (entry.getKey().random != null) {
                //bug2:missed the relationships
                entry.getValue().random = hashMap.get(entry.getKey().random);
            }

        }

        return dummy.next;

    }
}
