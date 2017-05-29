package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 8/12/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/subsets-ii/
 * **************************************************************
 * Description:
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * For example,
 * If nums = [1,2,2], a solution is:
 * <p>
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * **************************************************************
 * Time: 15 mins
 * Beat:36
 * Bug:1
 * **************************************************************
 * Hindsight:
 * 1.看了九章的视频,再做的,思路更加清晰,意识到用集合+状态迁移的方式来可视化解题
 * 2.问题的关键是:在备选集合中,只选择第一个重复的数字,所以要加一个判断
 * 3.所以要增加一个状态保持数组
 * 4.相当于subset基础上增加了一个filter
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No090_Subsets_II {
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        boolean[] visited = new boolean[nums.length];
        //bug1:没有排序
        Arrays.sort(nums);

        helper(result, new ArrayList<Integer>(), nums, visited, 0);
        return result;
    }


    private void helper(List<List<Integer>> result, List<Integer> subset, int[] nums, boolean[] visited, int pos) {
        result.add(new ArrayList<>(subset));

        for (int i = pos; i < nums.length; i++) {

            if (i >= 1 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            subset.add(nums[i]);
            helper(result, subset, nums, visited, i + 1);
            visited[i] = false;
            subset.remove(subset.size() - 1);
        }

    }

}
