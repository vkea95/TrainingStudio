package leetcode.com.tag.tree;

import leetcode.com.util.ListNode;
import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JianZhang on 9/10/17.
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * Thoughts:
 * 1. 先从1个节点的树开始构建,然后逐渐添加节点---只要是子节点为空的都可以添加,
 * 2. 所以需要滴BST进行遍历处理,clone处理节点
 * 3.
 * --->以上想法,没有问题,但是没有想明白该如何递归实现。
 * 4. 参考答案用的是递归分别生成左右子树的方式,来解决问题。
 */
public class No095_Unique_Binary_Search_Trees_II {
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) return new ArrayList<>();
        return generateSubTrees(1, n);
    }

    private List<TreeNode> generateSubTrees(int start, int end) {

        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);//处理空的子节点问题,后面还要添加这个null,来进行判断呢,而且list课可以加入null元素!!!
            return list;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generateSubTrees(start, i - 1);
            List<TreeNode> right = generateSubTrees(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    list.add(root);
                }
            }
        }


        return list;
    }
}
