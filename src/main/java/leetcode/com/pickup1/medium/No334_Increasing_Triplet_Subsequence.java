package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 8/6/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * ****************************************************
 * Description:
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * Given [5, 4, 3, 2, 1],
 * return false.
 * ****************************************************
 * Thoughts:
 * 1.时间复杂度是O(n)
 * 2.存在这样的序列就好,不一定要求三个index是连续的。
 * 3.设想这样的一个元素,先从左侧开始遍历到它的直前,算法不够巧妙啊
 * ****************************************************
 * Time: -
 * Beat: 40%
 * Bug: -
 * ****************************************************
 * Hindsight:
 * 1.很朴素的解法,
 * 2.算法很巧妙,假设在某个index直前会有两个较小的元素,一旦有比第二个较小的元素大的情况,就会满足条件
 * 2.1且这两个较小的元素的还会不断地进行更新
 * 3.1 2 0 4
 * ****************************************************
 */
public class No334_Increasing_Triplet_Subsequence {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) min = num;
            else if (num < secondMin) secondMin = num;
            else if (num > secondMin) return true;
        }
        return false;
    }
}
