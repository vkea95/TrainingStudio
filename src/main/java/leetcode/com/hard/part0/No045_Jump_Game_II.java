package leetcode.com.hard.part0;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 6/19/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/jump-game-ii/
 * ****************************************************
 * Desription:
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to
 * the last index.)
 * ****************************************************
 * Thoughts:
 * 1.DP:A.定义状态转移方程 B.初始条件
 * S[i]:到第i个节点,所需要的min步数
 * function: S[i]=Min(s[j]+1, j<i && j能够跳到i)
 * PS: DP的方法导致TLE啦
 * Time: 60 mins
 * Beat: 12%
 * Bug:3
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No045_Jump_Game_II {

    public int jump_DP_TLE(int[] nums) {
        int[] steps = new int[nums.length];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[0] = 0;

        //bug1:TLE: 将j的初始化挪到外面,减少不必要的循环
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            steps[i] = Integer.MAX_VALUE;
            for (; j < i; j++) {
                //bug1:j+nums[j]>i   --> j+nums[j]>=i
                if (steps[j] != Integer.MAX_VALUE && j + nums[j] >= i) {
                    steps[i] = steps[j] + 1;
                    break;
                }
            }
        }
        return steps[steps.length - 1];

    }

    public int jump_easy(int[] A) {
        if (A == null || A.length <= 1) return 0;
        int sc = 0;
        int e = 0;
        int max = 0;
        for (int i = 0; i < A.length - 1; i++) {
            max = Math.max(max, i + A[i]);
            if (i == e && max >= i) {
                sc++;
                e = max;
            }
        }
        return max >= A.length - 1 ? sc : Integer.MAX_VALUE;

    }
}
