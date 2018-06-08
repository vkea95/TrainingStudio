package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jason on 2016/1/31.
 * Locations:
 * https://leetcode.com/problems/4sum/
 * ****************************************
 * Description:
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution set must not contain dupNumber quadruplets.
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * A solution set is:
 * (-1,  0, 0, 1)
 * (-2, -1, 1, 2)
 * (-2,  0, 0, 2)
 * *****************************************
 * Solutions:
 * 参照3sum的做法呗，套用下
 * *****************************************
 * Tips:
 * *****************************************
 * Bugs：
 * 1.总是忘记跳过重复元素的这个过程，
 */
public class No018_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        List<Integer> pairs = new ArrayList<>();
                        pairs.add(nums[i]);
                        pairs.add(nums[j]);
                        pairs.add(nums[left]);
                        pairs.add(nums[right]);
                        result.add(pairs);
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while ((left < right && nums[right] == nums[right + 1])) {
                            right--;
                        }
                    }
                }
            }

        }

        return result;
    }
}
