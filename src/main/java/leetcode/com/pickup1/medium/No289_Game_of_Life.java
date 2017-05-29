package leetcode.com.pickup1.medium;

/**
 * Created by tclresearchamerica on 6/17/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/game-of-life/
 * ****************************************************
 * Description:
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with
 * its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above
 * Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * <p>
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update
 * some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would
 * cause problems when the active area encroaches the border of the array. How would you address these problems?
 * ****************************************************
 * Thoughts:
 * 当时解了好久的一道题,记得使用DP,但是实际上,我连题都没看懂,不知道该如何更新这个数组...
 * 其实看了 答案后,也还是不太明白,就是当一个活点和周围有三个或更多活点的时候,状态该如何更新
 * 看着答案又敲了一遍,还是没有一遍过,并且还有不太理解的地方
 * ****************************************************
 * Time: 30mins
 * Beats: 12%
 * Bug: 2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No289_Game_of_Life {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = 0;
                //bug1:判断x和y的上界的时候,因为是大于号,所以要用到i+2,
                for (int x = Math.max(0, i - 1); x < Math.min(m, i + 2); x++) {
                    //bug2:j++ --> y++
                    for (int y = Math.max(0, j - 1); y < Math.min(n, j + 2); y++) {
                        lives += board[x][y] & 1;
                    }
                }
                //Bug2:判断方法
                // 如果自己是活的，周边有两个活的，lives是3
                // 如果自己是死的，周边有三个活的，lives是3
                // 如果自己是活的，周边有三个活的，lives减自己是3
                if (lives == 3 || lives - board[i][j] == 3) {
                    board[i][j] |= 2;
                }


            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }

    }
}
