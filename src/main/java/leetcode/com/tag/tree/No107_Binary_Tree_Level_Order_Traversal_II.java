package leetcode.com.tag.tree;

import leetcode.com.util.ListNode;
import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by JianZhang on 9/25/17.
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * Solutions:
 * 1. 可以用No102的算法,然后用Collections.reverse(result);逆序就可以了--->很多关于list,collection类型的操作方法都被放入了collections中!!!要试着尝试这个方法。
 * 2. 可以用一个队列来进行处理,
 * 2.1 先把root放入队里,
 * 2.2 进入while循环,
 * 2.2.1 记录当前的queue的size--->表示某层有多少个节点
 * 2.2.2 for 循环前步取得的size
 * 2.2.2.1 将peek的节点的左右非空孩子入队列
 * 2.2.2.2 将peek的节点插入到子结果集
 * 2.2.3 将子结果集放到result list的第零层
 */
public class No107_Binary_Tree_Level_Order_Traversal_II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) queue.offer(root);

        while (!queue.isEmpty()) {
            int currentSize = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < currentSize; i++) {
                TreeNode curtNode = queue.poll();
                if (curtNode.left != null) queue.offer(curtNode.left);
                if (curtNode.right != null) queue.offer(curtNode.right);
                subList.add(curtNode.val);
            }

            result.add(0, subList);
        }

        return result;
    }
}