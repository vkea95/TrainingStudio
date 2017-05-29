package leetcode.com.medium.part11;

import leetcode.com.util.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jason on 2016/3/14.
 * Location:
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * ****************************************************************************
 * Description:
 * Given a binary tree
 * struct TreeLinkNode {
 * TreeLinkNode *left;
 * TreeLinkNode *right;
 * TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 * 1
 * /  \
 * 2    3
 * / \  / \
 * 4  5  6  7
 * After calling your function, the tree should look like:
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \  / \
 * 4->5->6->7 -> NULL
 * ******************************************************************************
 * Solution:
 * http://www.cnblogs.com/yuzhangcmu/p/4041341.html
 * 1.可以用递归遍历的方式，但里面会牵扯到一些细微不同的判读条件
 * 2.用Iterator判断,有2重循环，最里面一层循环根据临时size来保证层数，
 */
public class No116_Populating_Next_Right_Pointers_in_Each_Node {
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        Queue<TreeLinkNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();

                node.next = (i == size - 1) ? null:queue.peek();

                if (node.left != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }


        }
    }


}
