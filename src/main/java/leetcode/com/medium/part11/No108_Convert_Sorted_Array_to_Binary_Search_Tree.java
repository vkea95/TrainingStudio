package leetcode.com.medium.part11;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/12.
 * Location：
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * *******************************************************************
 * Description:
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * *******************************************************************
 * Concepts:
 * 二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
 * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
 */
public class No108_Convert_Sorted_Array_to_Binary_Search_Tree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return makeTree(nums, 0, nums.length - 1);
    }

    private TreeNode makeTree(int[] nums, int start, int end) {
        if (start == end) {
            return new TreeNode(nums[start]);
        } else if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = makeTree(nums, start, mid - 1);
        root.right = makeTree(nums, mid + 1, end);
        return root;
    }

}
