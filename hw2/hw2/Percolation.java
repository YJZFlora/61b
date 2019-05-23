package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int count;
    int[][] grid;
    WeightedQuickUnionUF uf;
    int VT;
    int VB;
    int length;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N * N + 3);
        count = 0;
        VT = N * N + 1;
        VB = N * N + 2;
        length = N;
        // union top row to VT, union top bottom to VB
        for (int i = 0; i < N; i++) {
            int n = to1dNumber(0, i);
            uf.union(n, VT);
            int m = to1dNumber(N - 1, i);
            uf.union(m, VB);
        }
    }

    private int to1dNumber(int row, int col) {
        return row * length + col;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row >= length || col >= length || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (grid[row][col] != 1) {
            grid[row][col] = 1;
            count = count + 1;
            checkandUnion(row, col);
        }
    }

    private void checkandUnion(int row, int col) {
        int target = to1dNumber(row, col);
        if (row != 0 && isOpen(row - 1, col)) {
            int up = target - length;
            uf.union(up, target);
        }
        if (row != length - 1 && isOpen(row + 1, col)) {
            int down = target + length;
            uf.union(down, target);
        }
        if (col != 0 && isOpen(row, col - 1)) {
            int right = target - 1;
            uf.union(right, target);
        }
        if (col != length - 1 && isOpen(row, col + 1)) {
            int left = target + 1;
            uf.union(left, target);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row >= length || col >= length || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row >= length || col >= length || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        int oned = to1dNumber(row, col);
        if (grid[row][col] == 1 && uf.connected(oned, VT)) {
            return true;
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(VT, VB);
    }


    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }

}
