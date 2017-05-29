package leetcode.com.hard.part2;

import leetcode.com.util.TreeNode;

import java.util.Stack;

/**
 * Created by tclresearchamerica on 5/4/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * ****************************************************
 * Description:
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later
 * in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized
 * to a string and this string can be deserialized to the original tree structure.
 * <p>
 * For example, you may serialize the following tree
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to
 * follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 * ****************************************************
 * Analysis:
 * 然后，这里需要序列化和反序列化二叉树，可以采用的方法很多了。多种遍历方式都可以，只有在序列化和反序列化时采用相同的遍历方式即可。
 * 其中较为简单的是前序遍历和层次遍历。
 * <p>
 * 因为采用层次遍历行需要利用队列，会多引入一种数据结构，所以这里采用前序遍历，这种方法也比较容易理解和实现。
 * <p>
 * 序列化，即将二叉树转成字符串。因为二叉树中包含null节点。需要采用一个特殊字符标记，因为这里的二叉树的值都是数字，
 * 所以可以采用非数字作为标记，如采用X。每个节点间用,分割。然后就是按照前序遍历的方法，输入二叉树成字符串，较为简单，不再赘述。
 * <p>
 * 反序列化，即将刚才生成的字符串转换成二叉树。首先，将字符串按照,split成字符串数组的形式，该数组中每一个元素即为一个二叉树的节点。
 * 这里本来可以很简单的用一个全局变量记录，当前遍历的数组index，但是因为题目中要求采用stateless的方式，所以必须换种方式。
 * 可以采用List的方式，将已经遍历的节点删除，剩下的就是当该字符串不是X时，按照前序遍历的方式重建二叉树。
 * <p>
 * 原文链接：http://www.jianshu.com/p/e099d10bc0db
 * 著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。
 * ****************************************************
 */
public class No297_Serialize_and_Deserialize_Binary_Tree {
    public static void main(String[] args) {
        Codec codec = new Codec();
        codec.deserialize("null,");
    }
}

class Codec {


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuffer result = new StringBuffer();
        // if()
        serializeHelper(root, result);
        return result.toString();
    }

    private void serializeHelper(TreeNode root, StringBuffer sb) {
        if (root == null) {
            sb.append("null");
            //bug4:需要将, 放到后面,否则会出现数组值不正确的问题.
            sb.append(",");
        } else {
            sb.append(root.val);
            sb.append(",");
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] array = data.split(",");
//        System.out.println(data);
        //bug3:没有进行"null".equals判断
        if (array.length > 0 && !"null".equals(array[0])) {

            TreeNode root = new TreeNode(Integer.parseInt(array[0]));
            Stack<TreeNode> stack = new Stack<>();
            //bug2:没有push
            stack.push(root);
            boolean leftFlg = true;
            for (int i = 1; i < array.length; i++) {
                TreeNode node = stack.peek();
                if (leftFlg) {
                    //bug1: 需要将== 改为equals
                    if (array[i].equals("null")) {
                        node.left = null;
                        leftFlg = false;
                    } else {
                        node.left = new TreeNode(Integer.parseInt(array[i]));
                        stack.push(node.left);
                    }

                } else {
                    //处理完毕
                    stack.pop();
                    //bug1: 需要将== 改为equals
                    if (array[i].equals("null")) {
                        node.right = null;
                    } else {
                        node.right = new TreeNode(Integer.parseInt(array[i]));
                        leftFlg = true;

                        stack.push(node.right);
                    }

                }
            }
            return root;

        } else {
            return null;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));