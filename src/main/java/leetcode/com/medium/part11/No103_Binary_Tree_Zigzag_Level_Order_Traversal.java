package leetcode.com.medium.part11;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jason on 2016/3/12.
 * Location:
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * ************************************************************************
 * Description:
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * ****************************************************************************
 * Solution:
 * 题意读清楚后，比较明白啦，答案用的是2个stack来完成主要操作，一个是存着当前level所操作的node，另一个存着下次要操作的node
 * 想nextLevel放结点的时候，要有个可以自行反转的flag来处理先左后右，还是先右后左
 * ****************************************************************************
 * Bugs:
 * 在第一重循环外面，如果执行：
 * currLevel = nextLevel;
 * nextLevel.clear();
 * 那么currLevel和nextLevel都会被清空，所以需要将 nextLevel指到一个新的位置即tmp
 * 这个地方特别容易出问题的。。。今后要注意！！！
 */
public class No103_Binary_Tree_Zigzag_Level_Order_Traversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();

        if (root == null) {
            return rst;
        }

        Stack<TreeNode> currLevel = new Stack<>();
        Stack<TreeNode> nextLevel = new Stack<>();
        Stack<TreeNode> tmp;

        currLevel.push(root);
        boolean normalOrder = true;

        while (!currLevel.isEmpty()) {
            List<Integer> currLevelResult = new ArrayList<>();

            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                currLevelResult.add(node.val);

                if (normalOrder) {

                    if (node.left != null) nextLevel.push(node.left);

                    if (node.right != null) nextLevel.push(node.right);
                } else {

                    if (node.right != null) nextLevel.push(node.right);

                    if (node.left != null) nextLevel.push(node.left);
                }

            }

            rst.add(currLevelResult);

            //bug 1
            tmp = currLevel;
            currLevel = nextLevel;
            nextLevel = tmp;
            normalOrder = !normalOrder;


        }

        return rst;
    }
}
