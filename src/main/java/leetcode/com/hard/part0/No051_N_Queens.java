package leetcode.com.hard.part0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jason on 2016/1/12.
 * ************************************************************
 * Locations:
 * https://leetcode.com/problems/n-queens/
 * ************************************************************
 * Description:
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 * <p>
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * <p>
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * ************************************************************
 * Analysis:
 * 1.逐行的对所有column进行处理,保证了踩到所有的点
 * ************************************************************
 * ************************************************************
 * ************************************************************
 */
public class No051_N_Queens {
    public static void main(String[] args) {
        No051_N_Queens obj = new No051_N_Queens();
        obj.solveNQueens(4);
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] c : board) {
            //bug1: 初期化整个数组
            Arrays.fill(c, '.');
        }

        //定义queen是否使用的数组
        boolean[] occupied = new boolean[n];
        helper(result, board, occupied, 0, n);
        return result;
    }

    private void helper(List<List<String>> result, char[][] curr, boolean[] col_occupied, int r, int n) {
        if (r == n) {
            System.out.println("Answer:");
            List<String> list = new ArrayList<>();
            //bug2:don't know how to setup the result
            for (char[] row : curr) {
                //bug3:don't know how to chang char[] to String
                System.out.println(new String(row));
                list.add(new String(row));
            }
            result.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(curr, col_occupied, r, i, n)) {
                curr[r][i] = 'Q';
                col_occupied[i] = true;
                //递归处理r+1行的数据
                helper(result, curr, col_occupied, r + 1, n);
                curr[r][i] = '.';
                col_occupied[i] = false;
            }

        }
    }

    private boolean isValid(char[][] curr, boolean[] col_occupied, int row, int col, int n) {
        //bug3:因为col_occupied已经对相应的列进行了check,且Row是递增的,在row上只有一个被标记了Q,所以只需check左上和右上的两条对角线
        if (col_occupied[col]) return false;
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (curr[row - i][col - i] == 'Q') return false;
        }
        for (int i = 1; row - i >= 0 && col + i < n; i++) {
            if (curr[row - i][col + i] == 'Q') return false;
        }
        return true;
    }
}
