package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 8/14/16.
 * ****************************************************
 * Location:
 * ****************************************************
 * Description:
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security
 * system connected and it will automatically contact the police if two adjacent houses were broken
 * into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
 * of money you can rob tonight without alerting the police.
 * ****************************************************
 * Thought:
 * 1.感觉一瞬间不会做了,只区分奇数和偶数是不正确的。然后,那个经典的不用dp的算法,也想不起来啦
 * 2 1 3 8
 * ****************************************************
 * Time: 20 mins
 * Beat: 4%
 * Bug:1
 * ****************************************************
 * Hindsight:
 * 1.在处理这道题上,还是有点迷茫,虽然题目解答出来了,
 * https://segmentfault.com/a/1190000005138379
 * 空间改进
 * 由递推数组可以看出，并不需要n个空间，所以我们用滚动数组
 * <p>
 * 时间复杂度：O(n)
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No198_House_Robber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        if (nums.length == 1) return nums[0];

        int[] benefits = new int[nums.length];
        benefits[0] = nums[0];
        //bug1: 没有check 数组的长度情形下,直接使用1,报错啦
        benefits[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            benefits[i] = Math.max(nums[i] + benefits[i - 2], benefits[i - 1]);
        }
        return benefits[nums.length - 1];
    }

    public int rob_O1(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        int[] f = new int[3];
        f[0] = nums[0];
        if (nums.length == 1) {
            return f[0];
        }
        f[1] = nums[0] > nums[1] ? nums[0] : nums[1];

        if (nums.length == 2) {
            return f[1];
        }
        for (int i = 2; i < nums.length; i++) {
            f[i % 3] = Math.max(f[(i - 2) % 3] + nums[i], f[(i - 1) % 3]);
        }
        return f[(nums.length - 1) % 3];
    }


}
