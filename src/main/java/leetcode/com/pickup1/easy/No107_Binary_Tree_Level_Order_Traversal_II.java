package leetcode.com.pickup1.easy;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by tclresearchamerica on 6/15/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * ****************************************************
 * Description:
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right,
 * level by level from leaf to root).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * ****************************************************
 * Thoughts:
 * preorder,inorder & postorder
 * ****************************************************
 * Hindsight:
 * 1.递归的做法是:正常放,但是出来后,要进行一次交换,即将list逆序
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No107_Binary_Tree_Level_Order_Traversal_II {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        travelTree(0, root, result);
        List<List<Integer>> reverseRst = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            reverseRst.add(result.get(result.size()-i-1));
        }
        return reverseRst;
    }

    public List<List<Integer>> levelOrderBottom_binary(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        travelTree(0, root, result);
        int mid = (result.size() - 1) / 2;
        int end = result.size() - 1;
        //bug3:不判断end<=0,那么就会发生index overflow
        if (end <= 0) return result;
        //bug4:i<mid --> i<=mid 导致2个元素的result,处理顺序不正确
        for (int i = 0; i <= mid; i++) {

            List<Integer> tmp = new ArrayList<>(result.get(i));
            result.set(i, result.get(end - i));
            result.set(end - i, tmp);
        }
        return result;
    }

    private void travelTree(int level, TreeNode root, List<List<Integer>> result) {
        if (root == null) return;

        if (result.size() <= level) result.add(new ArrayList<>());
        //bug1:导致逆序,
        result.get(level).add(root.val);
        travelTree(level + 1, root.left, result);
        travelTree(level + 1, root.right, result);
        //bug2:导致节点被插入了错误的位置,因为list还是会增长的所以,只有当所有的节点都遍历完毕,才可以确定正确的相对位置,
//        result.get(level).add(root.val);
//        result.get(result.size()-level-1).add(root.val);
    }

    public List<List<Integer>> levelOrderBottom_NonResurvisive(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if(root == null) return wrapList;

        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(0, subList);
        }
        return wrapList;
    }
}
