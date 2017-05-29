package leetcode.com.medium.part21;

import leetcode.com.util.TreeNode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by tclresearchamerica on 4/28/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * ****************************************************
 * Description:
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * <p>
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 * ****************************************************
 * Analysis:
 * 目标节点可能为左/右子树,所以想着一直向左找到最左侧的节点---即最小值,然后再往回倒腾k--,如果不满足要求就再将右节点入栈时间复杂度:O(n)
 * 是否可以用树本身的特性来完结这个问题呢?
 * 其实可以用一个今天上午用到的priorityQueue来完成,
 * ****************************************************
 * Beats:7.8,但是我可以保证完成follow的问题,每次插一个元素的时候,同时维护这个queue就好了
 */
public class No230_Kth_Smallest_Element_in_a_BST {
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, Collections.reverseOrder());

        kthHelper(queue, root, k);
        return queue.peek();
    }

    private void kthHelper(PriorityQueue<Integer> queue, TreeNode root, int k) {

        if (root == null || queue.size() >= k) return;

        if (root.left != null) kthHelper(queue, root.left, k);
        if (queue.size() >= k) return;

        queue.add(root.val);

        if (root.right != null) kthHelper(queue, root.right, k);
    }
}


