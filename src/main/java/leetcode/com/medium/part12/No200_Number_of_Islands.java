package leetcode.com.medium.part12;

/**
 * Created by jason on 2016/4/3.
 * Location:
 * https://leetcode.com/problems/number-of-islands/
 * **********************************************
 * Description:
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 * Example 1:
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * Example 2:
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 * **********************************************
 * Solution:
 * BFS,将相连的island删掉，然后再循环整个矩阵
 */
public class No200_Number_of_Islands {
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, 1, -1, 0};

    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        if (grid[0].length == 0) return 0;

        int n = grid.length;
        int m = grid[0].length;

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    removeIsland(grid, i, j, n, m);
                    count++;
                }
            }
        }
        return count;
    }

    public void removeIsland(char[][] grid, int x, int y, int n, int m) {
        grid[x][y] = '0';
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) {
                if (grid[nextX][nextY] == '1') {
                    removeIsland(grid, nextX, nextY, n, m);
                }
            }

        }
    }
}
