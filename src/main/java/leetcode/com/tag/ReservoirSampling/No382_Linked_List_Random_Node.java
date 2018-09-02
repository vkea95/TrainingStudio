package leetcode.com.tag.ReservoirSampling;

import leetcode.com.util.ListNode;

/**
 * Created by JianZhang on 11/5/17.
 * Given a singly linked indexList, return a random node's value from the linked indexList.
 * Each node must have the same probability of being chosen.
 * Follow up:
 * What if the linked indexList is extremely large and its length is unknown to you?
 * Could you solve this efficiently without using extra space?
 * Example:
 * // Init a singly linked indexList [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 */
public class No382_Linked_List_Random_Node {

    class Solution {

        /** @param head The linked indexList's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
        public Solution(ListNode head) {

        }

        /** Returns a random node's value. */
        public int getRandom() {
            return 0;
        }
    }
}
