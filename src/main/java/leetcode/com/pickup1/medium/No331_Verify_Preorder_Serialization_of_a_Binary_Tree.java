package leetcode.com.pickup1.medium;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 8/7/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 * ****************************************************
 * Description:
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record
 * the node's value. If it is a null node, we record using a sentinel value such as #.
 * <p>
 * _9_
 * /   \
 * 3     2
 * / \   / \
 * 4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents
 * a null node.
 * <p>
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a
 * binary tree. Find an algorithm without reconstructing the tree.
 * <p>
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 * <p>
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas
 * such as "1,,3".
 * <p>
 * Example 1:
 * "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Return true
 * <p>
 * Example 2:
 * "1,#"
 * Return false
 * <p>
 * Example 3:
 * "9,#,#,1"
 * Return false
 * <p>
 * <p>
 * ****************************************************
 * Thoughts:
 * 1.节点个数要满足要求不能> or <
 * 2.null节点要满足要求
 * 3.如果按照数组来进行判断的话,那么需要一个index,来进行索引
 * 4.叶子节点的后面要跟着2个#
 * 5.right node的处理要保证好啊-->算法?
 * ****************************************************
 * Time: 30 mins
 * Beat: 94%
 * Bug:0
 * ****************************************************
 * Hindsight:
 * 1.按照自己的想法,设计的算法,将#当做叶子节点处理,所有的算法都follow二叉树的preorder算法,
 * 过程中检查index是否越界,遍历结束后,检查index是否刚好到的length()-1的位置
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No331_Verify_Preorder_Serialization_of_a_Binary_Tree {

    private int index = 0;

    public boolean isValidSerialization(String preorder) {
        if (preorder == null) return false;

        if (preorder.length() == 0) return true;

        String[] nodes = preorder.split(",");
        this.index = 0;
        if (helper(nodes) && this.index == nodes.length - 1) {
            return true;
        }
        return false;
    }


    private boolean helper(String[] nodes) {
        if (this.index >= nodes.length) {
            return false;
        }

        if ("#".equals(nodes[this.index])) {
            return true;
        }
        this.index++;
        if (!helper(nodes)) return false;
        this.index++;
        if (!helper(nodes)) return false;
        return true;
    }

}
