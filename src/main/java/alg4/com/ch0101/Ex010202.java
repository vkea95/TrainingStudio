package alg4.com.ch0101;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdIn;

/**
 * Created by JianZhang on 5/20/17.
 */
public class Ex010202 {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        if (n <= 1) return;
        double minDistance = Double.MAX_VALUE;
        Interval1D[] interval1Ds = new Interval1D[n];
        for (int i = 0; i < n; i++) {
            double min = 0, max = 0;
            if (!StdIn.isEmpty())
                min = StdIn.readDouble();
            if (!StdIn.isEmpty())
                max = StdIn.readDouble();
            interval1Ds[i] = new Interval1D(min, max);

        }
        for (int i =0 ;i<n;i++){
            for (int j =i+1;j<n;j++){
                if (interval1Ds[i].intersects(interval1Ds[j])){
                    System.out.println("Intersect: " + interval1Ds[i].toString() +" and "+interval1Ds[j]);
                }
            }
        }
    }

}
