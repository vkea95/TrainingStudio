package leetcode.com.easy.part0;

import java.util.Arrays;

/**
 * Created by jason on 2016/2/5.
 * Locations:
 * https://leetcode.com/problems/valid-sudoku/
 * **********************************************
 * Descriptions:
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 * ***********************************************
 * Solutions:
 * ***********************************************
 * Tips:
 *  在算子矩阵的时候，用了3个变量i,j和k，前两个的增幅是3，保证每次会跨越1个子矩阵，最后的k，在处理x坐标时用i+k/3，保证
 *  每加3会移到下一行，j+k%3，保证每次都加1，但是不会超过3
 */
public class No036_Valid_Sudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[] visited = new boolean[9];

        //row
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!process(visited, board[i][j]))
                    return false;
            }
        }
        //col
        for (int i = 0; i < 9; i++) {
            Arrays.fill(visited, false);
            for (int j = 0; j < 9; j++) {
                if (!process(visited, board[j][i]))
                    return false;
            }
        }


        //row
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Arrays.fill(visited, false);
                for (int k = 0; k < 9; k++) {
                    if (!process(visited, board[i + k / 3][j + k % 3]))
                        return false;

                }
            }
        }
        return true;
    }

    private boolean process(boolean[] visited, char digit) {
        if (digit == '.') {
            return true;
        }

        int num = digit - '0';
        if (num < 1 || num > 0 || visited[num - 1]) {
            return false;
        }

        visited[num - 1] = true;
        return true;
    }
}
