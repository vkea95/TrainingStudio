package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by JianZhang on 9/13/17.
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class No102_Binary_Tree_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, root, 0);
//        Collections.reverse(result);
        return result;
    }

    private void helper(List<List<Integer>> result, TreeNode node, int index) {
        if (node == null) return;

        if (result.size() <= index) {
            result.add(index, new ArrayList<>());
        }
        result.get(index).add(node.val);
        helper(result, node.left, index + 1);
        helper(result, node.right, index + 1);

    }
}
