package leetcode.com.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by tclresearchamerica on 7/29/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/largest-divisible-subset/
 * ****************************************************
 * Description:
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements
 * in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 * <p>
 * If there are multiple solutions, return any subset is fine.
 * <p>
 * Example 1:
 * <p>
 * nums: [1,2,3]
 * <p>
 * Result: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 * <p>
 * nums: [1,2,4,8]
 * <p>
 * Result: [1,2,4,8]
 * ****************************************************
 * Thought:
 * 1.https://segmentfault.com/a/1190000005922634
 * 复杂度
 * O(N^2) 时间 O(N) 空间
 * <p>
 * 思路
 * 和LIS很相似，dp[i]表示nums数组从0到i的最大集合的size.
 * 这题应该分成两个问题：
 * <p>
 * 得到最大集合size
 * 输出这个集合
 * 对于第一个问题，最大集合size就是dp数组的最大值，可以边画表边维护一个当前最大值;
 * 对于第二个问题，我们要维护一个parent数组，记录nums[i]加入到了哪个集合;
 * dp[i] = max(dp[i], dp[j] + 1), where 0<=j<i
 * <p>
 * 注意这个case：
 * [1,2,4,8,9,72]
 * 到72的时候，往前找到9，可以整除，更新dp[5]为max(1, 2 + 1) = 3,
 * 注意此时应该继续往前找，不能停，直到找到8,发现dp[3] + 1 = 5 > 3，于是更新dp[i]
 * 注意就是不能停，找到一个能整除并不够，前面有可能有更大的啊~~
 * <p>
 * <p>
 * ****************************************************
 * Hindsight:
 * 1.首先是,不太会做
 * 2.其次是,看了答案后,有了思路,解法可以建立起来啦
 * ****************************************************
 * Time: 120 mins
 * Beat: 87%
 * Bug: -
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No368_Largest_Divisible_Subset {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.sort(nums);
        //fill with dp
        Arrays.fill(dp, 1);
        //if the value of parent is -1, then itself is a set
        Arrays.fill(parent, -1);

        int max = 1, max_index = -1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                //dp[i] < dp[j] + 1条件是必须的,保证了集合最大化
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    // dp[j] + 1 直接决定了上面循环中的条件
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                    if (dp[i] > max) {
                        max = dp[i];
                        max_index = i;
                    }
                }
            }
        }
        return getReslt(nums, parent, max_index);
    }

    private List<Integer> getReslt(int[] nums, int[] parent, int max_index) {
        List<Integer> result = new ArrayList<>();
        if (max_index == -1) {
            result.add(nums[0]);
            return result;
        }
        int iter = max_index;
        while (iter != -1) {
            result.add(nums[iter]);
            iter = parent[iter];
        }
        return result;
    }


}
