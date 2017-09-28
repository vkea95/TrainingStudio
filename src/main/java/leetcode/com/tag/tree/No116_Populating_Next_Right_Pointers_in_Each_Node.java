package leetcode.com.tag.tree;

import leetcode.com.util.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by JianZhang on 9/25/17.
 * Given a binary tree
 * <p>
 * struct TreeLinkNode {
 * TreeLinkNode *left;
 * TreeLinkNode *right;
 * TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Note:
 * <p>
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
 * Solutions:
 * 1. ，就有递归和非递归两种方法，最好两种方法都要掌握，都要会写。下面先来看递归的解法，由于是完全二叉树，所以若节点的左子结点存在的话，
 * 其右子节点必定存在，所以左子结点的next指针可以直接指向其右子节点，对于其右子节点的处理方法是，判断其父节点的next是否为空，若不为空，
 * 则指向其next指针指向的节点的左子结点，若为空则指向NULL，代码如下：
 * Credit:http://www.cnblogs.com/grandyang/p/4288151.html
 */
public class No116_Populating_Next_Right_Pointers_in_Each_Node {


    //    此处需要一个通解@Queue
//    对于非递归的解法要稍微复杂一点，但也不算特别复杂，需要用到queue来辅助，由于是层序遍历，每层的节点都按顺序加入queue中，
// 而每当从queue中取出一个元素时，将其next指针指向queue中下一个节点即可。代码如下：
    public void connect_common(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        while (true) {
            TreeLinkNode curt = queue.peek();
            queue.poll();
            if (curt != null) {
                curt.next = queue.peek();
                if (curt.left != null) queue.offer(curt.left);
                if (curt.right != null) queue.offer(curt.right);
            } else {
                if (queue.size() == 0 || queue.peek() == null) return;
                //此处会把前次被指的右节点的内容入队列
                queue.offer(null);
            }
        }

    }

    // 1. ，就有递归和非递归两种方法，最好两种方法都要掌握，都要会写。下面先来看递归的解法，由于是完全二叉树，所以若节点的左子结点存在的话，
    // 其右子节点必定存在，所以左子结点的next指针可以直接指向其右子节点，对于其右子节点的处理方法是，判断其父节点的next是否为空，若不为空，
    // 则指向其next指针指向的节点的左子结点，若为空则指向NULL，代码如下：

    public void connect_recursive(TreeLinkNode root) {
        if (root == null) return;

        if (root.left != null) root.left.next = root.right;
        if (root.right != null) root.right.next = root.next == null ? null : root.next.left;
        connect_recursive(root.left);
        connect_recursive(root.right);
    }


    //Solution:2.，，题目中要求用O(1)的空间复杂度，所以我们来看下面这种碉堡了的方法。用两个指针start和cur，
    // 其中start标记每一层的起始节点，cur用来遍历该层的节点，设计思路之巧妙，不得不服啊
    //横着走的话,就是链表,通过上层的指针,完成下层next指针指向
    public void connect_non_recursive(TreeLinkNode root) {
        if (root == null) return;
        //中start标记每一层的起始节点
        TreeLinkNode start = root, curt = null;
        while (start.left != null) {

            curt = start;
            //cur用来遍历该层的节点，
            while (curt != null) {
                curt.left.next = curt.right;
                if (curt.next != null) curt.right.next = curt.next.left;
                curt = curt.next;
            }
            //中start标记每一层的起始节点
            start = start.left;
        }
    }


    public void connect(TreeLinkNode root) {
        if (root == null) return;
        connect(root.left);
        connect(root.right);
        TreeLinkNode left = root.left;
        TreeLinkNode right = root.right;
        while (left != null && right != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }
    }
}
