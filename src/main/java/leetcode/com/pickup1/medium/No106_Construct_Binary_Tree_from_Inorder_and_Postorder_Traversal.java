package leetcode.com.pickup1.medium;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 7/14/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * ****************************************************
 * Description:
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * ****************************************************
 * Thoughts:
 * 1.postOrder的最后一项必然是root,那么每棵子树的root也在这个字数的最后一项,
 * 它的前一项必然是右子树的根?-->(不对,若无右子树,则结论不正确,需要inorder来佐证)
 * 2.Inorder:root切分了这棵子树的左右子树
 * 3.先找到postorder的最后一项,就是root,然后在inorder找到root,如果后面有这一项,那就是右子树,然后再在postorder中找
 * 4.右子树的问题容易解决,左子树的问题该如何破解呢?
 * 5.还是,看了答案才明白,这个里面的问题在于,要找到左子树的话,必须彼此借助才可以。
 * 例如,在inOrder中Root的位置前面都是左子树,那么它的数量也是可以计算出来的啦,那么在postOrder中就可以知道相对位置啦
 * 6.如何判断一个节点的左右子节点是否为null呢,那就要看这个节点的位置是否合法,比如,index位置异常等等。
 * 7.具体解法,首先需要定位啦,定位 Inorder的位置
 * ****************************************************
 * Time: 120 mins
 * Beat: 45%
 * Bug:1
 * ****************************************************
 * Hindsight:
 * 1.简单推导是没有问题的,问题在于没有找到合适的方法,将给的2个条件结合起来思考,或者说结合的不够。那么以后类似的输入有多个条件的话
 * 还是要想方设法结合才好
 * 2.推导方法的时候,没有想到用recrusive的方法。停留在while上面,感觉思维收到了禁锢。这个以后要灵活考虑问题啊
 * 3.和Xiaobo讨论后,发现可以将方法的签名简单化,并且程序更加简单明了,即顺序为root->right->left的方法,一路从postOrder将节点顺序摘出
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No106_Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal {
    private int postEnd = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postEnd = postorder.length - 1;
        return helper(inorder, postorder, 0, inorder.length - 1);
//        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode helper(int[] inOrder, int[] postOrder, int iStart, int iEnd) {
        if (iStart > iEnd) return null;
        int position = getPosition(inOrder, postOrder[postEnd], iStart, iEnd);
        TreeNode root = new TreeNode(postOrder[postEnd--]);

        root.right = helper(inOrder, postOrder, position + 1, iEnd);
        root.left = helper(inOrder, postOrder, iStart, position - 1);
        return root;
    }

    //bug1:don't knwo set the value of the root node, it's just use the
    private TreeNode helper(int[] inOrder, int iStart, int iEnd, int[] postOrder, int pStart, int pEnd) {

        if (iEnd < iStart) return null;

        int position = getPosition(inOrder, postOrder[pEnd], iStart, iEnd);

        TreeNode root = new TreeNode(postOrder[pEnd]);

        root.left = helper(inOrder, iStart, position - 1, postOrder, pStart, pStart + (position - iStart - 1));

        //bug1:postOrder的开始位置,只要在前一个call的end上+1即可。不是加2
        root.right = helper(inOrder, position + 1, iEnd, postOrder, pStart + (position - iStart), pEnd - 1);

        return root;
    }

    private int getPosition(int[] order, int value, int start, int end) {
        int position = -1;

        for (int i = start; i <= end; i++) {
            if (order[i] == value) return i;
        }
        return position;
    }
}
