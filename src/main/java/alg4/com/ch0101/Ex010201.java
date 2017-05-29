package alg4.com.ch0101;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by JianZhang on 5/20/17.
 */
public class Ex010201 {

    public static void main(String[] args) {
        int maxRange=10000;
        int n = StdIn.readInt();
        if (n <= 1) return;
        double minDistance = Double.MAX_VALUE;
        Point2D[] point2Ds = new Point2D[n];
        //create the points
        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(-maxRange, maxRange);
            int y = StdRandom.uniform(-maxRange, maxRange);
            point2Ds[i] = new Point2D(x, y);
            System.out.println("Point2D("+x+", " +y+")");

        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double currentDist = point2Ds[i].distanceTo(point2Ds[j]);
                minDistance = minDistance < currentDist ? minDistance : currentDist;
            }
        }
        System.out.println("The minial distance is " + minDistance);
        //count all the distance

    }
}
