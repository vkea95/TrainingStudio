package leetcode.com.medium.part02;

/**
 * Created by jason on 2016/2/22.
 * Location:
 * https://leetcode.com/problems/jump-game/
 * *******************************************
 * Description:
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * *********************************************
 * Solution:
 * DP or greedy
 * DP 方法
 * 1.方程组
 * 2.初期化
 */
public class No055_Jump_Game_DP {
    public boolean canJump(int[] nums) {
        boolean[] can = new boolean[nums.length];
        can[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (can[j] && j + nums[j] >= i) {
                    can[i] = true;
                    break;
                }
            }
        }
        return can[can.length - 1];
    }
}
