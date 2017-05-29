package leetcode.com.pickup1.easy;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 9/21/16.
 * ****************************************************
 * Location
 * https://leetcode.com/problems/binary-tree-paths/
 * ****************************************************
 * Description:
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * For example, given the following binary tree:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * All root-to-leaf paths are:
 * <p>
 * ["1->2->5", "1->3"]
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No257_Binary_Tree_Paths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        helper(result, root, new String());
        return result;
    }

    private void helper(List<String> result, TreeNode root, String str) {
        if (root == null) return;
        str += root.val;
        if (root.left == null && root.right == null) {
            result.add(str);
            return;
        }
        helper(result, root.left, str + "->");
        helper(result, root.right, str + "->");
    }
}
