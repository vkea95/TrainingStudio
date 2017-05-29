package leetcode.com.pickup1.medium;

import apple.laf.JRSUIUtils;
import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 9/14/16.
 * *************************************************************
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
 * *************************************************************
 * Thought:
 * 1.BST:有其自己的规则,root节点分别大于左节点及其子节点,小于右节点及其子节点
 * *************************************************************
 * Hindsight:
 * 1.意识到是递归了,但是没有总结出做法来,看了答案后,才发现做法比较标准和简单。
 * 2.递归函数,生成树,分别生成左,右和root节点,然后再拼装在一起
 * 3.需要给resultList,加装null,否则处理单个节点会出现问题
 * *************************************************************
 * *************************************************************
 */
public class No095_Unique_Binary_Search_Trees_II {
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) return new ArrayList<>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            //bug1: need to add null to result list, otherwise, nothing to add the recursive
            result.add(null);
            return result;

        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTree = helper(start, i - 1);
            List<TreeNode> rightTree = helper(i + 1, end);
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    result.add(node);
                }
            }
        }
        return result;
    }

}
