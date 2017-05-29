package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 7/16/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * ****************************************************
 * Description:
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 * How would you optimize the kthSmallest routine?
 * ****************************************************
 * Thought:
 * 1.开始的时候,没有想到答案,后来看了答案才知道该怎么整,其实自己是可以搞定的,就是中序遍历,先让最小的进去,然后是根,最后是右子树
 * 2.递归的写法比较简单,但是想尝试while循环的方式
 * 它是先一路向左压入栈中,然后出栈,然后压入右节点,然后继续压入左节点
 * ref:
 * https://segmentfault.com/a/1190000003704692
 * ****************************************************
 * 后续 Follow Up
 * 这题的难点其实在于Follow Up：如果我们频繁的操作该树，并且频繁的调用kth函数，有什么优化方法使时间复杂度降低至O(h)？h是树的高度。
 * 根据提示，我们可以在TreeNode中加入一个rank成员，这个变量记录的是该节点的左子树中节点的个数，其实就是有多少个节点比该节点小。
 * 这样我们就可以用二叉树搜索的方法来解决这个问题了。这个添加rank的操作可以在建树的时候一起完成。
 * ****************************************************
 * Hindsight:
 * 1.在白板上写,还是有问题的。思路会乱入。目前有个解法,就是先梳理可能的解法,例如preOrder,inOrder,postOrder等等,然后,再写上经典的解法,
 * 再考虑是否可以增加一个数据结构或略微修改下程序结构,达到解决问题的目的。
 * 2.基本遍历方式的递归和非递归的实现:http://robinsoncrusoe.iteye.com/blog/808526
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No230_Kth_Smallest_Element_in_a_BST {
//    Stack<TreeNode> stack = new Stack<>();

    public void postOrder_BST(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode prev = root;
        while (!s.isEmpty() && root != null) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }

            if (!s.isEmpty()) {
                TreeNode temp = s.peek().right;
                if (temp == null || temp == prev) {
                    TreeNode p = s.peek();
                    prev = p;
                    root = null;
                } else {
                    root = temp;
                }
            }
        }

    }

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        while (root != null || !s.isEmpty()) {

            while (root != null) {
                s.push(root);
                root = root.left;
            }
            if (!s.isEmpty()) {
                root = s.pop();
                k--;
                if (k == 0) return root.val;
                root = root.right;
            }

        }

        return 0;
    }


    public int kthSmallest_non_recursive(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        while (root != null) {
            s.push(root);
            root = root.left;
        }

        while (!s.isEmpty()) {
            TreeNode curt = s.pop();
            k--;
            if (k == 0) return curt.val;
            curt = curt.right;
            while (curt != null) {
                s.push(curt);
                curt = curt.left;
            }
        }
        return 0;
    }

    public int kthSmallest_zj(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        helper(root, stack, k);
        if (stack.size() == k) return stack.peek().val;
        return -1;
    }

    private void helper(TreeNode root, Stack<TreeNode> stack, int k) {
        if (root == null) return;
        helper(root.left, stack, k);
        if (stack.size() == k) return;
        stack.push(root);
        helper(root.right, stack, k);
    }

    public int kthSmallest_zj_white_board__01(TreeNode root, int k) {
        int result = 0;
        Stack<TreeNode> stack = new Stack<>();
        helper(root, stack);
        while (!stack.isEmpty() && k >= 0) {
            TreeNode node = stack.pop();
            k--;
            if (k == 0) {
                result = node.val;
                break;
            }
            helper(node.right, stack);
        }
        return result;
    }

    private void helper(TreeNode root, Stack<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /*
    此解法认为第一个叶子节点就是最小的节点,没有考虑root只有右节点的情况
     */
    public int kthSmallest_wrong(TreeNode root, int k) {
        int[] result = new int[2];
        boolean[] flg = new boolean[1];
        result[0] = k;
        helper(root, result, flg);
        return result[1];
    }

    private void helper(TreeNode root, int[] result, boolean[] flg) {
        if (root == null) return;
        if (root.left == null && root.right == null && flg[0] == false) {
            flg[0] = true;
        }
        helper(root.left, result, flg);
        if (flg[0]) {
            result[0]--;
            if (result[0] == 0) {
                result[1] = root.val;
            }
        }
        if (result[0] <= 0) return;

        helper(root.right, result, flg);
    }
}
