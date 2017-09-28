package leetcode.com.tag.tree;

import leetcode.com.util.TreeLinkNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by JianZhang on 9/26/17.
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
 * Solutions:
 * 1.原本的完全二叉树的条件不再满足，但是整体的思路还是很相似，仍然有递归和非递归的解法。我们先来看递归的解法，
 * 这里由于子树有可能残缺，故需要平行扫描父节点同层的节点，找到他们的左右子节点。代码如下：
 * Credit:http://www.cnblogs.com/grandyang/p/4290148.html
 */
public class No117_Populating_Next_Right_Pointers_in_Each_Node_II {

    //    此处需要一个通解@Queue
//    对于非递归的解法要稍微复杂一点，但也不算特别复杂，需要用到queue来辅助，由于是层序遍历，每层的节点都按顺序加入queue中，
// 而每当从queue中取出一个元素时，将其next指针指向queue中下一个节点即可。代码如下：
    public void connect_common(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);
        while (true) {
            TreeLinkNode curt = queue.poll();
            if (curt != null) {
                curt.next = queue.peek();
                if (curt.left != null) queue.offer(curt.left);
                if (curt.right != null) queue.offer(curt.right);
            } else {
                if (queue.size() == 0 || queue.peek() == null) return;
                queue.offer(null);
            }
        }

    }

    public void connect(TreeLinkNode root) {
        // head是上一层的节点，我们用上一层节点的next形成链表，来链接当前这层
        TreeLinkNode head = root;
        while (head != null) {
            // 记录链接到哪个节点的额外指针
            TreeLinkNode curr = new TreeLinkNode(0);
            // tmp指向该层的第一节点
            TreeLinkNode tmp = curr;
            while (head != null) {
                // 尝试链接左节点
                if (head.left != null) {
                    curr.next = head.left;
                    curr = curr.next;
                }
                // 尝试链接右节点
                if (head.right != null) {
                    curr.next = head.right;
                    curr = curr.next;
                }
                head = head.next;
            }
            // 将head移动到当前这层的的第一个，准备链接下一层
            head = tmp.next;
        }

    }
}
