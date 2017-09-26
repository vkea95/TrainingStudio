package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JianZhang on 9/25/17.
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * return
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 */
public class No113_Path_Sum_II {
    List<List<Integer>> result;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new ArrayList<>();
        List<Integer> subSet = new ArrayList<>();
        helper(root, sum, subSet);
        return result;
    }


    private void helper(TreeNode root, int sum, List<Integer> subSet) {
        if (root == null) return;
        subSet.add(root.val);
        if (root.left == null && root.right == null) {
            if ((sum - root.val) == 0) {
                result.add(new ArrayList<>(subSet));
            }
        }

        helper(root.left, sum - root.val, subSet);
        helper(root.right, sum - root.val, subSet);
        subSet.remove(subSet.size() - 1);
    }
}
