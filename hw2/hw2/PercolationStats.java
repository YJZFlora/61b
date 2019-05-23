package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] xt;
    private double mean;
    private double std;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        xt = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation per = pf.make(N);
            xt[i] = independentE(per, N);
        }
    }

    // return the percolation threshold
    private double independentE(Percolation per, int N) {
        while (!per.percolates()) {
            per.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
        }
        int opentime = per.numberOfOpenSites();
        return (double) opentime / (double) (N * N);
    }

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(xt);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        std = StdStats.stddev(xt);
        return std;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean - (1.96 * std) / Math.sqrt((double) xt.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean + (1.96 * std) / Math.sqrt((double) xt.length);
    }

}
