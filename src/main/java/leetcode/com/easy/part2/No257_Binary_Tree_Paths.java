package leetcode.com.easy.part2;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 4/30/16.
 * *************************************************
 * Location:
 * https://leetcode.com/problems/binary-tree-paths/
 * *************************************************
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
 * *************************************************
 * Analysis:
 * 递归解决问题,遇到leaf,就结束,否则就在后面
 * *************************************************
 * Bug:
 * 因为要记录路径,所以传送的String对象有可能会在多次递归中被当做同一个对象使用
 * *************************************************
 */
public class No257_Binary_Tree_Paths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> rst = new ArrayList<>();

        if (root == null) return rst;
        pathHelper(root, rst, "");
        return rst;
    }

    public void pathHelper(TreeNode root, List<String> paths, String path) {
        path += root.val;
        if (root.left != null) {
            pathHelper(root.left, paths, path + "->");
        }
        if (root.right != null) {
            pathHelper(root.right, paths, path + "->");

        }
        if (root.right == null && root.left == null) {
            paths.add(path);
        }

    }
}
