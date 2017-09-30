package leetcode.com.tag.dfs;

import edu.princeton.cs.algs4.In;
import leetcode.com.util.ListNode;
import leetcode.com.util.TreeNode;

import java.util.*;

/**
 * Created by JianZhang on 9/29/17.
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * For example:
 * Given the following binary tree,
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * You should return [1, 3, 4].
 */
public class No199_Binary_Tree_Right_Side_View {

    //非递归方法,就是要用队列,可以使用同层遍历法
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.offer(root);

        int layer = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curt = queue.poll();
                if (layer < list.size()) {
                    list.set(layer, curt.val);
                } else {
                    list.add(curt.val);
                }

                if (curt.left != null) queue.offer(curt.left);
                if (curt.right != null) queue.offer(curt.right);
            }
            layer++;

        }
        return list;
    }

    public List<Integer> rightSideView_recursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, 0, result);
        return result;

    }

    //preorder, then new one overwrite the old one
    private void helper(TreeNode root, int layer, List<Integer> result) {
        if (root == null) return;

        if (layer < result.size()) {
            result.set(layer, root.val);
        } else {
            result.add(root.val);
        }

        helper(root.left, layer + 1, result);
        helper(root.right, layer + 1, result);
    }


}
