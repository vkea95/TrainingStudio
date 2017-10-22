package leetcode.com.tag.BinarySearchTree;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by JianZhang on 10/21/17.
 * Given an array of integers, find out whether there are two distinct indices i and j in the array
 * such that the absolute difference between nums[i] and nums[j] is at most t
 * and the absolute difference between i and j is at most k.
 * Solutions:
 * 1. 两个方法:A TreeSet获取将要入树的数字的ceiling和flour 值,此处需要将它们转换为long型
 *            B SortedMap,要放long型数据,找到左右boundary,然后寻找满足条件的subSet,若非空,则满足题意
 */
public class No220_Contains_Duplicate_III {

    //Credit: http://www.cnblogs.com/anne-vista/p/4827824.html
//    这个里面有个大小为k的窗口问题,
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];

            if (set.floor(cur) != null && (long) cur - (long) set.floor(cur) <= (long) t) return true;
            if (set.ceiling(cur) != null && (long) set.ceiling(cur) - (long) cur <= (long) t) return true;

            set.add(cur);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }

        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate_2(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        SortedSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < nums.length; i++) {
            long leftBound = (long) nums[i] - t;
            long rightBound = (long) nums[i] + t + 1;
            SortedSet<Long> subSet = set.subSet(leftBound, rightBound);
            if (!subSet.isEmpty()) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
//    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//        if (nums == null || nums.length == 0) return false;
//        BST bst = new BST();
//        for (int i = 0; i < nums.length; i++) {
//            if (bst.addNode(i, nums[i], t, k)) return true;
//        }
//        return false;
//    }
//    用BST的想法有问题,如果只是小的向左插入,大的向右插入,这样
//
//    class TreeNode {
//        public TreeNode left, right;
//        public int index, value;
//
//        public TreeNode(int index, int value) {
//            this.index = index;
//            this.value = value;
//        }
//    }
//
//    class BST {
//        public TreeNode root;
//
//        public boolean addNode(int index, int value, int k, int t) {
//            if (root == null) root = new TreeNode(index, value);
//            else {
//                TreeNode node = root;
//                while (node != null) {
//                    int diff = Math.abs(node.value - value);
//                    if (diff <= t && (index - node.index) <= k) return true;
//                    if (value <= node.value) {
//                        if (node.left == null) {
//                            node.left = new TreeNode(index, value);
//                            break;
//                        } else {
//                            node = node.left;
//                        }
//                    } else {
//                        if (node.right == null) {
//                            node.right = new TreeNode(index, value);
//                            break;
//                        } else {
//                            node = node.right;
//                        }
//                    }
//                }
//
//            }
//
//            return false;
//        }
//
//    }
}
