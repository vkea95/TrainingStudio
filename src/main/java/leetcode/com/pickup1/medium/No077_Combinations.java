package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/17/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/combinations/
 * ****************************************************
 * Description:
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * ****************************************************
 * Time: 10 mins
 * Beats: 12%
 * Bug: 0
 * ****************************************************
 * Thoughts:
 *
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No077_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();

        combineHelper(result, solution, 1, n, k);

        return result;
    }

    private void combineHelper(List<List<Integer>> result, List<Integer> solution, int start, int n, int k) {
        if (k == 0) {
            result.add(new ArrayList<>(solution));
            return;
        }
        for (int i = start; i <= n; i++) {
            solution.add(i);
            combineHelper(result, solution, i + 1, n, k - 1);
            solution.remove(solution.size() - 1);
        }
    }


    public List<List<Integer>> combine_dfs(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<Integer>(), k, 1, n - k + 1);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list, int kLeft, int from, int to) {
        if (kLeft == 0) {
            ans.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = from; i <= to; ++i) {
            list.add(i);
            dfs(ans, list, kLeft - 1, i + 1, to + 1);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combine_faster(int n, int k) {

        if (n == 0 || k == 0 || k > n) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        for (int i = 1; i <= n + 1 - k; i++) res.add(Arrays.asList(i));
        for (int i = 2; i <= k; i++) {
            List<List<Integer>> tmp = new ArrayList<List<Integer>>();
            for (List<Integer> list : res) {
                for (int m = list.get(list.size() - 1) + 1; m <= n - (k - i); m++) {
                    List<Integer> newList = new ArrayList<Integer>(list);
                    newList.add(m);
                    tmp.add(newList);
                }
            }
            res = tmp;
        }
        return res;
    }
}
