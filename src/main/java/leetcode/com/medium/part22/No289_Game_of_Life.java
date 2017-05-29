package leetcode.com.medium.part22;

/**
 * Created by tclresearchamerica on 5/4/16.
 * *******************************************
 * Location:
 * https://leetcode.com/problems/game-of-life/
 * *******************************************
 * Description:
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
 * devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its
 * eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the
 * above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 * *******************************************
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some
 * cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
 * problems when the active area encroaches the border of the array. How would you address these problems?
 * *******************************************
 * Analysis:
 * 1.二维数组,。
 * *******************************************
 * Solution:
 * https://segmentfault.com/a/1190000003819277    --> *
 * Solution_I:
 * <p>
 * 最简单的方法是再建一个矩阵保存，不过当inplace解时，如果我们直接根据每个点周围的存活数量来修改当前值，由于矩阵是顺序遍历的，
 * 这样会影响到下一个点的计算。如何在修改值的同时又保证下一个点的计算不会被影响呢？实际上我们只要将值稍作编码就行了，
 * 因为题目给出的是一个int矩阵，大有空间可以利用。这里我们假设对于某个点，值的含义为
 * <p>
 * 0 : 上一轮是0，这一轮过后还是0
 * 1 : 上一轮是1，这一轮过后还是1
 * 2 : 上一轮是1，这一轮过后变为0
 * 3 : 上一轮是0，这一轮过后变为1
 * 这样，对于一个节点来说，如果它周边的点是1或者2，就说明那个点上一轮是活的。最后，在遍历一遍数组，把我们编码再解回去，
 * 即0和2都变回0，1和3都变回1，就行了
 * Solution_II
 * 另一种编码方式是位操作，将下轮该cell要变的值存入bit2中，然后还原的时候右移就行了。
 * Solution_III
 * 表优化法
 * 复杂度
 * 时间 O(NN) 空间 O(512)
 * <p>
 * 思路
 * 上面的方法实测都比较慢，对于5000*5000的矩阵计算时间都在600-1000ms，甚至比简单的用buffer的方法慢，
 * 我们再介绍一个能将速度提高一倍的方法。
 * 一般来说，优化程序有这么几个思路：
 * <p>
 * 尽量减少嵌套的循环
 * 减少对内存的读写操作
 * 上个解法中，使用多个for循环的就比较慢，如果我们能够直接计算出该点的值而不用for循环就好了。这里我们可以用一个“环境”变量，
 * 表示该点所处的环境，这样我们根据它以及它周围八个点的值就可以直接算出它的环境值，而不需要用for循环来检查周围8个点。
 * 有人说，这不就只是把读取操作放到循环外面来了吗？其实这只是用了优化了第一点，减少循环，对于第二点我们也有优化，
 * 我们计算环境值这样计算，对于以n4为中心的点，其环境为
 * <p>
 * n8  n5  n2
 * n7  n4  n1
 * n6  n3  n0
 * 则环境值environment = n8 * 256 + n7 * 128 + n6 * 64 + n5 * 32 + n4 * 16 + n3 * 8 + n2 * 4 + n1 * 2 + n0 * 1，
 * 这么做的好处是把每一个格子的死活信息都用一个bit来表示，更巧妙地是当我们计算以n1为中心的环境时，是可以复用这些信息的，
 * 我们不用再读取一遍n5, n4, n3, n2, n1, n0的值，直接将上一次的环境值模上64后再乘以8，就是可以将他们都向左平移一格，
 * 这时候再读取三个新的值a, b, c就行了。
 * <p>
 * n8  n5  n2  a
 * n7  n4  n1  b
 * n6  n3  n0  c
 * 通过这种方法，我们将内存的读取次数从每个点九次，变成了每个点三次。另外我们还要预先制作一个表，来映射环境值和结果的关系。
 * 比如环境值为7时，说明n2, n1, n0都是活的，结果应该为1（下一轮活过来）。这里制作表的程序可以这么写：
 * <p>
 * int[] table = new int[512];
 * for(int i = 0; i < 512; i++){
 * int lives = Integer.bitCount(i);
 * if(lives == 3 || (lives - ((i & 16) > 0 ? 1 : 0) == 3)){
 * table[i] = 1;
 * }
 * }
 */
public class No289_Game_of_Life {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = 0;
                // 判断上边
                if (i > 0) {
                    lives += board[i - 1][j] == 1 || board[i - 1][j] == 2 ? 1 : 0;
                }
                // 判断左边
                if (j > 0) {
                    lives += board[i][j - 1] == 1 || board[i][j - 1] == 2 ? 1 : 0;
                }
                // 判断下边
                if (i < m - 1) {
                    lives += board[i + 1][j] == 1 || board[i + 1][j] == 2 ? 1 : 0;
                }
                // 判断右边
                if (j < n - 1) {
                    lives += board[i][j + 1] == 1 || board[i][j + 1] == 2 ? 1 : 0;
                }
                // 判断左上角
                if (i > 0 && j > 0) {
                    lives += board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 2 ? 1 : 0;
                }
                //判断右下角
                if (i < m - 1 && j < n - 1) {
                    lives += board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 2 ? 1 : 0;
                }
                // 判断右上角
                if (i > 0 && j < n - 1) {
                    lives += board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 2 ? 1 : 0;
                }
                // 判断左下角
                if (i < m - 1 && j > 0) {
                    lives += board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 2 ? 1 : 0;
                }
                // 根据周边存活数量更新当前点，结果是0和1的情况不用更新
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 3;
                } else if (board[i][j] == 1) {
                    if (lives < 2 || lives > 3) board[i][j] = 2;
                }
            }
        }
        // 解码
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] % 2;
            }
        }
    }


    public void solveInplaceBit_II(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = 0;
                // 累加上下左右及四个角还有自身的值
                for (int y = Math.max(i - 1, 0); y <= Math.min(i + 1, m - 1); y++) {
                    for (int x = Math.max(j - 1, 0); x <= Math.min(j + 1, n - 1); x++) {
                        //Bug1:即只计算变化前的状态的值
                        // 累加bit1的值
                        lives += board[y][x] & 1;
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
        // 右移就是新的值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //Bug3:数据右移一位,取最新的状态
                board[i][j] >>>= 1;
            }
        }
    }

    public void solveWithTable_III(int rounds, int[][] board) {
        // 映射表
        int[] lookupTable = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0,
                0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1,
                0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0,
                0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1,
                1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0,
                0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int m = board.length, n = board[0].length;
        if (n == 0) return;
        int[][] buffer = new int[m][n];
        for (int i = 0; i < m; i++) {
            // 每一行开始时，先计算初始的环境值（左边两列）
            int environment = (i - 1 >= 0 && board[i - 1][0] == 1 ? 4 : 0) +
                    (board[i][0] == 1 ? 2 : 0) +
                    (i + 1 < m && board[i + 1][0] == 1 ? 1 : 0);
            // 对该行的每一列，通过加入右边新的一列，来计算该点的环境值
            for (int j = 0; j < n; j++) {
                // 将之前的环境值模64再乘以8，然后加上右边新的三列
                environment = (environment % 64) * 8 +
                        (i - 1 >= 0 && j + 1 < n && board[i - 1][j + 1] == 1 ? 4 : 0) +
                        (j + 1 < n && board[i][j + 1] == 1 ? 2 : 0) +
                        (i + 1 < m && j + 1 < n && board[i + 1][j + 1] == 1 ? 1 : 0);
                buffer[i][j] = lookupTable[environment];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = buffer[i][j];
            }
        }
    }

}