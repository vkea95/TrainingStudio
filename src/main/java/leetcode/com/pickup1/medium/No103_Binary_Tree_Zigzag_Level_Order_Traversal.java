package leetcode.com.pickup1.medium;

import leetcode.com.util.ListNode;
import leetcode.com.util.TreeNode;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * Created by tclresearchamerica on 6/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * ****************************************************
 * Thoughts:
 * 一层一层的遍历树,会用到stack,每次把该层节点的孩子,按照从左到右的方式放入队列,同时将该节点放入solution的list中
 * 在处理zigzag的时候,出现了问题,原打算用LinkedList类,通过addFirst() & addLast()的交替方式来取代stack
 * 但实际过程中,思路有点绕,运行结果也是在第3+层的时候,输出结果就不正确了,纠结addFirst还是addLast的时候,还是stack方式来的比较直接.
 * ****************************************************
 * Time: 200 mins
 * Beats: 12%
 * Bug: 2
 * ****************************************************
 * 网络答案:
 * Beats:50%
 * 实现方式:递归调用,根据层数判断是加到相应Solution的最后一个,还是第一个,这个思路很妙,首先就是自己建立了路线图,类似于深度优先
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No103_Binary_Tree_Zigzag_Level_Order_Traversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        travel(res, 0, root);
        return res;
    }

    private void travel(List<List<Integer>> res, int level, TreeNode cur) {
        if (cur == null) return;
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        if (level % 2 == 0) {
            res.get(level).add(cur.val);
        } else {
            //指定位置
            res.get(level).add(0, cur.val);
        }
        travel(res, level + 1, cur.left);
        travel(res, level + 1, cur.right);
    }


    public List<List<Integer>> zigzagLevelOrder_slow(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<TreeNode> stackEven = new Stack<>();
        Stack<TreeNode> stackOdd = new Stack<>();
        Stack<TreeNode> curstack;

        if (root == null) return result;
        stackEven.add(root);
        curstack = stackEven;
        boolean zigzagFlg = true;
        while (!curstack.isEmpty()) {
            List<Integer> solution = new ArrayList<>();


            while (!curstack.isEmpty()) {
                TreeNode node = curstack.pop();

                solution.add(node.val);
                if (zigzagFlg) {
                    //bug1:这样向queue插入元素的话,会出现问题,就是在第2层的时候,
                    // 会把右侧的节点的子先插了进去,但是它应该在后面的时候处理
                    //所以在这个时候,就会出现队列和栈的混合功能

                    if (node.left != null) stackOdd.push(node.left);
                    if (node.right != null) stackOdd.push(node.right);

                } else {
                    if (node.right != null) stackEven.push(node.right);
                    if (node.left != null) stackEven.push(node.left);

                }
            }
            curstack = zigzagFlg ? stackOdd : stackEven;
            zigzagFlg = !zigzagFlg;
            result.add(solution);
        }


        return result;


    }
}
