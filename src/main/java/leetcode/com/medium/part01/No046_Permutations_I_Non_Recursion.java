package leetcode.com.medium.part01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/1/10.
 */
public class No046_Permutations_I_Non_Recursion {
    public static void main(String[] args) {
        No046_Permutations_I_Non_Recursion no046 = new No046_Permutations_I_Non_Recursion();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
//        no046.permute(nums);

    }

    /**
     * @param nums: A indexList of integers.
     * @return: A indexList of permutations.
     * **************************************************
     * Solutions:
     * the index of the element in the array is stored in the stack
     * -- it's a good way to use the index of the element in the array
     * Stack的初期值设置成-1，这样保证在循环逻辑的第一步中，取出最后的元素last（-1），
     * 在循环内部设置next flag 为 -1，
     * 在第一个for循环中i的起始值是last+1，保证之前处理过的元素不会在本次循环中被放到stack中
     * 发现第一个不存在于Stack中的元素后，将该元素付给next，并跳出循环
     * 在循环外部，若next为-1，表示在第一个循环中没有向stack中加入新的元素，本次处理无效，continue至while重新开始
     * 若next不为-1，则循环将stack中不存在的元素加入stack
     * 最后将Stack中的元素放入，结果集，然后再进行循环处理
     * **************************************************
     * Comments:
     * Perfect design, the loop operations and the values of the flags (next,last) are very smart
     * The thoughts of design should be memorized.
     *
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations
                = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return permutations;
        }

        int n = nums.length;
        ArrayList<Integer> stack = new ArrayList<Integer>();

        stack.add(-1);
        while (stack.size() != 0) {
            Integer last = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);

            // increase the last number
            int next = -1;
            for (int i = last + 1; i < n; i++) {
                if (!stack.contains(i)) {
                    next = i;
                    break;
                }
            }
            if (next == -1) {
                continue;
            }

            // generate the next permutation
            stack.add(next);
            for (int i = 0; i < n; i++) {
                if (!stack.contains(i)) {
                    stack.add(i);
                }
            }

            // copy to permutations set
            ArrayList<Integer> permutation = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                permutation.add(nums[stack.get(i)]);
            }
            permutations.add(permutation);
        }

        return permutations;
    }
}
