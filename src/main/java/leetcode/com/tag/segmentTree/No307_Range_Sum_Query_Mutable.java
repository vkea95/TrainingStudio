package leetcode.com.tag.segmentTree;

import edu.princeton.cs.algs4.SegmentTree;

/**
 * Created by JianZhang on 10/8/17.
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * <p>
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example:
 * Given nums = [1, 3, 5]
 * <p>
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * Concepts:http://www.cnblogs.com/xiaoyao24256/p/6590885.html
 * Solutions:
 * 1. 线段树的节点是要维护一个区间和一个权值的,然后父节点[x,y]的左右子树又是完全2分的即[x,(x+y)/2]和[(x+y)/2+1,y]
 * 问题:
 * 1. segmentTreeNode的左右孩子不要忘记写在代码中
 * Bugs:
 * 1. 需要在update和sum中增加节点是否为null,start和end是否超限的判断,否则会有空指针
 */
public class No307_Range_Sum_Query_Mutable {
    private class NumArray {

        SegmentTreeNode root;

        public NumArray(int[] nums) {

            if (nums == null || nums.length == 0) root = new SegmentTreeNode(0, 0, 0);
            else root = buildNode(0, nums.length - 1, nums);
        }

        public void update(int i, int val) {
            update(root, i, val);
        }

        public int sumRange(int i, int j) {
            return sumRange(root, i, j);
        }


        private int sumRangeHelper(int i, int j, SegmentTreeNode node) {
            //check if the node live in the range
            //此处将,index范围外的数据统统过滤掉,即只返回0,不会再向下递归寻找相应的数据了
            if (node == null || i > j || i < node.start || j > node.end) return 0;

            if (i <= node.start && node.end <= j) {
                return node.sum;
            }

            int mid = (node.start + node.end) / 2;
            //bug1:don't know use Math.min(mid,j) & Math.max(mid+1,i)
//            此处的做法可以结合本方法的入口判断条件来进行理解,即,用min和max来缩小范围,总之方法会正常应对的
            int result = sumRangeHelper(i, Math.min(mid, j), node.leftNode)
                    + sumRangeHelper(Math.max(mid + 1, i), j, node.rightNode);
            return result;

        }


        public int sumRange(SegmentTreeNode node, int i, int j) {
            if (node == null || i > j || i < node.start || j > node.end) return 0;

            if (node.start == i && node.end == j) return node.sum;
            int mid = node.start + (node.end - node.start) / 2;
            if (j <= mid) {
                return sumRange(node.leftNode, i, j);
            } else if (i > mid) {
                return sumRange(node.rightNode, i, j);
            } else {
                return sumRange(node.leftNode, i, mid) + sumRange(node.rightNode, mid + 1, j);
            }
        }


        private void update(SegmentTreeNode node, int position, int value) {
            if (node == null) return;
            //bug1:叶子节点更新完毕后,需要立即返回才对,否则就会去掉用空的指针了,但是可以增加一个node!=null的判断,反之出错,虽然此逻辑多余
            //找到叶子节点,并进行value更新,
            if (node.start == node.end && node.start == position) {
                node.sum = value;
                return;
            }
            int mid = node.start + (node.end - node.start) / 2;
            if (position <= mid) {
                update(node.leftNode, position, value);
            } else {
                update(node.rightNode, position, value);
            }

            node.sum = node.leftNode.sum + node.rightNode.sum;
        }

        private SegmentTreeNode buildNode(int start, int end, int[] nums) {
            if (start == end) return new SegmentTreeNode(start, end, nums[start]);

            SegmentTreeNode root = new SegmentTreeNode(start, end);
            int mid = start + (end - start) / 2;
            root.leftNode = buildNode(start, mid, nums);
            root.rightNode = buildNode(mid + 1, end, nums);
            //push up 操作
            root.sum = root.leftNode.sum + root.rightNode.sum;
            return root;
        }

    }


    private class SegmentTreeNode {

        SegmentTreeNode leftNode, rightNode;
        public int start, end, sum;

        SegmentTreeNode(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

        SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
