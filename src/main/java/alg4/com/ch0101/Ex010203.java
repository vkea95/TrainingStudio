package alg4.com.ch0101;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by JianZhang on 5/20/17.
 * 不会画图,不能再图片上显示出图形,等下再试试看啦
 */
public class Ex010203 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);
        Interval2D[] interval2D = new Interval2D[n];
        setupDraw(max, max);
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(10, 10);
        for (int i = 0; i < n; i++) {
            double min1 = StdRandom.uniform(min, 100);
            double max1 = StdRandom.uniform(min, 100);
            //确保
            System.out.println("x1:" + min1 + " y1: " + max1);
            Interval1D interval1D_1 = min1 < max1 ? new Interval1D(min1, max1) : new Interval1D(max1, min1);
            double min2 = StdRandom.uniform(min, max);
            double max2 = StdRandom.uniform(min, max);
            Interval1D interval1D_2 = min2 < max2 ? new Interval1D(min2, max2) : new Interval1D(max2, min2);
            System.out.println("x2:" + min2 + " y2: " + max2);
            interval2D[i] = new Interval2D(interval1D_1, interval1D_2);
            StdDraw.setPenColor(StdDraw.RED);

            interval2D[i].draw();

        }
//        StdDraw stdDraw
        System.out.print("Over");
    }

    private static void setupDraw(double xScale, double yScale) {
        StdDraw.setXscale(0, xScale);
        StdDraw.setXscale(0, yScale);
        StdDraw.setPenRadius(.005);
    }
}
