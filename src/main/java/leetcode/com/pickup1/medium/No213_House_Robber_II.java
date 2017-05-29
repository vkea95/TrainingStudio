package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 7/14/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/house-robber-ii/
 * ****************************************************
 * Description:
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he
 * will not get too much attention. This time, all houses at this place are arranged in a circle. That means the
 * first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as
 * for those in the previous street.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
 * of money you can rob tonight without alerting the police.
 * ****************************************************
 * Thoughts:
 * 1.如果是间隔1个寻找的话,是没有问题的,但是有可能会间隔多个,这个怎么玩儿?
 * 2.第一次写代码失败啦,计划是从i-1 和i-2的dp中获得数据结构,看来不正确,因为i-1就会导致相邻,所以推理失败
 * 3.所以,状况就是,推导出了方法,但是没有正确的反应到代码上,耗时2个小时,问题在于这个我第一次天真地以为,i要在i-2和i-3中选择,但其实这样
 * 会到值假设数组有3个以上的元素,处理起来会有问题,后来看了答案,才理解,可以i-1和(i-2)+i取最大值射给i,
 * 4.当然,我还有另一解法,就是
 * ****************************************************
 * Time: 180 mins
 * Beat: 3%
 * Bug: 3
 * ****************************************************
 * Hindsight:
 * 1.根据3+1的思路,我可以做到可视化,但在处理特殊case和视图向代码转换的过程,还是不足的。
 * 2.没有打破假设,如果能打破假设,那么我的算法会更加简洁,贴近实战
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No213_House_Robber_II {


    public static void main(String[] args) {
        No213_House_Robber_II obj = new No213_House_Robber_II();
        obj.rob(new int[]{4, 1, 2, 7, 5, 3, 1});
    }

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    private int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
            System.out.println("num[" + j + "]:" + num[j] + ":include:" + include + " exclude:" + exclude + " e:" + e + " i:" + i);
        }
        return Math.max(include, exclude);
    }

    public int rob_dp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];


        int[] dp0 = new int[nums.length + 1];
        int[] dp1 = new int[nums.length + 1];
        //bug2:保证每次都可以取2个情况下,所以将下标错位处理
        dp0[1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (i < nums.length - 1) {
                dp0[i + 1] = Math.max(dp0[i], dp0[i - 1] + nums[i]);
            } else {
                dp0[i + 1] = Math.max(dp0[i], dp0[i - 1]);
            }
            dp1[i + 1] = Math.max(dp1[i], dp1[i - 1] + nums[i]);

        }
        return Math.max(dp0[nums.length], dp1[nums.length]);
//        dp0[1] = nums[1];
        //bug1:因为数组头尾不可相见,所以不能用num[2]+num[0]-->万一有3个元素就不对啦!!!
//        dp0[2] = nums[2]+nums[0];

//        dp1[1] = nums[1];
////        dp1[2] = nums[2];
//        for (int i = 1; i < nums.length; i++) {
//            if (i == nums.length - 1) {
//                dp0[i] = Math.max(dp0[i - 1], dp0[i - 2]);
//            } else {
//                dp0[i] = Math.max(dp0[i - 1], dp0[i - 2] + nums[i]);
//            }
//            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
//        }
//        return Math.max(Math.max(dp0[nums.length - 1], dp0[nums.length - 2]), Math.max(dp1[nums.length - 1], dp1[nums.length - 2]));
    }
}
