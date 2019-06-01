package ctc.com.viii.ch07.p07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Edge {
    private Shape shape;
    private String code;
    private Piece parentPiece;

    public Edge(Shape shape, String code) {
        this.shape = shape;
        this.code = code;
    }

    public Edge _createMatchingEdge() {
        if (shape == Shape.FLAT) return null;
        return new Edge(shape.getOpposite(), getCode());
    }

    public void setParentPiece(Piece parentPiece) {
        this.parentPiece = parentPiece;
    }

    public Shape getShape() {
        return shape;
    }

    public String getCode() {
        return code;
    }

    public Piece getParentPiece() {
        return parentPiece;
    }

    public boolean fitsWith(Edge edge) {
        return edge.getCode().equals(getCode());
    }

    public String toString() {
        return code;
    }
}

class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int[][] c = initDs(target, startFuel, stations);
        bfs(0, c, 0, c[0][1]);
        return c[c.length - 2][2] == Integer.MAX_VALUE ? -1 : c[c.length - 2][2] - 1;
    }

    private void bfs(int i, int[][] c, int cnt, int fuel) {
        if (i >= c.length - 2) {
            System.out.println("return: " + i);
            c[c.length - 2][2] = Math.min(c[c.length - 2][2], cnt);
            return;
        }
        int maxIndex = binarySearch(c, i + 1, c.length - 1, c[i][0] + fuel);
        if (maxIndex < 0) {
            maxIndex = -maxIndex - 1;
            maxIndex--;
        }
        System.out.println("maxIndex: " + maxIndex);
        Map<Integer, Integer> map = new HashMap<>();
        for (int j = i + 1; j <= maxIndex; j++) {
            System.out.println("j: " + j);
            if (c[j][3] < fuel + (c[i][0] - c[j][0]) + c[j][1]) {
                c[j][2] = Math.min(c[j][2], cnt + 1);
                c[j][3] = fuel + (c[i][0] - c[j][0]) + c[j][1];// 跟新当前station可以有的max gas amount
                map.put(j, c[j][3]);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            bfs(entry.getKey(), c, cnt + 1, entry.getValue());
        }

    }

    private int binarySearch(int[][] c, int s, int e, int target) {
        while (s <= e) {
            int m = (s + e) >>> 1;
            if (c[m][0] == target) {
                System.out.println("m: " + m);
                return m;
            } else if (c[m][0] < target) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        return -s - 1;
    }

    private int[][] initDs(int target, int startFuel, int[][] stations) {
        int n = stations.length + 2;
        int[][] c = new int[n + 1][4];
        c[0][0] = 0;
        c[0][1] = startFuel;
        c[0][2] = 0;
        for (int i = 0; i < stations.length; i++) {
            c[i + 1][0] = stations[i][0];
            c[i + 1][1] = stations[i][1];
            c[i + 1][2] = Integer.MAX_VALUE;
            c[i + 1][3] = -1;
        }
        c[n - 1][0] = target;
        c[n - 1][1] = 0;
        c[n - 1][2] = Integer.MAX_VALUE;
        c[n - 1][3] = -1;//bug
        c[n][0] = Integer.MAX_VALUE;// 增加一个缓冲地带防止binarySearch结果越界
        c[n][1] = 0;
        c[n][2] = Integer.MAX_VALUE;
        c[n][3] = -1;//bug
        return c;
    }
}