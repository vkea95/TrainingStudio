package leetcode.com.easy.part1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/3/16.
 * Location:
 * https://leetcode.com/problems/pascals-triangle-ii/
 * *******************************************************
 * Description:
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * ********************************************************
 * Solution:
 */
public class No119_Pascals_Triangle_II {
    public List<Integer> getRow(int rowIndex) {
//        List<List<Integer>> rst = new ArrayList<>();
        List<Integer> prev = new ArrayList<>();

        if (rowIndex < 0) return prev;

        prev.add(1);
//        rst.add(prev);

        for (int i = 1; i < rowIndex + 1; i++) {
            List<Integer> solution = new ArrayList<>();

            for (int j = 0; j < i + 1; j++) {
                solution.add(-1);
            }
            solution.set(0, prev.get(0));
            solution.set(solution.size() - 1, prev.get(i - 1));

            for (int j = 1; j < i; j++) {
                solution.set(j, prev.get(j - 1) + prev.get(j));
            }
//            rst.add(solution);
            prev = solution;
        }

        return prev;


    }
}
