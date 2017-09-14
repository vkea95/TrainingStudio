package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JianZhang on 9/13/17.
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class No103_Binary_Tree_Zigzag_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, root, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, TreeNode node, int index) {
        if (node == null) return;

        if (result.size() <= index) {
            result.add(index, new ArrayList<>());
        }
        if (index % 2 == 0) {
            result.get(index).add(node.val);
        } else {
            result.get(index).add(0, node.val);

        }
        helper(result, node.left, index + 1);
        helper(result, node.right, index + 1);


    }
}
