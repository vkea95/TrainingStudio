package leetcode.com.hard.part0;

import leetcode.com.util.TreeNode;

/**
 * Created by jason on 2016/3/12.
 * Location:
 * https://leetcode.com/problems/recover-binary-search-tree/
 * ***********************************************
 * Description:
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * ************************************************
 * Solution:
 * 1.遍历是必须的，中序遍历比较直观易懂
 * 2总的来说，判断是不是BST还是中序遍历最方便，但是题中说道O(n)复杂度太easy，要求开常量空间。
 * 具体的思路，还是通过中序遍历，只不过，不需要存储每个节点，只需要存一个前驱即可。
 * ************************************************
 * Tips:
 * Second 元素是从root取得的，这个要推导下才能得出，画个树状图就可以理解啦。当时将lastElement设置给first和second是没有推导所致
 * 即使是2个结点那么一推导也就直到root要付给second啦啦啦啦啦
 */
public class No099_Recover_Binary_Search_Tree {

    private TreeNode firstEle = null;
    private TreeNode secondEle = null;
    private TreeNode lastEle = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        recursive(root);

        int tmp = firstEle.val;
        firstEle.val = secondEle.val;
        secondEle.val = tmp;
    }

    private void recursive(TreeNode root) {
        if (root == null) {
            return;
        }
        recursive(root.left);
        if (firstEle == null && root.val < lastEle.val) {
            firstEle = lastEle;
        }
        if (firstEle != null && root.val < lastEle.val) {
            secondEle = root;
            System.out.println("1");
        }
        lastEle = root;
        recursive(root.right);

    }
}
