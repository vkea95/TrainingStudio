package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/20/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * ****************************************************
 * Description:
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which
 * the sum ≥ s. If there isn't one, return 0 instead.
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * ****************************************************
 * Thoughts:
 * 1.不能排序
 * 2.如果是倒序处理会不会效果更好呢
 * 3.TLE的原因是没有利用之前求和留下的数值,
 * ****************************************************
 * Thoughts:
 * 1.充分利用之前累加后留下的结果
 * 2.一旦超出就把最前面的元素踢掉,然后继续循环处理
 * Time: 25 mins
 * Beat: 13%
 * Bug:1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No209_Minimum_Size_Subarray_Sum {
    public static void main(String[] args) {
        No209_Minimum_Size_Subarray_Sum obj = new No209_Minimum_Size_Subarray_Sum();
        int[] arr = new int[]{1, 2, 3, 4, 5};
        obj.minSubArrayLen(11, arr);
    }

    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        int start = 0;
        int n = nums.length;
        int sum = 0;
        int cnt = 0;
        //bug1:i应该从0开始计数,而不是逆序计数
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            cnt++;
            while (sum >= s) {
                min = min < cnt ? min : cnt;
                sum -= nums[start++];
                cnt--;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int minSubArrayLen_TLE(int s, int[] nums) {
        int min = Integer.MAX_VALUE;

        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] >= s) return 1;
            else {
                int tmp = s - nums[i];
                int cnt = 1;
                for (int j = i - 1; j >= 0; j--) {
                    tmp -= nums[j];
                    cnt++;
                    if (tmp <= 0) {
                        min = min < cnt ? min : cnt;
                        break;
                    }
                }

            }
        }
        if (min == Integer.MAX_VALUE) {
            return 0;
        } else {
            return min;
        }

    }
}
