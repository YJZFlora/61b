package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    double[] xt;
    double mean;
    double std;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
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
        double sumofxt = 0.0;
        for (int i = 0; i < xt.length; i++) {
            sumofxt = sumofxt + xt[i];
        }
        mean = sumofxt / xt.length;
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double sum = 0.0;
        for (int i = 0; i < xt.length; i++) {
            sum = sum + Math.pow((xt[i] - mean), 2);
        }
        std = Math.sqrt(sum / (double) (xt.length - 1));
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
