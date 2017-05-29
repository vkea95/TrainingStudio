package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 7/11/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * ****************************************************
 * Description:
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last
 * level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * ****************************************************
 * Ref:https://discuss.leetcode.com/topic/15533/concise-java-solutions-o-log-n-2
 * ****************************************************
 * Hindsight:
 * 1.完全不记得解题思路,basic keypoint就是,左右相等直接就可以用,左右不等,分别处理再相加
 * 2.网络答案的解题方法没有搞懂,为什么右侧高度等于H-1的时候, nodes +=1<<h, 右侧高度小于h-1时,需要 nodes += 1<< h-1
 * 3.推演下,或许明白了,算height的时候,总是走左侧路线,这个再结合完全二叉树的条件,就形成了,只有当首次?左右不一致的时候,才会出现不等的情况.
 * 4.似乎是No.3想象的那个样子,但是有不太确定呢,还要继续推导
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No222_Count_Complete_Tree_Nodes {

    public static void main(String[] args) {
        No222_Count_Complete_Tree_Nodes obj = new No222_Count_Complete_Tree_Nodes();
        System.out.println(1 << 6 - 1);
        System.out.println(1 << 6);
    }

    public int countNodes(TreeNode root) {
        int nodes = 0, h = height(root);
        while (root != null) {
            if (height(root.right) == h - 1) {
                //Tip: 1<<h
                nodes += 1 << h;
                root = root.right;
            } else {
                //Tip: (1<<h) -1
                nodes += 1 << h - 1;
                root = root.left;
            }
            h--;
        }
        return nodes;
    }


    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public int countNodes_slow(TreeNode root) {
        int h = height(root);
        return h < 0 ? 0 :
                height(root.right) == h - 1 ? (1 << h) + countNodes_slow(root.right)
                        : (1 << h - 1) + countNodes_slow(root.left);
    }
}
