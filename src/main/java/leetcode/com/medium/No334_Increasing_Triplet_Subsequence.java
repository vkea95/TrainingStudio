package leetcode.com.medium;

import org.omg.PortableInterceptor.INACTIVE;

/**
 * Created by tclresearchamerica on 5/12/16.
 * Location:
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * ****************************************************
 * Description:
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * <p>
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * <p>
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * <p>
 * Given [5, 4, 3, 2, 1],
 * return false.
 * <p>
 * <p>
 * ****************************************************
 * Analysis:
 * 看上去答案显而易见,直接比较即可了嘛,考虑下复杂度,感觉比较容易TLE哦
 * 想想比较其实是不可避免的,那么就要减少比较次数,且一旦有结果就直接返回好了
 * 1.DP如何?设有数组[2],初期化为MAX,数组元素n,我们画在纸上面看看,这样建一个同样大小的数组,
 * 初期值均为零,从原来的数组的第1项(index=1),找到距离它最近的比他小的数,然后用那个数的新数组的值为基数+1,如果加的和大于2,即返回true
 * bug1:找到的第一个比他小的数,也许是不对的比如: 1,0,0,2,0,10
 * 2.网络答案:快很多,和我的思路吻合,但是非常简洁,直到核心
 * 设2个min值,min1和min2,如果一旦两个元素是递增的趋势,如果index较小的元素比min大,即为真.他成功的将思路化简了,
 * min1取得0~i的最小值,min2取得是0~i+1的最小值.如果nums[i+1]<=nums[i] 则 只要考虑nums[,然后若nums[i+1]>nums[i],
 * 则只要保证nums[i]>min1即可.
 * ****************************************************
 */
public class No334_Increasing_Triplet_Subsequence {
    public static void main(String[] args) {
        No334_Increasing_Triplet_Subsequence obj = new No334_Increasing_Triplet_Subsequence();
        int[] nums = {1, 2, 3, 4, 5};
        obj.increasingTriplet(nums);
    }

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length <= 2) return false;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {

            min1 = Math.min(nums[i], min1);

            if (nums[i] > min2 || nums[i + 1] > min2) {
                return true;
            }
            if (nums[i] < nums[i + 1]) {
                if (nums[i] > min1) return true;

                min2 = Math.min(min2, nums[i + 1]);
            }
        }
        return false;

    }

    public boolean increasingTriplet_slow(int[] nums) {
        if (nums == null || nums.length <= 2) return false;
        int[] compare = new int[nums.length];

        for (int i = 1; i < nums.length; i++) {
//            int j=i;
            for (int j = i - 1; j >= 0; j--) {
                //bug2:没有考虑数字有1,0,2,0,5
                //bug3:忘记 compar[0]=0
                if (nums[i] > nums[j]) {
                    compare[i] = compare[j] + 1;
                    if (compare[i] >= 2) {
                        return true;
                    }

                }
            }
        }

        return false;
    }
}
