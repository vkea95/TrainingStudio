package leetcode.com.medium;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jason on 2016/3/12.
 *
 */
public class test {

    public static void main(String[] args) {
        test tt = new test();
//        TreeNode root = new TreeNode(1);

//        tt.zigzagLevelOrder(root);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();

        if (root == null) return rst;

        Stack<TreeNode> currLevel = new Stack<>();
        Stack<TreeNode> nextLevel = new Stack<>();
        Stack<TreeNode> tmp = new Stack<>();
        boolean zigzagFlg = true;

        currLevel.push(root);

        while (!currLevel.isEmpty()) {
            List<Integer> curRst = new ArrayList<>();
            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                curRst.add(node.val);

                if (zigzagFlg) {
                    if (node.left != null) nextLevel.push(node.left);
                    if (node.right != null) nextLevel.push(node.right);
                } else {
                    if (node.right != null) nextLevel.push(node.right);
                    if (node.left != null) nextLevel.push(node.left);

                }

            }

            rst.add(curRst);
            currLevel = nextLevel;
            nextLevel.clear();

            zigzagFlg = !zigzagFlg;
        }

        return rst;
    }
}
