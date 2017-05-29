package leetcode.com.pickup1.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by tclresearchamerica on 7/25/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/surrounded-regions/
 * ****************************************************
 * Description:
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
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
 * ****************************************************
 * Thought:
 * 1.根据这个example,感觉是要使用并查集的概念,
 * 2.如果要是通过参数将状态传给另一个相邻的O的方式,那么如果一堆挤在一起的o就会发生循环调用了,还要记住状态
 * 3.感觉这里面会用到递归调用啊,不然怎么处理连接在一起的O们呢,
 * 4.但是如何选用适合的数据结构和算法呢?
 * ****************************************************
 * Internet Answer:
 * 时间 O(MN) 空间 O(MN)
 * 思路
 * 从矩阵的四条边上的点作为起点，搜索连续的一块区域上值为O的点并赋为一个临时变量。这里我们用BFS或DFS进行搜索。
 * 对四条边上所有点都进行过这个步骤后，矩阵内剩余的O就是被包围的O。此时再对所有点遍历一遍，将临时变量赋回O，而剩余的O则赋为X。
 * 注意
 * 用队列实现BFS时，赋临时变量B时要在将周边点加入队列时就赋值，减少while循环的次数，否则Leetcode会超时
 *
 * 1.看了第一遍的解答,线索隐藏在处在四个边框的O是不可以被flip的,那么和它们连接的也就不可以flip,依次作为解题方案---Smart!
 * 2.队列在存储数组坐标的时候,使用的方法也和巧妙,这个要记下来,就是用【x】【y】->x*col+y=sum,还原的时候x=sum/cols,y=sum%cols
 * ****************************************************
 * Time: 34 mins
 * Beat: 23%
 * Bug:-
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No130_Surrounded_Regions {
    public void solve(char[][] board) {

        if (board == null || board.length == 0 || board[0].length == 0) return;

        Queue<Integer> queue = new LinkedList<>();
        int row = board.length, cols = board[0].length;
        for (int i = 0; i < row; i++) {
            enqueue(queue, i, 0, board);
            enqueue(queue, i, cols - 1, board);
        }
        for (int i = 0; i < cols; i++) {
            enqueue(queue, 0, i, board);
            enqueue(queue, row - 1, i, board);
        }

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            int x = idx / cols;
            int y = idx % cols;
            if (board[x][y] == 'O') {
                board[x][y] = 'D';
            }

            enqueue(queue, x + 1, y, board);
            enqueue(queue, x - 1, y, board);
            enqueue(queue, x, y + 1, board);
            enqueue(queue, x, y - 1, board);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cols; j++) {

                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'D') {
                    board[i][j] = 'O';
                }
            }
        }

    }

    private void enqueue(Queue<Integer> queue, int x, int y, char[][] board) {
        int cols = board[0].length;
        if (x >= 0 && x < board.length && y >= 0 && y < cols && board[x][y] == 'O') {
            queue.add(x * cols + y);
        }
    }
}
