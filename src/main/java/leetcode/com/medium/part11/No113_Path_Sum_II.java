package leetcode.com.medium.part11;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/3/13.
 * Location:
 * https://leetcode.com/problems/path-sum-ii/
 * ********************************************
 * Description:
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
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
 * ***************************************************
 * Solution:
 * 肯定要用递归，那么就套用经典的递归模板，传入结果list和solution List，然后在后者中增加元素
 * 但是叶子节点要单独处理
 * ********************************************
 * Bug:
 * 1.要熟悉递归的template
 * 2.叶子节点要单独处理
 */
public class No113_Path_Sum_II {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {


        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();

        findPath(result, solution, root, sum);
        return result;
    }

    private void findPath(List<List<Integer>> result, List<Integer> solution, TreeNode root, int sum) {
        if (root == null) return;

        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                //bug1: forget to add/remove the leaf node
                solution.add(root.val);
                result.add(new ArrayList<>(solution));
                solution.remove(solution.size() - 1);
            }
            return;
        }

        sum -= root.val;
        solution.add(root.val);
        findPath(result, solution, root.left, sum);
        findPath(result, solution, root.right, sum);
        solution.remove(solution.size() - 1);

    }
}
