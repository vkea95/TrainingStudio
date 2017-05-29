package alg4.com.ch0101;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by JianZhang on 12/5/16.
 * Copied from
 * https://github.com/aistrate/AlgorithmsSedgewick/blob/master/1-Fundamentals/1-1-BasicProgModel/Ex_1_1_31.java
 */
public class P_31 {
    public static void drawRandConn(int n, double p) {
        StdDraw.setCanvasSize(1024, 1024);
        StdDraw.setScale(-1.0, 1.0);
        //设置笔的宽度
        StdDraw.setPenRadius(0.015);

        double[][] points = new double[n][2];
        for (int i = 0; i < n; i++) {
            //按照360度,切分圆形的弧,并找到坐标点
            points[i][0] = Math.cos(2 * Math.PI * 1 * i / n);
            points[i][1] = Math.sin(2 * Math.PI * 1 * i / n);
            StdDraw.point(points[i][0], points[i][1]);
        }

        //重新设置笔的宽度
        StdDraw.setPenRadius();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (StdRandom.bernoulli(p))
                    StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]);
            }
        }
        return;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        p = Math.max(0, Math.min(1, p));

        drawRandConn(N, p);
    }

}
