package leetcode.com.pickup1.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016/7/28.
 * ****************************************************
 * Hindsight:
 * 什么事pascal三角？就是杨辉三角
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No119_Pascal_Triangle_II {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> prev = new ArrayList<>();

        if (rowIndex < 0) return prev;
        prev.add(1);
        for (int i = 1; i < rowIndex + 1; i++) {
            List<Integer> solution = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                solution.add(-1);
            }

            solution.set(0, prev.get(0));
            solution.set(solution.size() - 1, prev.get(i - 1));
            for (int j = 1; j < i; j++) {
                solution.set(i, prev.get(j - 1) + prev.get(j));
            }
            prev = solution;
        }

        return prev;


    }
}
