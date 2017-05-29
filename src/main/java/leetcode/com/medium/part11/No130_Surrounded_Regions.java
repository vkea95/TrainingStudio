package leetcode.com.medium.part11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jason on 2016/3/20.
 * Location：
 * https://leetcode.com/problems/surrounded-regions/
 * *****************************************************
 * Description:
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * ******************************************************
 * Solution:
 * 总的来说都可以归结为一个连通性的问题，用BFS；
 * 1.很明显在最外面一圈的O挨着的O肯定最后不是被包围的，与这个O也相连的也不能被包围，即这些O在最后的矩阵里面还是O；
 * 2.我们从最外面一圈的O出发，用BFS，利用队列帮助进行扩展，将所有它能联通到的O都标记，
 * 3.随后我们再扫描一遍矩阵，被标记的改回O,没有被标记的O都是可以被包围的，所以要改成X；
 * ******************************************************
 * Hints:
 * 题目出的很巧妙，照着答案敲了一遍都没理解为什么要这么干，
 * 干想了20分钟，才想明白，该怎么做
 * 它成功地将BSF的算法隐藏在了看不到的地方，考的真是发现问题，分析问题，解决问题的能力啊！！！真是老奸巨猾啊。。。
 * 其实问题的关键根本就不在于“X"，而是只要和”O"相连接，就不是答案，除此以外的O都要翻成X！！！
 * 最外层的O肯定不能翻成X
 */
public class No130_Surrounded_Regions {

    private static Queue<Integer> queue = null;
    private static char[][] boardObj;
    private static int rows = 0;
    private static int cols = 0;

    public static void main(String[] args) {
        No130_Surrounded_Regions obj = new No130_Surrounded_Regions();
        char[][] boards = new char[][]{"XXX".toCharArray(), "XOX".toCharArray(), "XXX".toCharArray()};
        obj.solve(boards);
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        queue = new LinkedList<>();
        boardObj = board;
        rows = boardObj.length;
        cols = boardObj[0].length;

        for (int i = 0; i < rows; i++) {
            enqueue(i, 0);
            enqueue(i, cols - 1);
        }

        for (int j = 1; j < cols - 1; j++) {
            enqueue(0, j);
            enqueue(rows - 1, j);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / cols;
            int y = cur % cols;
            if (boardObj[x][y] == 'O') {
                boardObj[x][y] = 'D';
            }

            enqueue(x - 1, y);
            enqueue(x + 1, y);
            enqueue(x, y - 1);
            enqueue(x, y + 1);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (boardObj[i][j] == 'D') boardObj[i][j] = 'O';
                else if (boardObj[i][j] == 'O') boardObj[i][j] = 'X';
            }
        }
        queue = null;
        boardObj = null;
        rows = 0;
        cols = 0;

    }

    //将值为0的元素下标放入队列中
    public static void enqueue(int x, int y) {
        if (x >= 0 && x < rows && y >= 0 && y < cols && boardObj[x][y] == 'O')
            queue.offer(x * cols + y);

    }


}
