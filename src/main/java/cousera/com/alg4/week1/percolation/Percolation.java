package cousera.com.alg4.week1.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by JianZhang on 1/4/18.
 */
public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;


    private boolean[][] isOpenArray = null;
    private int openCount = 0;
    private int n = 0;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        isOpenArray = new boolean[n][n];
        this.n = n;
    }

    private boolean isValid(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) return false;
        return true;
    }

    public void open(int row, int col) {
        if (!isValid(row, col)) throw new IllegalArgumentException();
        if (isOpen(row, col)) return;
        isOpenArray[row - 1][col - 1] = true;

        openCount++;

        if (isValid(row - 1, col) && isOpen(row - 1, col)) {
            weightedQuickUnionUF.union(getIndex(row - 1, col), getIndex(row, col));
        }
        if (isValid(row + 1, col) && isOpen(row + 1, col)) {
            weightedQuickUnionUF.union(getIndex(row + 1, col), getIndex(row, col));
        }
        if (isValid(row, col - 1) && isOpen(row, col - 1)) {
            weightedQuickUnionUF.union(getIndex(row, col - 1), getIndex(row, col));
        }
        if (isValid(row, col + 1) && isOpen(row, col + 1)) {
            weightedQuickUnionUF.union(getIndex(row, col + 1), getIndex(row, col));
        }
        if (row == 1) weightedQuickUnionUF.union(0, getIndex(row, col));

        if (weightedQuickUnionUF.connected(0, getIndex(row, col))) {
            if (row == n)
                weightedQuickUnionUF.union(0, n * n + 1);
            else if (!weightedQuickUnionUF.connected(0, n * n + 1))
                for (int i = 1; i <= n; i++) {
                    // bug: reduce the calls fto connected
                    if (isOpen(n, i) && weightedQuickUnionUF.connected(getIndex(row, col), getIndex(n, i))) {
                        weightedQuickUnionUF.union(0, n * n + 1);
                        break;
                    }
                }
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    public boolean isOpen(int row, int col) {
        if (!isValid(row, col))
            throw new IllegalArgumentException();
        return isOpenArray[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (!isValid(row, col))
            throw new IllegalArgumentException();
        return weightedQuickUnionUF.connected(0, getIndex(row, col));
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, n * n + 1);
    }

    public static void main(String[] args) {
    }
}