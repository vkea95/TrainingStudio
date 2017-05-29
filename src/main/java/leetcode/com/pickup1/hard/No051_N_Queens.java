package leetcode.com.pickup1.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tclresearchamerica on 6/8/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/n-queens/
 * ****************************************************
 * Description:
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens
 * attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate
 * a queen and an empty space respectively.
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No051_N_Queens {

    public static void main(String[] args) {
        No051_N_Queens obj = new No051_N_Queens();
        obj.solveNQueens(5);
    }

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];
        for (char[] c : board) {
            //bug1: 初期化整个数组
            Arrays.fill(c, '.');
        }

        dfs(board, 0, n, 0, 0, 0,0);
        return this.result;
    }

    private void dfs(char[][] board, int row, int n, int column, int diag, int antiDiag, int temp) {
        System.out.println("=============================================================");
        System.out.printf("row: %d, true col: %d, column: %d, diag: %d, antiDiag: %d ", row, temp, column, diag, antiDiag);
        System.out.println();
        System.out.println("column: " + Integer.toBinaryString(column));
        System.out.println("diag: " + Integer.toBinaryString(diag));
        System.out.println("antiDiag: " + Integer.toBinaryString(antiDiag));
        if (row == n) {
            System.out.println("=============================Answers================================");
            System.out.printf("row: %d, true col: %d, column: %d, diag: %d, antiDiag: %d ", row, temp, column, diag, antiDiag);
            System.out.println();
            System.out.println("column: " + Integer.toBinaryString(column));
            System.out.println("diag: " + Integer.toBinaryString(diag));
            System.out.println("antiDiag: " + Integer.toBinaryString(antiDiag));
            List<String> solution = new ArrayList<>();
            for (char[] chars : board) {
                solution.add(new String(chars));
            }
            this.result.add(solution);
            return;
        }
        for (int i = 0; i < n; ++i) {
            boolean isColSafe = ((1 << i) & column) == 0;
            //bug1: hard to understand ...
            //此处的diag & antiDiag的方法采用的对角线考虑法 row-i & row+i分别是不同方向的对角线
            boolean isDiagSafe = ((1 << (n - 1 + row - i)) & diag) == 0;
            boolean isAntiDiagSafe = ((1 << (row + i)) & antiDiag) == 0;
            if (isColSafe && isDiagSafe && isAntiDiagSafe) {
                board[row][i] = 'Q';
                dfs(board, row + 1, n, (1 << i) | column, (1 << (n - 1 + row - i)) | diag, (1 << (row + i)) | antiDiag, i);
                board[row][i] = '.';
            }
        }
    }


    public List<List<String>> solveNQueens_TLE(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] boards = new char[n][n];
        //bug1:setup the isValid array
        boolean[][] isValid = new boolean[n][n];

        for (char[] chars : boards)
            Arrays.fill(chars, '.');
        nQueenHelper(boards, isValid, 0, result);
        return result;
    }

    private void nQueenHelper(char[][] boards, boolean[][] isValid, int row, List<List<String>> result) {
        if (row == boards.length) {
            List<String> solution = new ArrayList<>();
            for (char[] chars : boards) {
                solution.add(new String(chars));
            }
            result.add(solution);
            return;
        }
        for (int i = 0; i < boards.length; i++) {
            if (isValid(isValid, row, i)) {
                boards[row][i] = 'Q';
                //bug1: forget to modify the flag array
                isValid[row][i] = true;
                nQueenHelper(boards, isValid, row + 1, result);
                boards[row][i] = '.';
                isValid[row][i] = false;
            }
        }
    }

    private boolean isValid(boolean[][] isValid, int row, int col) {
        if (isValid[row][col]) return false;
        for (int i = 1; (row - i) >= 0 && (col - i) >= 0; i++) {
            if (isValid[row - i][col - i]) return false;
        }
        for (int i = 1; (row - i) >= 0 && (col + i) < isValid.length; i++) {
            if (isValid[row - i][col + i]) return false;
        }
        return true;
    }
}
