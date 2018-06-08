package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/19/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/combination-sum/
 * ****************************************************
 * Description:
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the
 * candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain dupNumber combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 * [
 * [7],
 * [2, 2, 3]
 * ]
 * ****************************************************
 * Thoughts:
 * 1.前提条件:A.求组合 B.无重复元素 C.都是正数
 * 2.优化条件:询问是否可以改变元素顺序
 * 3.修正算法
 * ****************************************************
 * Bug1:理解错了题意,原来虽然元素是不重复的,但是却可以重复使用,所以答案不正确.
 * <p>
 * ****************************************************
 * Time: 25 mins
 * Beats: 78% -->99%
 * Bug:3
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No039_Combination_Sum {
    public static void main(String[] args) {
        No039_Combination_Sum obj = new No039_Combination_Sum();
        int[] arr = new int[]{2, 3, 6, 7};
        obj.combinationSum(arr, 7);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        helper(result, new ArrayList<Integer>(), candidates, 0, target);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> solution, int[] candidates, int from, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(solution));
            return;
        }
        for (int i = from; i < candidates.length && candidates[i] <= target; i++) {
            //bug2:没有判断target-candidates[i]==0的条件导致结果不正确
            //optimization:将必须计算的部分,挪出来,结果存入某个变量中
            int tmp = target - candidates[i];
            if (tmp == 0 || tmp >= candidates[i]) {
                solution.add(candidates[i]);
                //bug1:from+1 --> from,否则含有重复元素的结果集不会出现
                //bug3:from ->i 下标弄错了啊！！！
                helper(result, solution, candidates, i, tmp);
                solution.remove(solution.size() - 1);
            }
        }
    }
}
