package leetcode.com.medium;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 5/11/16.
 * Location:
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 * ****************************************************
 * Description:
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node,
 * we record the node's value. If it is a null node, we record using a sentinel value such as #.
 * _9_
 * /   \
 * 3     2
 * / \   / \
 * 4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
 * where # represents a null node.
 * <p>
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of
 * a binary tree. Find an algorithm without reconstructing the tree.
 * <p>
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 * <p>
 * You may assume that the input format is always valid, for example it could never contain two consecutive
 * commas such as "1,,3".
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
 * Analysis:
 * http://www.programcreek.com/2015/01/leetcode-verify-preorder-serialization-of-a-binary-tree-java/
 * We can keep removing the leaf node until there is no one to remove. If a sequence is like "4 # #",
 * change it to "#" and continue. We need a stack so that we can record previous removed nodes.
 * ****************************************************
 * 事后诸葛亮:
 * 1.根据既成事实进行判断,因为是preOrder二叉树,不方便用快捷的stack方式,但是将其转化为,叶子递归变节点的方式来完成判断
 * 2.还有根据#的出现规律,进行判断的,
 * ****************************************************
 * 执行效率约48%,还有提高的可能性哦
 * ****************************************************
 * ****************************************************
 */
public class No331_Verify_Preorder_Serialization_of_a_Binary_Tree {
    public static void main(String[] args) {
        No331_Verify_Preorder_Serialization_of_a_Binary_Tree obj = new No331_Verify_Preorder_Serialization_of_a_Binary_Tree();
        obj.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
//        obj.isValidSerialization("9,#,92,#,#");
//        obj.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
        obj.isValidSerialization("#");


    }

    public boolean isValidSerialization(String preorder) {
        int diff = 0, index = 0;
        String[] strs = preorder.split(",");
        while (index < strs.length) {
            if (strs[index++].equals("#")) diff++;
            else diff--;
            if (diff == 1) break;
        }
        return diff == 1 && index == strs.length;
    }

    public boolean isValidSerialization_slow_II(String preorder) {
        if (preorder == null) return true;

        //bug4:节点值可能是多位数,所以不可以按照char进行切分
        String[] chars = preorder.split(",");
        Stack<String> stack = new Stack<>();

        //bug1:可将String的内容都放入stack,所以就不必判断char数组的下标了
        for (int i = 0; i < chars.length; i++) {
//            stack.push(chars[i]);
            if ("#".equals(chars[i])) {

                //bug3:forget to circle the operations, leaf -> node should be loop operation
                while (!stack.isEmpty() && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.isEmpty()) return false;
                    stack.pop();
                }
            }
            stack.push(chars[i]);
        }

        return stack.size() == 1 && stack.pop().equals("#");
    }

    public boolean isValidSerialization_Slow(String preorder) {
        if (preorder == null) return true;

        //bug4:节点值可能是多位数,所以不可以按照char进行切分
        String[] chars = preorder.split(",");
        Stack<String> stack = new Stack<>();

        //bug1:可将String的内容都放入stack,所以就不必判断char数组的下标了
        for (int i = 0; i < chars.length; i++) {
            stack.push(chars[i]);

            while (stack.size() >= 3) {
                if (stack.get(stack.size() - 1).equals("#") &&
                        stack.get(stack.size() - 2).equals("#") &&
                        //bug3:判断条件应该是X##,而不是###
                        !stack.get(stack.size() - 3).equals("#")
                        ) {
                    stack.pop();
                    stack.pop();
                    stack.pop();
                    stack.push("#");
                } else {
                    //bug2:forget to jump out, otherwise TLE
                    break;
                }
            }
        }

        return stack.size() == 1 && stack.pop().equals("#");
    }
}
