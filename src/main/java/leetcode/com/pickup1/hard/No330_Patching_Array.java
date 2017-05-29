package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 8/16/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/patching-array/
 * ****************************************************
 * Description:
 * Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number
 * in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number
 * of patches required.
 * <p>
 * Example 1:
 * nums = [1, 3], n = 6
 * Return 1.
 * <p>
 * Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 * Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 * Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 * So we only need 1 patch.
 * <p>
 * Example 2:
 * nums = [1, 5, 10], n = 20
 * Return 2.
 * The two patches can be [2, 4].
 * <p>
 * Example 3:
 * nums = [1, 2, 2], n = 5
 * Return 0.
 * <p>
 * ****************************************************
 * Thought:
 * 看了几遍答案后,意识到题目是要算下需要补充几个数字才可以让数组中的数字相加覆盖1~n
 * 看上去还是数学问题,
 * ****************************************************
 * Ref:
 * https://discuss.leetcode.com/topic/35494/solution-explanation/2
 * Explanation:
 * Explanation
 * <p>
 * Let miss be the smallest sum in [0,n] that we might be missing. Meaning we already know we can build all sums in
 * [0,miss). Then if we have a number num <= miss in the given array, we can add it to those smaller sums to
 * build all sums in [0,miss+num). If we don't, then we must add such a number to the array, and it's best to
 * add miss itself, to maximize the reach.
 * <p>
 * Example: Let's say the input is nums = [1, 2, 4, 13, 43] and n = 100. We need to ensure that all sums
 * in the range [1,100] are possible.
 * <p>
 * Using the given numbers 1, 2 and 4, we can already build all sums from 0 to 7, i.e., the range [0,8).
 * But we can't build the sum 8, and the next given number (13) is too large. So we insert 8 into the array.
 * Then we can build all sums in [0,16).
 * <p>
 * Do we need to insert 16 into the array? No! We can already build the sum 3, and adding the given 13 gives us sum 16.
 * We can also add the 13 to the other sums, extending our range to [0,29).
 * <p>
 * And so on. The given 43 is too large to help with sum 29, so we must insert 29 into our array.
 * This extends our range to [0,58). But then the 43 becomes useful and expands our range to [0,101).
 * At which point we're done.
 * https://discuss.leetcode.com/topic/35517/share-my-greedy-solution-by-java-with-simple-explanation-time-1-ms
 * ****************************************************
 * Hindsight:
 * 1.题目没有理解清楚,算法内容要搞懂
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No330_Patching_Array {
    public static void main(String[] args) {
        No330_Patching_Array obj = new No330_Patching_Array();
        obj.minPatches(new int[]{5}, 6);
    }

    public int minPatches(int[] nums, int n) {
        //bug1: 因为是不断地累加,所以存在超出int最大值范围的可能
        long miss = 1;
        int added = 0;
        int i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i++];
            } else {
                miss += miss;
                added++;
            }
        }
        return added;
    }

    public static int minPatches_greedy(int[] nums, int n) {
        long max = 0;
        int cnt = 0;
        for (int i = 0; max < n; ) {
            if (i >= nums.length || max < nums[i] - 1) {
                max += max + 1;
                cnt++;
            } else {
                max += nums[i];
                i++;
            }
        }
        return cnt;
    }
}
