package bittiger.io.thread.TreeCheck;

import leetcode.com.util.TreeNode;

import java.util.concurrent.*;

public class IsValidBST implements Callable<Boolean> {

    TreeNode root;
    long minVal;
    long maxVal;
    ExecutorService executorService;

    public IsValidBST(ExecutorService executorService, TreeNode root, long minVal, long maxVal) {
        this.root = root;
        this.minVal = minVal;
        this.maxVal = maxVal;
        this.executorService = executorService;
    }


    @Override
    public Boolean call() throws Exception {
        return isValidTree();
    }

    public boolean isValidTree() throws ExecutionException, InterruptedException {
        if (root == null) {
            return true;
        }
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        Future<Boolean> left = executorService.submit(new IsValidBST(executorService, root.left, minVal, root.val));
        Future<Boolean> right = executorService.submit(new IsValidBST(executorService, root.right, minVal, root.val));
        return left.get() && right.get();
    }
}
