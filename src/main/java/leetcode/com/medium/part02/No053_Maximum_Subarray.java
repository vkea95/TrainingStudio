package leetcode.com.medium.part02;

import java.util.ArrayList;

/**
 * Created by jason on 2016/1/20.
 * Locations:
 * https://leetcode.com/problems/maximum-subarray/
 * ****************************************************
 * Descriptions:
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
 * which is more subtle.
 * *****************************************************
 * Solutions:
 * 用前缀和的解决子数组问题：
 * sum[i]=A[0] + ... + A[i]
 * sum[i-j]=sum[j]- sum[i - 1]
 * 构造出新的求和数组之后，用买卖股票I的方式求max即可
 */
public class No053_Maximum_Subarray {

    //Greedy
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);//若sum小于零则将其充值为零再进行累加
        }

        return max;
    }
    //prefix sum(DP)

    public int maxSubArray_DP(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            max = Math.max(max, sum - minSum);//用当前的sum 减去 之前最小的sum得到的值，和曾经最大的结果比较
            minSum = Math.min(minSum, sum);//留存sum中最小的值，为以后做准备
        }

        return max;
    }


    public int maxSubArray(ArrayList<Integer> nums) {
        // write your code
        if (nums.size() == 0)
            return 0;
        int n = nums.size();
        int[] global = new int[n];
        int[] local = new int[n];
        global[0] = nums.get(0);
        local[0] = nums.get(0);
        for (int i = 1; i < n; i++) {
            local[i] = Math.max(nums.get(i), local[i - 1] + nums.get(i));
            global[i] = Math.max(local[i], global[i - 1]);
        }
        return global[n - 1];
    }
}
