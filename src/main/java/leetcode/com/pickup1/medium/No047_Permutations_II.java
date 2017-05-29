package leetcode.com.pickup1.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 8/11/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/permutations-ii/
 * **************************************************************
 * Description:
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * <p>
 * For example,
 * [1,1,2] have the following unique permutations:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * **************************************************************
 * Thought:
 * 1.首先就是要排序,在开始的时候,就要保证数组有序,确保生成unique permutation
 * **************************************************************
 * Time: 30 mins
 * Beat: 77%
 * Bug: 1
 * **************************************************************
 * Hindsight:
 * 1.苦思无解啊,觉得原来的这个递归的解法还是挺好用的,可是不方便处理重复的元素呢
 * 2.觉得需要增加一个数据结构来处理重复的元素,但是这其中的算法关系并没有想的很清楚啊
 * 3.看了第一遍的答案,才意识到可以用一个数组来处理这个重复的元素呢!!!
 * 数组的好处,在于一一对应,能够精确把握当前前进的路径,保证,那么如何会让这个算法走重复的路径呢?
 * 好像是,走到当前位置的时候,如果前一个元素和自己的值相同,且没有遍历过,那么这个时候,就要小心出现重复的数列啦!!!!
 * 4.扫了一眼答案和关键算法后,把题目解了。还是应该灵活解题,不拘泥于某一种固化思维啊
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No047_Permutations_II {
    public static void main(String[] args) {
        No047_Permutations_II obj = new No047_Permutations_II();
        obj.permuteUnique(new int[]{1,1, 2});
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        List<Integer> solution = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        helper(nums, solution, result, visited);

        return result;
    }

    private void helper(int[] nums, List<Integer> solution, List<List<Integer>> result, boolean[] visited) {
        if (solution.size() == nums.length) {
            result.add(new ArrayList<>(solution));
            System.out.println(solution);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            //bu1: i-1>=0 则 要求i>=1
            if (i >= 1 && nums[i - 1] == nums[i] && !visited[i - 1]) {
                continue;
            }

            visited[i] = true;
            solution.add(nums[i]);

            helper(nums, solution, result, visited);
            solution.remove(solution.size() - 1);
            visited[i] = false;

        }

    }


}
