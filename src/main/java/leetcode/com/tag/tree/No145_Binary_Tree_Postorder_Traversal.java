package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by JianZhang on 9/13/17.
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [3,2,1].
 * <p>
 * Note: Recursive solution is trivial, could you do it iteratively?
 * Bugs:
 * 1. 完全fail,没有思路
 * 2. 借用原来的解法
 * <p>
 * 非递归的方法略难，需要curr和prev两个variable来作为指示，进行区分是否为兄弟伙父子关系，
 * (prev,left==curr||prev.right==curr):前一节点是否为当前节点的父亲？
 * (curr.left==prev)?当前节点是否为前一节点的父亲
 * 每次入栈，只push一个节点，每次先peek，当prev和curr为同一个结点的时候，再pop将结点加入结果集
 * <p>
 * Credit:http://www.cnblogs.com/TenosDoIt/p/3416835.html
 */
public class No145_Binary_Tree_Postorder_Traversal {
    public List<Integer> postorderTraversal(TreeNode root) {
//    Algorithm:非递归使用栈。首先把根节点压栈，然后循环如下操作：用一个变量来记录上次访问的节点，如果当前栈顶元素左右儿子都为空 或者
// 上次访问的节点非空且等于栈顶节点的左儿子或右儿子，则直接访问；否则把栈顶元素的右节点和左节点依次压栈。代码如下：
        List<Integer> rst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
//        用一个变量来记录上次访问的节点
        TreeNode prev = null;
        if (root != null) stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();
//            如果当前栈顶元素左右儿子都为空-->叶子节点
//            或:上次访问的节点非空且等于栈顶节点的左儿子或右儿子，则直接访问；-->写个后续遍历即可知
            if ((curr.left == null && curr.right == null) ||
                    (prev != null && (prev == curr.left || prev == curr.right))) {
                //
                rst.add(stack.pop().val);
            } else {
//                否则把栈顶元素的右节点和左节点依次压栈---->因为栈的处理顺序是后入先出,所以要先入右节点
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }
            prev = curr;
        }

        return rst;
    }

    public List<Integer> postorderTraversal_2(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode curr = root;

        if (root == null) return rst;

        stack.push(root);

        while (!stack.isEmpty()) {
            curr = stack.peek();
            //traverse down the tree
            //此时的判断条件时,当前节点曾经是前一个被处理节点的左孩子或右孩子,则代表该节点还可以入栈。。。
            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                }
            } else if (curr.left == prev) {
                //traverse up the tree from the left
                if (curr.right != null) stack.push(curr.right);
            } else {
                rst.add(curr.val);
                stack.pop();
            }
            prev = curr;
        }
        return rst;

    }
}
