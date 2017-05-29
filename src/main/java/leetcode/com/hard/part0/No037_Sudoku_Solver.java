package leetcode.com.hard.part0;

/**
 * Created by tclresearchamerica on 5/29/16.
 * ************************************************
 * Location:
 * https://leetcode.com/problems/sudoku-solver/
 * ************************************************
 * Description:
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * Empty cells are indicated by the character '.'.
 * <p>
 * You may assume that there will be only one unique solution.
 * ************************************************
 * Solution:
 * 1.将行,列和格子组,分别建立二维数组9*9.
 * 2.预处理导入输入的数组
 * 3.将发现的数字直接把应得数组的格子设成true.
 * 4.递归调用backtracking方法,只有当他们都为空的时候,才会循环,且在y=8时,行加1,列归零
 * 5.当行为9时,代表回溯结束,直接返回即可
 * ************************************************
 * ************************************************
 * ************************************************
 * ************************************************
 * ************************************************
 */
public class No037_Sudoku_Solver {

    private boolean[][] row;
    private boolean[][] col;
    private boolean[][] subGrid;
    private boolean isFound;

    public static void main(String[] args) {
        No037_Sudoku_Solver obj = new No037_Sudoku_Solver();
        char[][] board = new char[][]{{'.', '.', '9', '7', '4', '8', '.', '.', '.'}};
    }

    public void solveSudoku(char[][] board) {
        row = new boolean[9][9];
        col = new boolean[9][9];
        subGrid = new boolean[9][9];
        isFound = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0' - 1;
                    int k = i / 3 * 3 + j / 3;
                    row[i][num] = true;
                    col[j][num] = true;
                    subGrid[k][num] = true;
                }
            }
        }
        backTracking(board, 0, 0);
    }

    public void backTracking(char[][] board, int x, int y) {
        if (x == 9) {
            isFound = true;
            return;
        }

        int z = x / 3 * 3 + y / 3;
        if (board[x][y] == '.') {
            for (int i = 1; i <= 9; i++) {
                int num = i - 1;
                if (row[x][num] == false &&
                        col[y][num] == false &&
                        subGrid[z][num] == false) {
                    char ch = (char) (i + '0');
                    board[x][y] = ch;
                    row[x][num] = true;
                    col[y][num] = true;
                    subGrid[z][num] = true;
                    if (y == 8)
                        backTracking(board, x + 1, 0);
                    else
                        backTracking(board, x, y + 1);
                    if (isFound)
                        break;
                    board[x][y] = '.';
                    row[x][num] = false;
                    col[y][num] = false;
                    subGrid[z][num] = false;

                }
            }
        } else {
            int num = board[x][y] - '0' - 1;
            row[x][num] = true;
            col[y][num] = true;
            subGrid[z][num] = true;

            if (y == 8)
                backTracking(board, x + 1, 0);
            else
                backTracking(board, x, y + 1);

        }


    }
}
