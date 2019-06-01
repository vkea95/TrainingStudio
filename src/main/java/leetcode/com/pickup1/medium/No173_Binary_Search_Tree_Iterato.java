package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 7/23/16.
 * *****************************************************************************
 * Location:
 * https://leetcode.com/problems/binary-search-tree-iterator/
 * *****************************************************************************
 * Description:
 * Implement an returnList over a binary search tree (BST). Your returnList will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * *****************************************************************************
 * Thought:
 * 1.空间是O(h),要遍历整棵树,寻找nextsmallest number,
 * 2.画图得到,这样的话就是中序遍历咯
 * ___5
 * _2___8
 * 1_3_7__9
 * 从根部,left child的方式压栈,一旦出栈,立刻判断是否有right child,若有则压栈,否则不做任何操作
 * *****************************************************************************
 * Time: 13 mins
 * Beat: 18%
 * Bug: 1
 * *****************************************************************************
 * Hindsight:
 * 1.问题还是在于,推演不够,再新插入节点的时候,没有进行过的推演啊,当然是要推演了!!!
 * 2.插入右节点也相当于插入一棵树呢
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 */
public class No173_Binary_Search_Tree_Iterato {
}

class BSTIterator {

    Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        helper(root);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();

    }

    /**
     * @return the next smallest number
     */
    public int next() {
        if (!stack.isEmpty()) {

            TreeNode node = stack.pop();
            //bug1:右节点插入后,还要继续向左寻找哦
            helper(node.right);
            return node.val;
        } else {
            return -1;
        }

    }

    private void helper(TreeNode root) {
        TreeNode node = root;

        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
