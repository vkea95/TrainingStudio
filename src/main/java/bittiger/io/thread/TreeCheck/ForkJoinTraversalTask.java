package bittiger.io.thread.TreeCheck;

import leetcode.com.util.TreeNode;

import java.util.concurrent.RecursiveTask;

public class ForkJoinTraversalTask extends RecursiveTask<Boolean> {

    TreeNode root;
    long minVal;
    long maxVal;
    int height;

    public ForkJoinTraversalTask(TreeNode root, int height, long minVal, long maxVal) {
        this.root = root;
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.height = height;
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) {
            return true;
        }
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    @Override
    protected Boolean compute() {
        if (height <= 3) {
            return isValidBST(root, minVal, maxVal);
        } else {
            ForkJoinTraversalTask left = new ForkJoinTraversalTask(root.left, height - 1, minVal, root.val);
            ForkJoinTraversalTask right = new ForkJoinTraversalTask(root.right, height - 1, root.val, maxVal);
            left.fork();
            right.fork();

            return left.join() && right.join();
        }
    }
}
