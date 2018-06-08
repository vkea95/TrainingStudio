package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 8/12/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/combination-sum-ii/
 * ****************************************************
 * Description:
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the
 * candidate numbers sums to T.
 * <p>
 * Each number in C may only be used once in the combination.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain dupNumber combinations.
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * ****************************************************
 * Time: 15 mins
 * Beat: 36%->78%
 * Bug: 1
 * ****************************************************
 * Hindsight:
 * 1.增加判断target是否小于candidate的判断
 * 2.可以去看第一遍的那个答案,用一个prev变量取代了visited的数组的用法,算是一种优化/化简
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No040_Combination_Sum_II {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates==null||candidates.length==0) return result;

        boolean[] visited=new boolean[candidates.length];
        Arrays.sort(candidates);
        helper(result,new ArrayList<Integer>(),candidates,visited,target,0);
        return result;
    }

    //bug1:没有将下一个的参数传进去
    private void helper(List<List<Integer>> result, List<Integer> solution, int[] candidates,boolean[] visited, int target,int pos) {
        if (target == 0) {
            result.add(new ArrayList<>(solution));
            return;
        }

        for (int i=pos;i<candidates.length;i++) {
            if (candidates[i] > target) {
                break;
            }
            int temp = target - candidates[i];
            if (i>=1&&candidates[i]==candidates[i-1]&&!visited[i-1]){
                continue;
            }
            solution.add(candidates[i]);
            visited[i]=true;
            helper(result, solution, candidates,visited, temp,i+1);
            solution.remove(solution.size() - 1);
            visited[i]=false;

        }


    }
}
