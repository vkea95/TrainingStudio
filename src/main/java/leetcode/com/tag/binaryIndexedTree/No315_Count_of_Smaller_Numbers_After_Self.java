package leetcode.com.tag.binaryIndexedTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by JianZhang on 10/16/17.
 * Reference: http://www.cnblogs.com/yrbbest/p/5068550.html
 * Solutions:
 * 1. 一个是BST,倒序遍历数组,并计数,放入树中==》每个节点只记忆它的左子树中比它小的节点的个数
 * 2. 一个是binary indexed tree: http://www.cnblogs.com/grandyang/p/4985506.html -->此处讲解简明扼要
 * 3.
 */
public class No315_Count_of_Smaller_Numbers_After_Self {

    public static void main(String[] args) {
        No315_Count_of_Smaller_Numbers_After_Self obj = new No315_Count_of_Smaller_Numbers_After_Self();
        obj.countSmaller(new int[]{1, 2, 3, 20, 2, 9, 2});
    }
    //Credit: https://discuss.leetcode.com/topic/31910/7ms-java-solution-using-binary-indexed-tree/2
//    Ref: http://blog.csdn.net/zhongjiekangping/article/details/6931407
//    BinaryIndexedTree Ref:https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
    /*
    In this solution, we use a binary indexed tree (BIT)
    Our assumption is that all elements in nums are positive
    */

    static int MAX = 11000; //we set max value that can be store in the tree
    int[] tree = new int[MAX];

    public List<Integer> countSmaller(int[] nums) {
        Integer[] result = new Integer[nums.length];

        //make all elements in the array posive while maintaining their order
        makePositive(nums);

        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = get(nums[i]);
            add(nums[i] + 1, 1);
        }
        return Arrays.asList(result);
    }

    public void makePositive(int[] nums) {
        int min = MAX;
        for (int i = 0; i < nums.length; i++)
            min = Math.min(min, nums[i]);
        if (min < 0) {
            min = -min + 1;
            for (int i = 0; i < nums.length; i++)
                nums[i] += min;
        }
    }

    public void add(int idx, int val) {
        while (idx < MAX) {
            tree[idx] += val;
            idx += (idx & (-idx));
        }
    }

    public int get(int idx) {
        int result = 0;
        while (idx > 0) {
            result += tree[idx];
            idx &= (idx - 1);
        }
        return result;
    }

    // Credit:    https://leetcode.com/discuss/73762/9ms-short-java-bst-solution-get-answer-when-building-bst
    class solution {
        private class TreeNode {
            public int val;
            public int count = 1;
            public TreeNode left, right;

            public TreeNode(int val) {
                this.val = val;
            }
        }

        public List<Integer> countSmaller(int[] nums) {
            List<Integer> res = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return res;
            }
            TreeNode root = new TreeNode(nums[nums.length - 1]);
//            最后一个元素是没有比它小的数字的,所以是0
            res.add(0);

            for (int i = nums.length - 2; i >= 0; i--) {
                int count = addNode(root, nums[i]);
                res.add(count);
            }

            Collections.reverse(res);
            return res;
        }

        private int addNode(TreeNode root, int val) {
            int curCount = 0;
            while (true) {
                if (val <= root.val) {
                    root.count++;                   // add the inversion count
                    if (root.left == null) {
                        root.left = new TreeNode(val);
                        break;
                    } else {
                        root = root.left;
                    }
                } else {
                    curCount += root.count;
                    if (root.right == null) {
                        root.right = new TreeNode(val);
                        break;
                    } else {
                        root = root.right;
                    }
                }
            }

            return curCount;
        }
    }
}
