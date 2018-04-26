package leetcode.com.medium;

import leetcode.com.util.TreeNode;

/**
 * Created by tclresearchamerica on 5/8/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/range-sum-query-mutable/
 * ****************************************************
 * Description:
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * ****************************************************
 * Analysis:
 * 1.类似于304,对数组的sum[]进行预处理,提前累加好,这里需要考虑的是大整数,空数组等等
 * 实际情况是,理论上程序工作没有问题,可是超时啦啦啦啦啦!!!!!!
 * 2.根据网络答案提示,结题思路被放到了树的设计上面,单个叶子节点放的是每个数组元素的值,其余节点放的是某段元素的sum,
 * 这样的设计:单个节点的val变化,只需要更新log(n)的时间复杂度,即从root到被更新的叶子节点的路径上的值,因为数组的长度固定,
 * 所以在建树的时候,可以选用2分法,这样就可以构造出一棵二叉树,时间复杂度在log(n)范畴
 * 这样每个节点的属性会有start,end和sum三个
 * 在计算sumRange的时候,这个index的问题,就在于如何方便的取到sum值
 * 具体的取法就是,让坐标区域落在[i,j]范围的node的sum值进行累加即可!!!,
 * 其余的范围直接返回零!!!!---------->Important
 * ****************************************************
 * ****************************************************
 */
public class No307_Range_Sum_Query_Mutable {
}

class NumArray {

    SegmentTreeNode root;
    private int[] sum;

    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            root = null;
        } else {
            root = buildTree(nums, 0, nums.length - 1);
        }

    }


    void update(int i, int val) {
        //find difference between new & old elements

        updateHelper(this.root, i, val);
    }

    public int sumRange(int i, int j) {
        return sumRangeHelper(i, j, this.root);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        //return leaf node
        if (start == end) {
            return new SegmentTreeNode(start, end, nums[start]);
        }

        //create the normal node without sum
        SegmentTreeNode root = new SegmentTreeNode(start, end);

        int mid = (start + end) / 2;
        root.leftNode = buildTree(nums, start, mid);
        root.rightNode = buildTree(nums, mid + 1, end);

        //bug2:start ->sum
        root.sum = root.leftNode.sum + root.rightNode.sum;

        return root;

    }

    private void updateHelper(SegmentTreeNode node, int i, int val) {
        //bug3: root -> node,变量名称搞错啦!!!
        if (node == null) return;

        int mid = (node.start + node.end) / 2;
        if (i <= mid) {
            updateHelper(node.leftNode, i, val);
        } else {
            updateHelper(node.rightNode, i, val);
        }

        //判断叶子节点的代码,可以放在这里,原因是因为,叶子节点的左右子节点都是null,不产生效果,依然会更新正确的节点位置的
        if (node.start == node.end && node.start == i) {
            node.sum = val;
            return;
        }
        node.sum = node.leftNode.sum + node.rightNode.sum;
    }

    private int sumRangeHelper(int i, int j, SegmentTreeNode node) {
        //check if the node live in the range
        if (node == null || i > j || i < node.start || j > node.end) return 0;

        if (i <= node.start && node.end <= j) {
            return node.sum;
        }

        int mid = (node.start + node.end) / 2;
        //bug1:don't know use Math.min(mid,j) & Math.max(mid+1,i)
        int result = sumRangeHelper(i, Math.min(mid, j), node.leftNode)
                + sumRangeHelper(Math.max(mid + 1, i), j, node.rightNode);
        return result;

    }


}

class SegmentTreeNode {
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
