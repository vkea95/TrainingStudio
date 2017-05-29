package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/8/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/subsets/
 * ****************************************************
 * Given a set of distinct integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 * ****************************************************
 * Thoughts:
 * 又是一道完全没有想法的问题,
 * 排序肯定是必须的啦
 * <p>
 * ****************************************************
 * recursive:
 * Time: 60mins
 * Beats: 18%
 * Bug: 1
 * Double circle:
 * Time: 60mins
 * Beats: 60%
 * bug:1
 * 不推荐使用位移动的那个方法,会牵扯到超过32位如何处理的问题
 * ****************************************************
 */
public class No078_Subsets {

    public static void main(String[] args) {
        No078_Subsets obj = new No078_Subsets();
        obj.subsets(new int[]{1,2,3});
    }
    public List<List<Integer>> subsets(int[] nums) {


        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        result.add(tmp); // add empty set
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int n = result.size();
            for (int j = 0; j < n; j++) {
                // NOTE: must create a new tmp object, and add element to it.
                tmp = new ArrayList<>(result.get(j));
                tmp.add(nums[i]);
                result.add(tmp);
            }
        }
        return result;
    }

    public List<List<Integer>> subsets_resursive(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();

        if (nums == null) {
            return result;
        }
//        result.add(solution);
        Arrays.sort(nums);
        subsetsHelper(nums, 0, result, solution);
        return result;
    }

    private void subsetsHelper(int nums[], int start, List<List<Integer>> result, List<Integer> solution) {
        //bug1:需要new一个新的对象,否则只有一个对象,里面的值最后都是空
        result.add(new ArrayList<>(solution));
        int len = nums.length;

        for (int i = start; i < len; i++) {
            solution.add(nums[i]);
            subsetsHelper(nums, i + 1, result, solution);
            solution.remove(solution.size() - 1);
        }

    }

}
