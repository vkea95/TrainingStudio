package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/28/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/jump-game/
 * ****************************************************
 * Description:
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * ****************************************************
 * Thought:
 * 1.标准的动态规划问题,就是不知道老师当时讲的思路是怎样的了,动态转移方程?初始状态.
 * 2.可以理解为DFS
 * 3.定义数组F[], 初始值均为零, F[i]表示第i个节点可以到第F[i]个-->深度优先嘛
 * Time: 18 mins:
 * Beat: 26%
 * Bug: 2
 * ****************************************************
 * Hindsight:
 * 看了第一遍的答案,发现可以用一个变量Fartest代替声明的数组, Beat:26%->75%
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No055_Jump_Game {
    public static void main(String[] args) {
        No055_Jump_Game obj = new No055_Jump_Game();
        obj.canJump(new int[]{3, 0, 0, 0});
    }

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) return true;
        boolean rst = false;

        int[] f = new int[nums.length + 1];
        f[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (f[i - 1] >= i) {
                //bug1:方程式写的不对哦: nums[i - 1] + i - 1->f[i-1]
                //f
                f[i] = i + nums[i] > f[i - 1] ? i + nums[i] : f[i - 1];
            }
        }

        return f[nums.length - 1] > 0 ? true : false;
    }
}
