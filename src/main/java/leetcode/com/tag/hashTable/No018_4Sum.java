package leetcode.com.tag.hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JianZhang on 10/6/17.
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note: The solution set must not contain duplicate quadruplets.
 * <p>
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * Solutions:
 * 1. don't forget how to avoid the duplicate case,
 * [1,0,-1,0,-2,2]
 * 0
 */
public class No018_4Sum {

    public static void main(String[] args) {
        No018_4Sum obj = new No018_4Sum();
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};

        obj.fourSum(nums, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        //step 1: sort
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> candidate = null;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1])
                continue;//avoid the duplicate cases,becuase the first one has combined with every one
            for (int j = i + 1; j < nums.length; j++) {
//                bug2: 此处需要增加 j!=i+1, 避免和外循环的数字进行冲突
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        candidate = new ArrayList<>();
                        candidate.add(nums[i]);
                        candidate.add(nums[j]);
                        candidate.add(nums[left]);
                        candidate.add(nums[right]);
                        resultList.add(candidate);
                        //bug 4: 处理完毕必须马上对left和right进行调整,否则就会无限循环了
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        //bug 3: right 要从--才可以
//                        while (left < right && nums[right] == nums[right + 1]) right--;
                        while (left < right && nums[right] == nums[right - 1]) right++;
                    }
                }
                //bug 2: wrong loop & analysis
//                for (int k = j + 1; k < nums.length; k++) {
//                    if (nums[k] == nums[k - 1]) continue;
//                    for (int l = k + 1; l < nums.length; l++) {
//                        if (nums[l] == nums[l - 1]) continue;
//                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
//                            candidate = new ArrayList<>();
//                            candidate.add(nums[i]);
//                            candidate.add(nums[j]);
//                            candidate.add(nums[k]);
//                            candidate.add(nums[l]);
//                            resultList.add(candidate);
//                        }
//
//                    }
//                }
            }
        }
        return resultList;
    }
}
