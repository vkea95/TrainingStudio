package leetcode.com.medium;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/4/3.
 * Location：
 * https://leetcode.com/problems/house-robber-iii/
 * ******************************************************
 * Description：
 * he thief has found himself a new place for his thievery again. There is only one entrance to this area,
 * called the "root." Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * Example 1:
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 * ******************************************************
 * Solution：
 * 考虑就是是否取同层的，选root或不选root这样的2个方案中2选1，但是不能简单根据奇偶来划分2个方案
 * 这个需要注意的地方跟House_robber_I类似，不能死盯奇偶，而是要取max。。。
 */
public class No337_House_Robber_III {
    public int rob(TreeNode root) {
        int[] result = helper(root);
        return Math.max(result[0], result[1]);
    }

    public int[] helper(TreeNode root) {
        int[] result = new int[2];
        if (root == null) return result;

        int[] left = helper(root.left);
        int[] right = helper(root.right);

        //resutlt[0] is when root is selected, result[1] is when not
        result[0] = root.val + left[1] + right[1];
        result[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return result;

    }
}
