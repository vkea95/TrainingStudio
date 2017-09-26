package leetcode.com.tag.tree;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by JianZhang on 9/25/17.
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example,
 * Given
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * Credit:http://www.cnblogs.com/grandyang/p/4293853.html
 * Solutions:
 * 1.这道题要求把二叉树展开成链表，根据展开后形成的链表的顺序分析出是使用先序遍历，那么只要是数的遍历就有递归和非递归的两种方法来求解，
 * 这里我们也用两种方法来求解。首先来看递归版本的，思路是先利用DFS的思路找到最左子节点，然后回到其父节点，把其父节点和右子节点断开，
 * 将原左子结点连上父节点的右子节点上，然后再把原右子节点连到新右子节点的右子节点上，然后再回到上一父节点做相同操作。代码如下：
 * 2.，这个方法是从根节点开始出发，先检测其左子结点是否存在，如存在则将根节点和其右子节点断开，
 * 将左子结点及其后面所有结构一起连到原右子节点的位置，把原右子节点连到元左子结点最后面的右子节点之后。代码如下：
 */
public class No114_Flatten_Binary_Tree_to_Linked_List {
    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left != null) flatten(root.left);
        if (root.right != null) flatten(root.right);
        //此处代码表示,左右两侧节点都可以收拢到右侧
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) root = root.right;//此处表明,这个递归处理,左右两侧分支的节点也都收拢到了右侧
        root.right = tmp;
    }

    public void flatten_preorder(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode tmp = stack.peek();
            stack.pop();
            if (tmp.left != null) {
                TreeNode r = tmp.left;
                while (r.right != null) r = r.right;
                r.right = tmp.right;
                tmp.right = tmp.left;
                tmp.left = null;
            }
            if (tmp.right != null) stack.push(tmp.right);
        }

    }

    public void flatten_nonrecursive(TreeNode root) {
        TreeNode curt = root;
        while (curt != null) {
            if (curt.left != null) {
                TreeNode node = curt.left;
                while (node.right != null) node = node.right;
                //将直下的右节点,放到左节点的右节点的最后
                node.right = curt.right;
                curt.right = curt.left;
                curt.left = null;
            }
            //下一步操作原来的左节点
            curt = curt.right;
        }

    }

}
