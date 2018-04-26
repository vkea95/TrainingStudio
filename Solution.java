class Solution {

    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] pacificVisited = new boolean[n][m];
        boolean[][] atlanticVisited = new boolean[n][m];
        
        Deque<int[]> pacificQueue = new ArrayDeque<>();
        Deque<int[]> atlanticQueue = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            pacificVisited[i][0] = true;
            atlanticVisited[i][m - 1] = true;
            pacificQueue.addLast(new int[]{i, 0});
            atlanticQueue.addLast(new int[]{i, m - 1});
        }
        
        for (int j = 0; j < m; j++) {
            pacificVisited[0][j] = true;
            atlanticVisited[n - 1][j] = true;
            pacificQueue.addLast(new int[]{0, j});
            atlanticQueue.addLast(new int[]{n - 1, j});
        }
        
        BSF(pacificQueue, matrix, pacificVisited);
        BSF(atlanticQueue, matrix, atlanticVisited);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    result.add(new int[] {i, j});
                }
            }
        }
        
        return result;
    }
    
    private void BSF(Deque<int[]>  deque,int[][] matrix, boolean[][] visited) {
        int[] steps = new int[]{1, 0, -1, 0, 1};
        int n = matrix.length;
        int m = matrix[0].length;
        
        while(!deque.isEmpty()) {
            int[] curt = deque.removeFirst();
            
            for (int i = 0; i < steps.length - 1; i++) {
                int x = curt[0] + steps[i];
                int y = curt[1] + steps[i + 1];
                if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y] && matrix[x][y] >= matrix[curt[0]][curt[1]]) {
                    visited[x][y] = true;
                    deque.addLast(new int[] {x, y});
                }
            }
            
        }
    }
}