package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 6/9/16.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No295_Find_Median_from_Data_Stream_II {
    public static void main(String[] args) {
        int num =1;
        System.out.println("yuil;");
        System.out.println();
    }
}

class MedianFinder_II {
    class TreeNode {
        int val;
        TreeNode parent, left, right;

        TreeNode(int val, TreeNode p) {
            this.val = val;
            this.parent = p;
            left = null;
            right = null;
        }

        void add(int num) {
            if (num >= val) {
                if (right == null)
                    right = new TreeNode(num, this);
                else
                    right.add(num);
            } else {
                if (left == null)
                    left = new TreeNode(num, this);
                else
                    left.add(num);
            }
        }

        TreeNode next() {
            TreeNode ret;
            if (right != null) {
                ret = right;
                while (ret.left != null)
                    ret = ret.left;
            } else {
                ret = this;
                while (ret.parent.right == ret)
                    ret = ret.parent;
                ret = ret.parent;
            }
            return ret;
        }

        TreeNode prev() {
            TreeNode ret;
            if (left != null) {
                ret = left;
                while (ret.right != null)
                    ret = ret.right;
            } else {
                ret = this;
                while (ret.parent.left == ret)
                    ret = ret.parent;
                ret = ret.parent;
            }
            return ret;
        }
    }

    int n;
    TreeNode root, curr;

    // Adds a number into the data structure.
    public void addNum(int num) {
        if (root == null) {
            root = new TreeNode(num, null);
            curr = root;
            n = 1;
        } else {
            root.add(num);
            n++;
            if (n % 2 == 1) {
                if (curr.val <= num)
                    curr = curr.next();
            } else if (curr.val > num)
                curr = curr.prev();
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (n % 2 == 0) {
            return ((double) curr.next().val + curr.val) / 2;
        } else
            return curr.val;
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();