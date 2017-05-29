package leetcode.com.easy.part2;

/**
 * Created by tclresearchamerica on 5/3/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/game-of-life/
 * *******************************************
 * Description:
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
 * devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts
 * with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from
 * the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * <p>
 * <p>
 * *******************************************
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some
 * cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
 * problems when the active area encroaches the border of the array. How would you address these problems?
 * *******************************************
 * Analysis:
 * 1.猜想会用到并查集或是深度优先的算法.
 * *******************************************
 */
public class No289_Game_of_Life {
    public void gameOfLife(int[][] board) {

    }
}
