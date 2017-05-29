package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 7/17/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/path-sum-ii/
 * ****************************************************
 * Description:
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
 * ****************************************************
 * Time: 10 mins
 * Beat: 11%
 * Bug:0
 * ****************************************************
 * Hindsight:
 * 1.之前刚做过的题目,思路成熟
 * 2.对于叶子节点的定位,比较清楚,即左右节点都为null,才是工作对象,
 * 3.不必在调用左右节点的时候,判断root是否为null,因为方法入口已经判断了
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No113_Path_Sum_II {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        helper(result, solution, root, sum);
        return result;
    }


    private void helper(List<List<Integer>> result, List<Integer> solution, TreeNode root, int sum) {
        if (root == null) return;

        solution.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == root.val) result.add(new ArrayList<>(solution));
        }

        helper(result, solution, root.left, sum - root.val);
        helper(result, solution, root.right, sum - root.val);
        solution.remove(solution.size() - 1);

    }
}
