package leetcode.com.medium.part21;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 4/27/16.
 * Location:
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * *************************************************************************
 * Description:
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
 * the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * _______3______
 * /              \
 * ___5__          ___1__
 * /      \        /      \
 * 6      _2       0       8
 * /  \
 * 7   4
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
 * since a node can be a descendant of itself according to the LCA definition.
 * *************************************************************************
 * Analysis:
 * BT会有2个孩子,左右子树,不管怎样先找到目标节点之一p,然后,在其内部找到的话,p就是LCA,反之,在在被扫描过的节点内倒查,
 * 但是需要区分子节点的深度,这就和先序遍历,中序遍历和后序遍历的有关,需要看哪种遍历方式比较好啦
 * 可以先序遍历,一遍寻找一边压栈,找到任意一个后,就要考虑下一步了,继续寻找到另一个那么就是,或者另一个在平行的位置,这时就要考虑寻找LCA策略了
 * #################
 * 第二次开始想这个问题,因为是在LCA,所以想到后续遍历也许是个解决方案,毕竟一旦2个节点都找到了,那么第一个得知这个消息的节点就是LCA
 * 但是这需要一个计数器,来计算深度,进而找到他们的LCA
 * -->这个比较繁琐,如果是设计成count的话,就比较方便了,计算左右子树的count和,若为2则直接返回
 * #################
 * 看了网络答案,发现可以通过构建一个实体,来完成计算节点的任务,LCA就存在这个节点中,这个算法的问题是时间复杂度O(n)
 * 第二种简单明了的答案就比较清楚了,但他的假设是这被搜索的2个节点真的存在
 * *************************************************************************
 * Solution:
 * <p>
 * *************************************************************************
 */
public class No236_Lowest_Common_Ancestor_of_a_Binary_Tree_I {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return searchBST(root, p, q).root;

    }



    private Entity searchBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return new Entity(0, null);

        Entity left = searchBST(root.left, p, q);

        if (left.count == 2) return left;


        Entity right = searchBST(root.right, p, q);

        if (right.count == 2) return right;

        int totalNum = left.count + right.count;
        //bug1:root.val==p.val --> root=p
        if (root.val == p.val || root.val == q.val) {
            totalNum++;
        }


        return new Entity(totalNum, root);
    }
}
class Entity {
    public int count;
    public TreeNode root;

    public Entity(int count, TreeNode root) {
        this.count = count;
        this.root = root;
    }
}