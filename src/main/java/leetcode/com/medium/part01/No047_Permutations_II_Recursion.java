package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.Arrays;
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
 * But because there are duplicated number in the array, so we need a visited array to mark if the element is visited.
 * *************************************************
 * Comments:
 * 1.因为会有重复元素，所以可以考虑先进行排序，为后面的优化做准备
 * 2.对于重复的元素，如果进行全排列的话，会有重复的排列产生，所以要考虑去重，
 * <p>
 * To be tested......
 */
public class No047_Permutations_II_Recursion {
    public static void main(String[] args) {
        No047_Permutations_II_Recursion permutations_ii = new No047_Permutations_II_Recursion();
        permutations_ii.permuteUnique(new int[]{1, 2, 2});

    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> stack = new ArrayList<>();
        int[] visited = new int[nums.length];

        Arrays.sort(nums);//sort optimization!!! remove the duplicated ones
        helper(nums, visited, result, stack);
//        System.out.println("result.size(): " + result.size());
//        System.out.println("8!:" + 8 * 7 * 6 * 5 * 4 * 3 * 2);
        return result;
    }

    public void helper(int[] nums, int[] visited, List<List<Integer>> result, List<Integer> permutation) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) {
                System.out.println("Continue");
                continue;
            }
            if(  (i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)){

                System.out.println("Visited【i-1】==0 continue");
                continue;
            }
            /*
            上面的判断其实并不影响最终结果，目的是为了让dfs能更快----------------->会影响最终结果
            云因在于visited【i-1】==0这个条件，表示，现在开始处理的某个数字
            已经被处理过了，所以continue
            可以看下输出的log就明白了

            上面这一连串判断条件，重点在于要能理解!visited.contains(i-1)
            要理解这个，首先要明白i作为数组内序号，i是唯一的
            给出一个排好序的数组，[1,2,2]
            第一层递归            第二层递归            第三层递归
            [1]                    [1,2]                [1,2,2]
            序号:[0]                 [0,1]            [0,1,2]
            这种都是OK的，但当第二层递归i扫到的是第二个"2"，情况就不一样了
            [1]                    [1,2]                [1,2,2]
            序号:[0]                [0,2]                [0,2,1]
            所以这边判断的时候!visited.contains(0)就变成了true，不会再继续递归下去，跳出循环
            步主要就是为了去除连续重复存在的，很神奇反正 = =||
        */
            visited[i] = 1;//mark that this element is visited
            permutation.add(nums[i]);
            System.out.println("Before helper ");
            System.out.print("visited: ");
            for (int j = 0; j < visited.length; j++)
                System.out.print(visited[j]+" ");
            System.out.println();
            System.out.print("permutation: ");
            for (int j = 0; j < permutation.size(); j++)
                System.out.print(permutation.get(j) +" ");
            System.out.println();
            helper(nums, visited, result, permutation);
            permutation.remove(permutation.size() - 1);
            visited[i] = 0;//mark that this element is not visited
            System.out.println("After helper ");
            System.out.print("visited: ");
            for (int j = 0; j < visited.length; j++)
                System.out.print(visited[j]+" ");
            System.out.println();
            System.out.print("permutation: ");
            for (int j = 0; j < permutation.size(); j++)
                System.out.print(permutation.get(j) +" ");
            System.out.println();
        }
    }
}
