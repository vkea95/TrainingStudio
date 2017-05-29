package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/7/16.
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
 * Thoughts:
 * 1.做过的题,第一个思路是在另一个同样size的数组中存储,第0个元素至本元素的sum,明显有问题嘛!在update的时候会出问题.
 * 2.换成其他的数据结构,似乎树是比较合适的,logn,但是如何构造树,并不是很清楚
 * 刚看了下答案,发现答案用的是树的结构,
 * Time: 60 mins
 * Beats: 28%
 * Bug: 6 主要集中在判断条件上,漏写或错写
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No307_Range_Sum_Query_Mutable {
}

class NumArray {

    private SumTreeNode sumTreeRoot;

    public NumArray(int[] nums) {
        buildSumTree(nums);
    }

    private void buildSumTree(int[] nums) {
        this.sumTreeRoot = sumTreeHelper(nums, 0, nums.length - 1);
    }

    private SumTreeNode sumTreeHelper(int[] nums, int start, int end) {
        //bug6: forget to judge nums.length==0
        if (nums == null || nums.length == 0) return null;
        if (start == end) {
            return new SumTreeNode(start, end, nums[start]);
        }

        SumTreeNode root = new SumTreeNode(start, end);
        int mid = (start + end) / 2;
        root.left = sumTreeHelper(nums, start, mid);
        root.right = sumTreeHelper(nums, mid + 1, end);
        root.val = root.left.val + root.right.val;
        return root;

    }

    void update(int i, int val) {
        updateHelper(i, val, this.sumTreeRoot);
    }

    private void updateHelper(int i, int val, SumTreeNode sumTreeNode) {
        if (sumTreeNode == null) return;
        if (sumTreeNode.start == i && sumTreeNode.end == i) {
            sumTreeNode.val = val;
            return;
        }
        int mid = (sumTreeNode.start + sumTreeNode.end) / 2;
        if (i < sumTreeNode.start || i > sumTreeNode.end) {
            return;
            //bug5: i< mid --> i<=mid
        } else if (i <= mid) {
            updateHelper(i, val, sumTreeNode.left);
        } else {
            updateHelper(i, val, sumTreeNode.right);
        }
        sumTreeNode.val = sumTreeNode.right.val + sumTreeNode.left.val;

    }

    public int sumRange(int i, int j) {
        return sumRangeHelper(i, j, this.sumTreeRoot);
    }

    private int sumRangeHelper(int start, int end, SumTreeNode sumTreeNode) {
        //bug1:don't know how tow calculate the result of the sum of the BT
        //bug2: forget to judge null
        if (sumTreeNode == null || start > end || start < sumTreeNode.start || end > sumTreeNode.end)
            return 0;
        //bug3:? 要用<= 否则会无限循环,没有结果
        //bug4: || --> && 否则计算不正确
        if (start <= sumTreeNode.start && sumTreeNode.end <= end)
            return sumTreeNode.val;
        int mid = (sumTreeNode.start + sumTreeNode.end) / 2;

        int result = sumRangeHelper(start, Math.min(mid, end), sumTreeNode.left)
                + sumRangeHelper(Math.max(mid + 1, start), end, sumTreeNode.right);

        return result;


    }
}

class SumTreeNode {
    public int val, start, end;
    public SumTreeNode left, right;

    public SumTreeNode(int start, int end) {
        this.start = start;
        this.end = end;

    }

    public SumTreeNode(int start, int end, int val) {
        this.start = start;
        this.end = end;
        this.val = val;

    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);