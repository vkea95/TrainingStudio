package leetcode.com.medium.part02;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/2/25.
 * Location：
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 * *************************************************************
 * Description:
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * OJ's Binary Tree Serialization:
 * The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
 * Here's an example:
 * 1
 * / \
 * 2   3
 * /
 * 4
 * \
 * 5
 * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 * ***************************************************************
 * Solution:
 */
public class No095_Unique_Binary_Search_Trees_II {
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) return new ArrayList<>();
        return generate(1, n);
    }

    private List<TreeNode> generate(int start, int end) {
        List<TreeNode> rst = new ArrayList<>();

        if (start > end) {
            rst.add(null);
            return rst;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generate(start, i - 1);
            List<TreeNode> right = generate(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    rst.add(root);
                }
            }

        }
        return rst;
    }

}

class Solution {
    private int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums[0] >= target) return -1;
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid;
            }

        }
        return left;
    }

    public int[] searchRange(int[] nums, int target) {

        int[] result = {-1, -1};

        int left = binarySearch(nums, target);
        int right = binarySearch(nums, target + 1);
        if (left < right) {
            result[0] = left + 1;//the next one is the answer
            result[1] = right;
        }
        return result;
    }
}