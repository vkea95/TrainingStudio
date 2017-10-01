package leetcode.com.tag.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by JianZhang on 9/30/17.
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded
 * by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
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
 * Solutions:
 * 1. BFS: 全循环遍历数组,发现一个0,就开始BFS,同时开始计数+1,进去的时候,将里面的0设置2,就可以好了
 * 2. UnionFind
 */
public class No200_Number_of_Islands {

    public static void main(String[] args) {

        No200_Number_of_Islands obj = new No200_Number_of_Islands();
        System.out.println(obj.numIslands(grid));
        System.out.println(obj.numIslands_2(grid));
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[0].length; j++) {
//                System.out.print(grid[i][j] + ", ");
//            }
//            System.out.println();
//        }

    }

    //    this answer is wrong, but I don't know why
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            System.out.println("Row: " + i + " count: " + count);
            for (int j = 0; j < grid[0].length; j++) {
                if (i == grid.length - 2) {
//                    for (int k = 0; k < grid[0].length; k++) {
//                        System.out.print(grid[i][k] + ", ");
//                    }
//                    System.out.println();
                }
                if (grid[i][j] == '1') {
                    //bfs

                    helper(grid, i, j);
                    count++;
                }
            }
        }


        recovery(grid);
        return count;
    }

    private void helper(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * cols + j);
        while (!queue.isEmpty()) {
            int index = queue.poll();

            if (index < 0 || index >= cols * rows) continue;
            int x = index / cols;
            int y = index % cols;
            if (grid[x][y] != '1') continue;
            grid[x][y] = '2';//visited

            queue.offer(index + 1);
            queue.offer(index - 1);
            queue.offer(index + cols);
            queue.offer(index - cols);

        }
    }

    //Credit:http://www.cnblogs.com/EdwardLiu/p/4416153.html
    public int numIslands_2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            System.out.println("Row: " + i + " count: " + count);
            for (int j = 0; j < grid[0].length; j++) {
                if (i == grid.length - 2) {
//                    for (int k = 0; k < grid[0].length; k++) {
//                        System.out.print(grid[i][k] + ", ");
//                    }
//                    System.out.println();
                }
                if (grid[i][j] == '1') {
                    count++;
                    floodFill(grid, i, j);
                }
            }
        }
        recovery(grid);
        return count;
    }

    public void floodFill(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if (grid[i][j] != '1') return; //either 0(water) or 2(visited)
        grid[i][j] = '2';
        floodFill(grid, i - 1, j);
        floodFill(grid, i + 1, j);
        floodFill(grid, i, j - 1);
        floodFill(grid, i, j + 1);
    }

    private void recovery(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '0') grid[i][j] = '1';
            }
        }
    }

    public static char[][] grid = new char[][]{
            {'1', '0', '0', '1', '1', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '1', '1', '0', '0', '1', '0', '0', '0', '1', '0', '1', '0', '1', '0', '0', '1', '0'},
            {'0', '0', '0', '1', '1', '1', '1', '0', '1', '0', '1', '1', '0', '0', '0', '0', '1', '0', '1', '0'},
            {'0', '0', '0', '1', '1', '0', '0', '1', '0', '0', '0', '1', '1', '1', '0', '0', '1', '0', '0', '1'},
            {'0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
            {'1', '0', '0', '0', '0', '1', '0', '1', '0', '1', '1', '0', '0', '0', '0', '0', '0', '1', '0', '1'},
            {'0', '0', '0', '1', '0', '0', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1', '0', '1'},
            {'0', '0', '0', '1', '0', '1', '0', '0', '1', '1', '0', '1', '0', '1', '1', '0', '1', '1', '1', '0'},
            {'0', '0', '0', '0', '1', '0', '0', '1', '1', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1'},
            {'0', '0', '1', '0', '0', '1', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0'},
            {'1', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '1', '0', '1', '0', '1', '0'},
            {'0', '1', '0', '0', '0', '1', '0', '1', '0', '1', '1', '0', '1', '1', '1', '0', '1', '1', '0', '0'},
            {'1', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1'},
            {'0', '1', '0', '0', '1', '1', '1', '0', '0', '0', '1', '1', '1', '1', '1', '0', '1', '0', '0', '0'},
            {'0', '0', '1', '1', '1', '0', '0', '0', '1', '1', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0'},
            {'1', '0', '0', '1', '0', '1', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', '0', '1', '1'},
            {'1', '0', '1', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '1', '0', '1', '0', '0', '0', '0'},//<---
//            {'0', '1', '1', '0', '0', '0', '1', '1', '1', '0', '1', '0', '1', '0', '1', '1', '1', '1', '0', '0'},
//            {'0', '1', '0', '0', '0', '0', '1', '1', '0', '0', '1', '0', '1', '0', '0', '1', '0', '0', '1', '1'},
//            {'0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '0', '1', '0', '0', '0', '1', '1', '0', '0', '0'}

    };
}
