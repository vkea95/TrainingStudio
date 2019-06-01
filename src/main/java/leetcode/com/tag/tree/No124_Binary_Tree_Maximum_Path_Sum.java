package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

/**
 * Created by JianZhang on 9/27/17.
 * Given a binary tree, find the maximum path sum.
 * <p>
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node
 * in the tree along the parent-child connections. The path must contain at least one node
 * and does not need to go through the root.
 * For example:
 * Given the below binary tree,
 * 1
 * / \
 * 2   3
 * Return 6.
 * Solutions:
 * 1. 就说这个路径有可能经过最上面的root,也有可能不经过这个root,当然也可以推广为不一定经过某个节点
 */
public class No124_Binary_Tree_Maximum_Path_Sum {
//    credit:http://www.cnblogs.com/grandyang/p/4280120.html
//    我们先来看一个简单的例子：
//
//            4
//            / \
//            11 13
//            / \
//            7  2
//
//    对于一条路径来说，可以分为两种情况，一是当顶节点是当前点，另一种是顶节点是父节点。例如，对于节点11来说，
//
//            1. 当顶节点是当前节点，对于节点11，路径为 7->11->2
//
//            2. 当顶节点是父节点4时，对于节点11，路径为 7->11->4->13
//
//    对于DFS来说，其递归过程中必定会对某节点的子节点调用，那么其返回值应该适用于上面第二种情况，
// 但是最终结果肯定是第一种情况，那么如果保存并更新最终结果呢，我们可以将其放在参数中传递。那么对于任意一个节点n来说，
//
//    dfsLeafNodes(n) = max(dfsLeafNodes(n->left) + n->val, dfsLeafNodes(n->right) + n->val, n->val);
//
//    top(n) = max(dfsLeafNodes(n), dfsLeafNodes(n->left) + dfsLeafNodes(n->right) + n->val, n->val);
//
//    res = max(res, top(n));
//
//想法太赞啦!!!!
    public int maxPathSum(TreeNode root) {
        int[] res = new int[]{root.val};
        helper(root, res);
        return res[0];
    }

    private int helper(TreeNode root, int[] res) {
        if (root == null) return 0;
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        //root作为顶节点的情况,也就是说把某个不含有最上层根的路径的值给记录下来了
        int top = root.val + (left > 0 ? left : 0) + (right > 0 ? right : 0);
        res[0] = Math.max(res[0], top);
//        选取某个分支,或是当前节点作为备选路径的case,此时value作为返回值出现。
        return Math.max(left, right) > 0 ? Math.max(left, right) + root.val : root.val;
    }
}
