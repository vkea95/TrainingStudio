package programcreek.com;

import java.util.ArrayList;

/**
 * Created by tclresearchamerica on 5/5/16.
 * ****************************************************
 * Location:
 * http://www.programcreek.com/2016/04/get-target-using-number-list-and-arithmetic-operations-google/
 * ****************************************************
 * Description:
 * Given a list of numbers and a target number, write a program to determine whether the target number
 * can be calculated by applying +-*"/"
 * operations to the number list? You can assume () is automatically added when necessary.
 * For example, given {1,2,3,4} and 21, return true. Because (1+2)*(3+4)=21
 * ****************************************************
 * Analysis:
 * This is a partition problem which can be solved by using depth first search.
 * <p>
 * <p>
 * ****************************************************
 * Thoughts:
 * 我自己肯定很难想到这个解法,即使是看了答案,理解起来还会有点困难,
 * 核心解法:深度优先遍历list,让每个元素都经历四则运算,最后判断其result的element值是否满足要求
 * ****************************************************
 * ****************************************************
 */
public class No001_Get_Target_Number {
    public static boolean isReachable(ArrayList<Integer> list, int target) {
        if (list == null || list.size() == 0) return false;
        int i = 0;
        int j = list.size() - 1;

        ArrayList<Integer> results = getResults(list, i, j, target);

        for (int num : results) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }


    public static ArrayList<Integer> getResults(ArrayList<Integer> list, int left, int right, int target) {
        ArrayList<Integer> result = new ArrayList<>();

        if (left > right) {
            return result;
        } else if (left == right) {
            result.add(list.get(left));
            return result;
        }

        for (int i = left; i < right; i++) {
            ArrayList<Integer> result1 = getResults(list, left, i, target);
            ArrayList<Integer> result2 = getResults(list, i + 1, right, target);

            for (int x : result1) {
                for (int y : result2) {
                    result.add((x + y));
                    result.add((x - y));
                    result.add((x * y));
                    if (y != 0)
                        result.add((x / y));
                }
            }
        }


        return result;
    }

}