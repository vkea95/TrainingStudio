package leetcode.com.medium.part11;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/12.
 * Location:
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * ***************************************************
 * Description:
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * ***************************************************
 * Solution:
 * 常识
 * 1.preOrder首元素为root
 * 2.根据一颗二叉树，写出它的inOrder和preOrder遍历，会发现：
 * 2.1 InOrder中root的左侧为左子树，右侧为右子树
 * 2.2 PreOrder中会发现其左子树就在其右侧第一个元素，右子树位于左子树全体结构后的第一个元素--》可根据preOrder的数据算出
 * -->以此确定数组扫描范围
 * 3.每次处理左右子树的时候都要将范围扩展成扫描整棵子树
 * 4.InOrder的起始下标比较容易推导，PreOrder的推导起来略显麻烦，必须将某棵子树的两种序列都写出来后，再进行推导
 * 关键就是要用PreOrder来定位每个小子树的root位置
 * 5. InOrder在这里起到了一个下标推导中介的用途，这俩数组的开始和结束下标都是必不可少的。。。
 * ZTMD难推啊啊啊啊啊啊啊！！！！！
 * ***************************************************
 * Tips:
 * 我需要将三种遍历的概念搞清楚啊！！！
 * concepts:
 * inOrder: left-root-right
 * preOrder: root-left-right
 */
public class No105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {

    private int findPos(int[] arr, int start, int end, int key) {
        int i;
        for (i = start; i <= end; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    private TreeNode consruct(int[] inorder, int inStart, int inEnd, int[] preOrder, int preStart, int preEnd) {
        if (inStart > inEnd) return null;

        TreeNode root = new TreeNode(preOrder[preStart]);
        int position = findPos(inorder, inStart, inEnd, preOrder[preStart]);

        root.left = consruct(inorder, inStart, position - 1, preOrder, preStart + 1, preStart + (position - inStart));

        root.right = consruct(inorder, position + 1, inEnd, preOrder, preEnd + (position - inEnd) + 1, preEnd);

        return root;

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode(1);

        if (inorder.length != preorder.length) return null;

        return consruct(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }

}
