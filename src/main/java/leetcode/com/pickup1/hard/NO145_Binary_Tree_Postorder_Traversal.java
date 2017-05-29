package leetcode.com.pickup1.hard;

import leetcode.com.util.ListNode;
import leetcode.com.util.TreeNode;

import java.util.*;

/**
 * Created by tclresearchamerica on 6/4/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * ****************************************************
 * Thoughts:
 * 1.if it requires the postorder, we'll need a data structure to keep the root element like stack FILO
 * 2.or recursive function to loop the elements ---> not permitted
 * ****************************************************
 * Time: 20 mins
 * Beats: 8%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class NO145_Binary_Tree_Postorder_Traversal {
//    Stack<TreeNode> stack = new Stack<>();

    //下面的那个wrong的做法,思路貌似没问题,但是结果不争取,且用到了3个数据结构,扫了一眼答案,
    //用stack+变量即可完成操作,需要标志栈中节点是否被操作过
    //所以按照这个方法,会一直不断push,直到某个节点的left & right 都是null
    //所以每次push进去的时候,要保存下节点,
    //方便判断是否曾经处理过的
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root != null)
            stack.push(root);

        TreeNode visitedNode = root;

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left == null && node.right == null) {
                result.add(node.val);
                visitedNode = stack.pop();
            } else {
                if (visitedNode == node.right || visitedNode == node.left) {
                    result.add(node.val);
                    visitedNode = stack.pop();
                    //bug1:break --> continue,否则答案不正确
                    continue;
                }
                if (node.right != null)
                    stack.push(node.right);
                if (node.left != null) {
                    stack.push(node.left);
                }
            }

        }
        return result;

    }

    public List<Integer> postorderTraversal_failed_by_wrong_answer(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        if (root != null)
            queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.right != null) queue.offer(node.right);
                if (node.left != null) queue.offer(node.left);
                stack.push(node.val);
            }
        }
        for (int i = stack.size() - 1; i >= 0; i--) {
            resultList.add(stack.get(i));
        }

        return resultList;

    }


    private void resursivePostOrder(TreeNode root, List<Integer> resultList) {
        if (root == null) return;
        resursivePostOrder(root.left, resultList);
        resursivePostOrder(root.left, resultList);

    }
}
