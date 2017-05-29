package leetcode.com.pickup1.hard;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 7/10/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/recover-binary-search-tree/
 * ****************************************************
 * Description:
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * ****************************************************
 * Thoughts:
 * 1.-_-|| 完全没有明白是什么意思呢!如何知道那两个元素被错放位置了?然后怎样才能把两个元素交换位置呢?规则是什么呢?
 * 2.难道是元素的大小顺序被打乱了?可那是平衡二叉树啊?
 * 2.BST遍历方法,前序,中序和后序
 * 3.看了答案后发现,他说的是平衡二叉树,所以中序遍历的情况下,要第一个元素是最小的,
 * 然后,需要两个阶段来保存,错位的元素.这过程是中序遍历,第一个元素最小,然后是次小,这样递增的过程
 * 所以一旦发生后面的比前面小,那么它就是目标,就要把它记录下来
 * ****************************************************
 * Time: 30 mins
 * Beat: 38%
 * Bug: 2
 * ****************************************************
 * Hindsight:
 * 选择中序遍历之后,就确定了大小顺序,所以比较的时候,就可以有参考啦.但是在处理last节点,first和second节点的时候,要小心处理
 * 任何时候都要保持last节点,这个节点之后的节点只要比他小就有问题,第一次的时候,把last->first,root->second
 * 从第二次开始,发生这样的情况,就root->second
 * 这个规律要理解记牢才好
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No099_Recover_Binary_Search_Tree {

    TreeNode fst = null;
    TreeNode snd = null;
    TreeNode last = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {

        travel(root);
        int tmp = fst.val;
        fst.val = snd.val;
        snd.val = tmp;

    }

    private void travel(TreeNode root) {
        if (root == null) return;

        travel(root.left);

        if (root.val < last.val) {
            if (fst == null) {
                //bug1:此处应该保留last节点的值
                fst = last;
                //bug2:需要处理root->snd 节点
                snd = root;
            } else {
                snd = root;
            }
        }


        last = root;
        travel(root.right);

    }
}
