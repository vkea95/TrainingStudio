package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/1/11.
 * Locations:
 * https://leetcode.com/problems/permutations-ii/
 * *************************************************
 * Descriptions:
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * <p>
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 * *************************************************
 * Solutions:
 * It's similar with the No.046, check it pls
 * *************************************************
 * Comments:
 */
public class No047_Permutations_II {
    public static void main(String[] args) {
        No047_Permutations_II permutations_ii = new No047_Permutations_II();
        permutations_ii.permuteUnique(new int[]{-1, 2, -1, 2, 1, -1, 2, 1});

    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> stack = new ArrayList<>();
        int size = nums.length;
        int next = -1;
        stack.add(next);
        while (stack.size() != 0) {
            int last = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            next = -1;
            for (int i = last + 1; i < size; i++) {
                if (!stack.contains(i)) {
                    stack.add(i);
                    next = i;
                    break;
                }
            }

            if (next == -1) continue;

            for (int i = 0; i < size; i++) {
                if (!stack.contains(i)) stack.add(i);
            }
            List<Integer> permutation = new ArrayList<>();
            for (int i = 0; i < stack.size(); i++)
                permutation.add(nums[stack.get(i)]);
            result.add(permutation);
        }
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++)
                System.out.print(result.get(i).get(j) > 0 ? " " + result.get(i).get(j) + " " : result.get(i).get(j) + " ");
            System.out.println();
        }
        System.out.println("result.heights(): " + result.size());
        System.out.println("8!:" + 8 * 7 * 6 * 5 * 4 * 3 * 2);
        return result;
    }
}
