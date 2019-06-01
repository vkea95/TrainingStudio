package jian.practice;

public class Test {
}

class SolutionX {
    private static final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] rst = new int[hits.length];
        int rows = grid.length, cols = grid[0].length;
        UnionFind uf = new UnionFind(rows * cols + 1);

        // Make all hits
        for (int i = 0; i < hits.length; i++) {
            // 1 turns to 0, 0 turns to -1.
            grid[hits[i][0]][hits[i][1]] -= 1;
        }

        // Build the board (After all hits)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // if the current cell is a brick, we can union it with its neighbors
                if (grid[i][j] == 1) {
                    int pos = i * cols + j;
                    // if it's in 0th row, it can stay in the board
                    // so we union it with the root (rows * cols)
                    if (i == 0) {
                        uf.union(pos, rows * cols);
                    }
                    // Here is similar to Making A Large Island
                    // We only need to union the left and up gird of current position
                    if (i > 0 && grid[i - 1][j] == 1) {
                        uf.union(pos, (i - 1) * cols + j);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        uf.union(pos, i * cols + j - 1);
                    }
                }
            }
        }

        // Reverse the time
        for (int i = hits.length - 1; i >= 0; i--) {
            int hitRow = hits[i][0], hitCol = hits[i][1];
            // The block used to be empty (there is no bricks)
            if (grid[hitRow][hitCol] == -1) {
                grid[hitRow][hitCol] = 0;
                continue;
            }

            // Get how many bricks in the board currently
            int preRoof = uf.getRoof();
            for (int[] dir : DIRS) {
                int nextRow = hitRow + dir[0];
                int nextCol = hitCol + dir[1];
                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols
                        || grid[nextRow][nextCol] != 1) {
                    continue;
                }
                uf.union(hitRow * cols + hitCol, nextRow * cols + nextCol);
            }

            // If the hit position is in 0th row, it should be union to root (rows*cols)
            if (hitRow == 0) {
                uf.union(hitRow * cols + hitCol, rows * cols);
            }
            // Add the hit brick
            grid[hitRow][hitCol] = 1;
            // Get the variation
            rst[i] = Math.max(0, uf.getRoof() - preRoof - 1);
        }

        return rst;
    }

    class UnionFind {
        int[] parent, rank;

        UnionFind(int N) {
            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
            rank[N - 1] = 0;
        }

        public int compressedFind(int index) {
            if (index != parent[index]) {
                parent[index] = compressedFind(parent[index]);
            }
            return parent[index];
        }

        public void union(int a, int b) {
            int aFather = compressedFind(a);
            int bFather = compressedFind(b);
            if (aFather == bFather) {
                return;
            } else if (aFather > bFather) {
                // make bFather to be the bigger one
                int temp = aFather;
                aFather = bFather;
                bFather = temp;
            }

            // Union smaller to bigger one, unless bFather is the root
            if (bFather == rank.length - 1) {
                parent[aFather] = bFather;
                rank[bFather] += rank[aFather];
            } else {
                parent[bFather] = aFather;
                rank[aFather] += rank[bFather];
            }
        }

        // Return how many bricks can stay in the board
        public int getRoof() {
            return rank[rank.length - 1];
        }
    }
}