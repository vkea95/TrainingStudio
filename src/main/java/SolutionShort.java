import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by JianZhang on 2/27/18.
 */
public class SolutionShort {

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null) {
            return -1;
        }
        int[][] maze2 = createMaze2(maze);
        int col = maze2[0].length;
        int[] newStart = getNewPos(start);
        int[] newDestination = getNewPos(destination);
        int startIndex = createIndex(newStart[0], newStart[1], col);
        int dstIndex = createIndex(newDestination[0], newDestination[1], col);
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();

        // copy from 787
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        queue.offer(new int[]{0, startIndex});


        while (!queue.isEmpty()) {
            int top[] = queue.remove();
            int distance = top[0];
            int startPos = top[1];

            if (startPos == dstIndex) {
                return distance;
            }

            System.out.println("start row: " + (startPos / col) + "  col: " + (startPos % col) + " distance: " + distance);
            // create the neighbour nodes
            createAdajacent(maze2, startPos, graph);

            Map<Integer, Integer> neighbours = graph.get(startPos);
            for (Integer neighbour : neighbours.keySet()) {
                queue.add(new int[]{distance + neighbours.get(neighbour), neighbour});
            }

        }

        return -1;
    }

    private int[] getNewPos(int[] pos) {
        int[] newPos = new int[2];
        newPos[0] = pos[0] + 1;
        newPos[1] = pos[1] + 1;

        return newPos;
    }

    private int[][] createMaze2(int[][] maze) {
        int row = maze.length;
        int col = maze[0].length;
        int[][] maze2 = new int[row + 2][col + 2];
        // up & down row
        for (int i = 0; i < col + 2; i++) {
            maze2[0][i] = 1;
            maze2[row + 1][i] = 1;
        }
        // left & right Col
        for (int i = 0; i < row + 2; i++) {
            maze2[i][0] = 1;
            maze2[i][col + 1] = 1;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                maze2[i + 1][j + 1] = maze[i][j];
            }
        }
        return maze2;
    }

    private int createIndex(int posRow, int posCol, int col) {
        return posRow * col + posCol;
    }

    private void findNeighbour(int[][] maze, int startRow, int startCol, int rowStep, int colStep, Map<Integer, Map<Integer, Integer>> graph) {
        System.out.println("findNeighbour: ");
        int row = maze.length;
        int col = maze[0].length;
        int startIndex = createIndex(startRow, startCol, col);
        int neighbourRow = startRow;
        int neighbourCol = startCol;

        while (neighbourCol > 0 && neighbourRow > 0 &&
                neighbourCol < col && neighbourRow < row &&
                maze[neighbourRow][neighbourCol] == 0) {
            neighbourCol += colStep;
            neighbourRow += rowStep;
        }
        if (Math.abs(neighbourCol - startCol) >= 2 || Math.abs(neighbourRow - startRow) >= 2) {
            int neighbourIndex = createIndex(neighbourRow - rowStep, neighbourCol - colStep, col);


            graph.put(startIndex, graph.getOrDefault(startIndex, new HashMap<>()));
            graph.put(neighbourIndex, graph.getOrDefault(neighbourIndex, new HashMap<>()));

            if (!graph.get(neighbourIndex).containsKey(startIndex)) {
                System.out.println(" startRow: " + startRow + " startCol " + startCol);
                System.out.println(" neighbourRow: " + (neighbourRow - rowStep) + " neighbourCol " + (neighbourCol - colStep));
                graph.get(startIndex).put(neighbourIndex, Math.abs(neighbourCol - startCol) + Math.abs(neighbourRow - startRow) - 1);

            }
        }
    }

    private boolean isValidVerticalNode(Map<Integer, Map<Integer, Integer>> graph, boolean chkRowFlg, int col, int posRow, int posCol) {

        System.out.print("isValidVerticalNode " + " posRow: " + posRow + " posCol " + posCol + " " + chkRowFlg);
        for (Integer startPos : graph.keySet()) {
            int startRow = startPos / col;
            int startCol = startPos % col;
            if (chkRowFlg && startRow == posRow) {
                for (Integer endPos : graph.get(startPos).keySet()) {
                    int endRow = endPos / col;
                    int endCol = endPos % col;

                    if (startRow == endRow) {
                        if (posCol <= Math.max(endCol, startCol) && posRow >= Math.min(endCol, startCol)) {
                            System.out.println("unValidVerticalNode " + " startRow: " + startRow + " startCol " + startCol + " endRow: " + endRow + " endCol " + endCol);

                            return false;
                        }

                    }
                }
            } else if (!chkRowFlg && startCol == posCol) {
                for (Integer endPos : graph.get(startPos).keySet()) {
                    int endRow = endPos / col;
                    int endCol = endPos % col;

                    if (endCol == startCol) {

                        if (posRow <= Math.max(startRow, endRow) && posRow >= Math.min(startRow, endRow)) {
                            System.out.println("unValidVerticalNode " + " startRow: " + startRow + " startCol " + startCol + " endRow: " + endRow + " endCol " + endCol);

                            return false;
                        }

                    }
                }

            }


        }
        System.out.println("valid ");

        return true;
    }

    private void createAdajacent(int[][] maze, int startIndex, Map<Integer, Map<Integer, Integer>> graph) {
        int col = maze[0].length;
        int startRow = startIndex / col;
        int startCol = startIndex % col;

        System.out.println("in creating adjacent ing startIndex " + startIndex + " startRow " + startRow + " startCol " + startCol);
        boolean chkRst = isValidVerticalNode(graph, true, maze[0].length, startRow, startCol);
        if (chkRst) {
            // move left
            findNeighbour(maze, startRow, startCol, 0, -1, graph);
            // move right
            findNeighbour(maze, startRow, startCol, 0, 1, graph);

        }
        chkRst = isValidVerticalNode(graph, false, maze[0].length, startRow, startCol);
        if (chkRst) {
            // move up
            findNeighbour(maze, startRow, startCol, -1, 0, graph);
            // move down
            findNeighbour(maze, startRow, startCol, 1, 0, graph);

        }

    }
//    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
//        if (maze == null) {
//            return -1;
//        }
//        int[][] maze2 = createMaze2(maze);
//        int col = maze2[0].length;
//        int[] newStart = getNewPos(start);
//        int[] newDestination = getNewPos(destination);
//        Map<Integer[], Map<Integer[], Integer>> graph = new HashMap<>();
//
//        // copy from 787
//        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
//        queue.offer(new int[]{0, newStart[0], newStart[1]});
//
//
//        while (!queue.isEmpty()) {
//            int top[] = queue.remove();
//            int distance = top[0];
//            int startRowPos = top[1];
//            int startColPos = top[2];
//            Integer[] startPos = new Integer[]{startRowPos, startColPos};
//            if (startRowPos == newDestination[0] && startColPos == newDestination[1]) {
//                return distance;
//            }
//
//            createAdajacent(maze2, startPos, graph);
//
//            Map<Integer[], Integer> neighbours = graph.get(startPos);
//            if (neighbours == null) {
//                continue;
//            }
//            for (Integer[] neighbour : neighbours.keySet()) {
//                queue.add(new int[]{distance + neighbours.get(neighbour), neighbour[0], neighbour[1]});
//            }
//
//        }
//
//        return -1;
//    }
//
//    private int[] getNewPos(int[] pos) {
//        int[] newPos = new int[2];
//        newPos[0] = pos[0] + 1;
//        newPos[1] = pos[1] + 1;
//
//        return newPos;
//    }
//
//    private int[][] createMaze2(int[][] maze) {
//        int row = maze.length;
//        int col = maze[0].length;
//        int[][] maze2 = new int[row + 2][col + 2];
//        // up & down row
//        for (int i = 0; i < col + 2; i++) {
//            maze2[0][i] = 1;
//            maze2[row + 1][i] = 1;
//        }
//        // left & right Col
//        for (int i = 0; i < row + 2; i++) {
//            maze2[i][0] = 1;
//            maze2[i][col + 1] = 1;
//        }
//
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                maze2[i + 1][j + 1] = maze[i][j];
//            }
//        }
//        return maze2;
//    }
//
//
//    private void findNeighbour(int[][] maze, Integer[] startPos, int rowStep, int colStep, Map<Integer[], Map<Integer[], Integer>> graph) {
//        int row = maze.length;
//        int col = maze[0].length;
//        int neighbourRow = startPos[0];
//        int neighbourCol = startPos[1];
//
//        while (neighbourCol > 0 && neighbourRow > 0 &&
//                neighbourCol < col && neighbourRow < row &&
//                maze[neighbourRow][neighbourCol] == 0) {
//            neighbourCol += colStep;
//            neighbourRow += rowStep;
//        }
//
//        if (Math.abs(neighbourCol - startPos[1]) >= 2 || Math.abs(neighbourRow - startPos[0]) >= 2) {
//
//            Integer[] neighbour = new Integer[]{neighbourRow - rowStep, neighbourCol - colStep};
//            graph.put(startPos, graph.getOrDefault(startPos, new HashMap<>()));
//            graph.put(neighbour, graph.getOrDefault(neighbour, new HashMap<>()));
//
//            if (!graph.get(neighbour).containsKey(startPos)) {
//
//                System.out.println("neighbour: " + neighbour[0] + ":" + neighbour[1] + " distance: " + (Math.abs(neighbourCol - startPos[1]) + Math.abs(neighbourRow - startPos[0]) - 1));
//                graph.get(startPos).put(neighbour, Math.abs(neighbourCol - startPos[1]) + Math.abs(neighbourRow - startPos[0]) - 1);
//
//            }
//        }
//    }
//
//    private boolean isOnEdge(Map<Integer[], Map<Integer[], Integer>> graph, boolean chkRowFlg, Integer[] pos) {
//
//        for (Integer[] startPos : graph.keySet()) {
//            for (Integer[] endPos : graph.get(startPos).keySet()) {
//                // at the same row?
//                if (chkRowFlg && endPos[0] == startPos[0] && pos[0] == startPos[0]) {
//                    if (pos[1] <= Math.max(startPos[1], endPos[1]) && pos[1] >= Math.min(startPos[1], endPos[1])) {
//                        return true;
//                    }
//
//                } else if (!chkRowFlg && endPos[1] == startPos[1] && pos[1] == startPos[1]) {
//                    if (pos[0] <= Math.max(startPos[0], endPos[0]) && pos[1] >= Math.min(startPos[0], endPos[0])) {
//                        return true;
//                    }
//
//                }
//            }
//
//        }
//        return false;
//    }
//
//
//    private void createAdajacent(int[][] maze, Integer[] startPos, Map<Integer[], Map<Integer[], Integer>> graph) {
//        System.out.println("in creating adjacent ing startIndex " + startPos + " startRow " + startPos[0] + " startCol " + startPos[1]);
//        boolean chkRst = isOnEdge(graph, true, startPos);
//        if (!chkRst) {
//            System.out.println("move row");
//            // move left
//            findNeighbour(maze, startPos, 0, -1, graph);
//            // move right
//            findNeighbour(maze, startPos, 0, 1, graph);
//
//
//        }
//        chkRst = isOnEdge(graph, false, startPos);
//        if (!chkRst) {
//            System.out.println("move col");
//            // move up
//            findNeighbour(maze, startPos, -1, 0, graph);
//            // move down
//            findNeighbour(maze, startPos, 1, 0, graph);
//        }
//
//    }
}

