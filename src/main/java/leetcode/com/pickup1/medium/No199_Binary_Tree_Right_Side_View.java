package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 9/27/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * ****************************************************
 * Description:
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes
 * you can see ordered from top to bottom.
 * <p>
 * For example:
 * Given the following binary tree,
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * You should return [1, 3, 4].
 * <p>
 * Credits:
 * Special thanks to @amrsaqr for adding this problem and creating all test cases.
 * ****************************************************
 * Bug: list需要的是remove+add 而不是只会用add
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No199_Binary_Tree_Right_Side_View {

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();
        result.add(0, 1);

    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        helper(root, 0, result);
        return result;

    }

    private void helper(TreeNode root, int index, List<Integer> result) {

        if (root == null) return;
        //bug1:indexList 的add处理，不等于它replace 所以需要额外的判断处理呢
        if (result.size() > index) {
            result.remove(index);
        }
        result.add(index, root.val);
        helper(root.left, index + 1, result);
        helper(root.right, index + 1, result);

    }

}
