package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 6/27/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/house-robber-iii/
 * ****************************************************
 * Description:
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area,
 * called the "root." Besides the root, each house has one and only one parent house. After a tour,
 * the smart thief realized that "all houses in this place forms a binary tree". It will automatically
 * contact the police if two directly-linked houses were broken into on the same night.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * <p>
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
 * <p>
 * ****************************************************
 * Thoughts:
 * 根据提示,他就只能隔层偷钱.就只要算奇数层和偶数层的数值和,然后取最大值即可.-->但实际测试并非如此哦
 * 所以这个算法该定义为啥样子呢
 * 看情形,会有2种情况,1.含root,2不含root
 * 这个需要注意的地方跟House_robber_I类似，不能死盯奇偶，而是要取max。。。
 * 所以在取子节点的较大值的时候,要用到一次比较
 * ****************************************************
 * Time: 30 min
 * Beat:53%
 * Bug: 0
 * ****************************************************
 * Hindsight:
 * 还是没有完全领会这道题的解法,不然就不会想不到解法了,在处理选择子节点还是,孙节点的问题上要好好反省
 * 如何才能通过代码完成我们既定的目标
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No337_House_Robber_III {
    public int rob(TreeNode root) {
        int[] rst = dfs(root);

        return Math.max(rst[0], rst[1]);
    }

    private int[] dfs(TreeNode root) {
        int[] rst = new int[2];
        if (root == null) return rst;

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        rst[0] = root.val + left[1] + right[1];
        rst[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return rst;


    }
}
