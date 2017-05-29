package leetcode.com.pickup1.hard;

import leetcode.com.util.TreeLinkNode;
import leetcode.com.util.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tclresearchamerica on 8/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 * ****************************************************
 * Description:
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * <p>
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * <p>
 * Note:
 * <p>
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 * 1
 * /  \
 * 2    3
 * / \    \
 * 4   5    7
 * After calling your function, the tree should look like:
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \    \
 * 4-> 5 -> 7 -> NULL
 * <p>
 * ****************************************************
 * Thought:
 * 1.完全蒙圈啊,所以这道题的关键在于要把同层的node可以放在一起进行处理,
 * 2.所以需要这样的数据结构,其实可以用之前的算法去套(递归或是非递归),因为算法是植根于算法的具体细节
 * ****************************************************
 * Ref:https://segmentfault.com/a/1190000003465911
 * 复杂度
 时间 O(N) 空间 O(N)

 思路
 相当于是Level Order遍历二叉树。通过一个Queue来控制每层的遍历，注意处理该层最后一个节点的特殊情况。此方法同样可解第二题。
 * ****************************************************
 * Time:-
 * Beat: 20%
 * Bug:-
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No117_Populating_Next_Right_Pointers_in_Each_Node_II {
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = queue.poll();
                node.next = i == size - 1 ? null : queue.peek();
                if (node.left!=null) queue.offer(node.left);
                if (node.right!=null)queue.offer(node.right);
            }
        }

    }
}
