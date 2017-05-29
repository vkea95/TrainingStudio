package leetcode.com.pickup1.easy;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 8/14/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * ****************************************************
 * Description:
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
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
 * <p>
 * ****************************************************
 * Thoughts:
 * 1.问题的关键,要把value放到正确的层数中
 * ****************************************************
 * Time: 10 mins
 * Beat: 85%
 * Bug: 0
 * ***************************************************
 * Hindsight:
 * 1.递归处理
 * 2.增加层数判断
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No102_Binary_Tree_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        helper(result, root, 0);
        return result;

    }

    private void helper(List<List<Integer>> result, TreeNode root, int level) {
        if (root == null) return;
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);
        helper(result, root.left, level + 1);
        helper(result, root.right, level + 1);


    }
}
