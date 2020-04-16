import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final int sourceIndex;
    private final boolean[] opened;
    private final WeightedQuickUnionUF unionFind;
    private boolean percolates = false;

    public Percolation(int n) {
        verify(n >= 0, "n cannot be lower than 1");
        this.n = n;
        this.sourceIndex = n * n;
        this.opened = new boolean[n * n];
        this.unionFind = new WeightedQuickUnionUF(n * n + 1);
    }

    public void open(int row, int col) {
        var index = index(row, col);
        opened[index] = true;
        if (row == 1) {
            unionFind.union(index, sourceIndex);
        }
        if (row == n && unionFind.find(sourceIndex) == unionFind.find(index)) {
            percolates = true;
        }

        unionFind.union(0, index(row, col));
    }

    public boolean isOpen(int row, int col) {
        return opened[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        return unionFind.find(sourceIndex) == unionFind.find(index(row, col));
    }

    public int numberOfOpenSites() {
        return n * n - unionFind.count();
    }

    public boolean percolates() {
        return percolates;
    }

    private int index(int row, int col) {
        verifyRange(row, col);
        return n * (row - 1) + col - 1;
    }

    private void verifyRange(int row, int col) {
        verify(row > 0 && row <= n, "Invalid row number");
        verify(col > 0 && col <= n, "Invalid column number");
    }

    private void verify(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }
}
