package cousera.com.alg4.week1.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by JianZhang on 1/9/18.
 */
public class PercolationStats {
    //    private Percolation perc = null;
    private int trials;
    private double[] samples;
    final private double CONFIDENE = 1.96;
    private double meanVal = 0.0d;
    private double stddevVal = 0.0d;

    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.trials = trials;
        samples = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
//            Stopwatch stopwatch = new Stopwatch();
            int openSitesNum = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                if (!perc.isOpen(row, col)) {
                    perc.open(row, col);
                    openSitesNum++;
//                    elapsedTime = stopwatch.elapsedTime();
                }
            }
            samples[i] = (double) openSitesNum / (n * n);

        }
        this.meanVal = StdStats.mean(samples);
        this.stddevVal = StdStats.stddev(samples);

    }    // perform trials independent experiments on an n-by-n grid

    public double mean() {
        return this.meanVal;
    }                          // sample meanVal of percolation threshold

    public double stddev() {
        return this.stddevVal;
    }                       // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return this.meanVal - (CONFIDENE * this.stddevVal) / Math.sqrt(trials);
    }                  // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return this.meanVal + (CONFIDENE * this.stddevVal) / Math.sqrt(trials);
    }                     // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int n = 0;
        int trial = 0;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            trial = Integer.parseInt(args[0]);

        }    // test client (described below)

        PercolationStats percolationStats = new PercolationStats(n, trial);

        StdOut.println("meanVal                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");

    }
}

