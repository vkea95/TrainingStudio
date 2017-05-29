package leetcode.com.medium.part12;

import leetcode.com.util.TreeNode;

import java.util.*;

/**
 * Created by jason on 2016/4/3.
 * Location:
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * *************************************************************
 * Description:
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes
 * you can see ordered from top to bottom.
 * For example:
 * Given the following binary tree,
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * You should return [1, 3, 4].
 * *************************************************************
 * Solution:
 * 想到的是HashMap，按层放结点，新的覆盖旧的---》可以使用DFS递归解决，也很精简
 * 网上的答案：This problem can be solve by using a queue. On each level of the tree, we add the right-most
 * element to the results.
 * 用到的这个解法很经典，曾经解过其他的问题，考虑作为解题模板留下来，解决同层问题还是要靠队列啊
 */
public class No199_Binary_Tree_Right_Side_View {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (queue.size() > 0) {
            int size = queue.size();
            //当前层中的首个结点
            TreeNode node = queue.peek();
            result.add(node.val);
            for (int i = 0; i < size; i++) {
                TreeNode top = queue.poll();
                if (top.right != null) queue.add(top.right);
                if (top.left != null) queue.add(top.left);
            }
        }
        return result;
    }

    public List<Integer> rightSideView_recursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int depth = 0;
        dfs(root, map, depth);

        while (map.containsKey(depth)){
            result.add(map.get(depth++));
        }
        return result;
    }

    public void dfs(TreeNode root, HashMap<Integer, Integer> map, int depth) {
        if (root == null) return;
        map.put(depth, root.val);
        dfs(root.left, map, depth + 1);
        dfs(root.right, map, depth + 1);
    }
}
