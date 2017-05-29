package leetcode.com.medium.part11;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/13.
 * Location:
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * ************************************************************************
 * Description:
 * Given inorder and postorder traversal of a tree, construct the binary tree
 * ************************************************************************
 * Solution:
 * 画一颗二叉树即可推导出来
 */
public class No106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return constructTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public int getPosition(int[] arr, int val, int start, int end) {
        int position = -1;
        for (int i = start; i <= end; i++) {
            if (arr[i] == val) {
                position = i;
                break;
            }
        }

        return position;
    }


    public TreeNode constructTree(int[] inOrder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd) {

        if (inStart > inEnd) return null;

        TreeNode root = new TreeNode(postOrder[postEnd]);

        int position = getPosition(inOrder, root.val, inStart, inEnd);

        //bug1:postOrder 的end：  postStart + (position - inStart)  -》 postStart + (position - inStart) - 1
        root.left = constructTree(inOrder, inStart, position - 1, postOrder, postStart, postStart + (position - inStart) - 1);
        //bug2:postOrder 的end：  postEnd  -》  postEnd - 1 如果不减一的话那么会永远取此树的根节点
        //bug3:因为bug1所以 右子树的开始结点也就算错了
        root.right = constructTree(inOrder, position + 1, inEnd, postOrder, postStart + (position - inStart), postEnd - 1);

        return root;
    }
}
