package ctc.com.viii.ch16;

public class Factorial {

    int factorsOf5(int i) {
        int count = 0;
        while (i % 5 == 0) {
            count++;
            i /= 5;
        }
        return count;
    }

}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */