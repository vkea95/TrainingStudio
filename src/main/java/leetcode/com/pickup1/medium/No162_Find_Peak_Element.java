package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/24/16.1
 * *****************************************************************************
 * Location:
 * https://leetcode.com/problems/find-peak-element/
 * *****************************************************************************
 * Description:
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that num[-1] = num[n] = -∞.
 * <p>
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 * <p>
 * <p>
 * *****************************************************************************
 * Time: 20 mins
 * Beat: 1.8% -> 35%
 * Bug: 2
 * *****************************************************************************
 * 网络解答:
 * Ref:https://segmentfault.com/a/1190000003488794
 * 2种做法:
 * 1.顺序遍历
 * 时间 O(N) 空间 O(1)
 * 思路
 * 最简单的做法，遍历数组找出比前后都大的元素。
 * 2.二分搜索
 * 复杂度
 * 时间 O(logN) 空间 O(1)
 * <p>
 * 思路
 * 题目要求时间复杂度为O(logN)，logN时间的题目一般都是Heap，二叉树，分治法，二分搜索这些很“二”解法。这题是找特定元素，
 * 基本锁定二分搜索法。我们先取中点，由题意可知因为两端都是负无穷，有上坡就必定有一个峰，我们看中点的左右两边大小，如果向左是上坡，
 * 就抛弃右边，如果向右是上坡，就抛弃左边。直到两边都小于中间，就是峰了。
 * <p>
 * <p>
 * *****************************************************************************
 * Hindsight:
 * 1.没有想到的是二分法也可以解决问题,就是寻找递增的范围
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 */
public class No162_Find_Peak_Element {
    public static void main(String[] args) {
        No162_Find_Peak_Element obj = new No162_Find_Peak_Element();
        obj.findPeakElement(new int[]{-2147483648});
    }

    public int findPeakElement(int[] nums) {
        for (int min = 0, max = nums.length - 1, mid = max / 2; min < max; mid = (min + max) / 2) {
            if (min == mid) return nums[min] < nums[max] ? max : min;
            min = nums[mid] < nums[mid + 1] ? mid : min;
            max = nums[mid] > nums[mid + 1] ? mid : max;
        }
        return 0;
    }

    public int findPeakElement_normal(int[] nums) {
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1] && nums[i] > nums[i - 1]) {
                return i;
            }
        }
        return nums.length <= 1 || nums[0] > nums[1] ? 0 : nums.length - 1;
    }

    public int findPeakElement_zj(int[] nums) {
        //bug1:
        if (nums == null || nums.length == 0) return -1;
        //bug2:加个判断,因为如果是1个的话,前后都是负无穷,直接返回就好了
        if (nums.length == 1) return 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > (i > 0 ? nums[i - 1] : Integer.MIN_VALUE) &&
                    (nums[i] > (i < nums.length - 1 ? nums[i + 1] : Integer.MIN_VALUE))) {
                return i;

            }
        }
        return -1;
    }
}
