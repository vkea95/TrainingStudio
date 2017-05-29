package alg4.com.ch0101;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by JianZhang on 12/10/16.
 * Copied from
 * https://github.com/aistrate/AlgorithmsSedgewick/blob/master/1-Fundamentals/1-1-BasicProgModel/Ex_1_1_32.java
 */
public class P_32 {
    public static void histogram(double[] values, int n, double l, double r) {
        int[] counts = new int[n];

        for (int i = 0; i < values.length; i++) {
            int k = getInterval(values[i], n, l, r);
            if (k >= 0)
                counts[k]++;
        }

        int maxCount = StdStats.max(counts);

        StdDraw.setCanvasSize(1024, 512);
        StdDraw.setXscale(l, r);
        StdDraw.setYscale(0, maxCount);

        double w = (r - l) / n;

        for (int i = 0; i < n; i++) {
            double x = l + (i + 0.5) * w,
                    y = counts[i] / 2.0,
                    rw = 0.5 * w,
                    rh = counts[i] / 2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }

    private static int getInterval(double v, int n, double l, double r) {
        if (v < l || v >= r)
            return -1;
        else
            return (int) (n * (v - l) / (r - l));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double l = Double.parseDouble(args[1]),
                r = Double.parseDouble(args[2]);

//        double[] values = StdIn.readAllDoubles();
        double[] values = new double[]{1.2, 2.3, 5.6, 0.1, 1.11111};
        histogram(values, n, l, r);
        System.out.print("over");
    }
}
