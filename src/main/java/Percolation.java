import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final int sourceIndex;
    private final boolean[] opened;
    private int open = 0;
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
        if (!isOpen(row, col)) {
            open++;
        }
        var index = index(row, col);
        opened[index] = true;
        for (int neighbourIndex : openNeighbours(row, col)) {
            if (neighbourIndex < 0) continue;
            unionFind.union(index, neighbourIndex);
        }
        if (row == 1) {
            unionFind.union(index, sourceIndex);
        }
        if (row == n && unionFind.find(sourceIndex) == unionFind.find(index)) {
            percolates = true;
        }
    }

    public boolean isOpen(int row, int col) {
        return opened[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        return unionFind.find(sourceIndex) == unionFind.find(index(row, col));
    }

    public int numberOfOpenSites() {
        return open;
    }

    public boolean percolates() {
        return percolates;
    }

    private int[] openNeighbours(int row, int col) {
        int[] result = new int[4];
        result[0] = col - 1 > 0 && opened[index(row, col - 1)] ? index(row, col - 1) : -1;
        result[1] = col + 1 <= n && opened[index(row, col + 1)] ? index(row, col + 1) : -1;
        result[2] = row - 1 > 0 && opened[index(row - 1, col)] ? index(row - 1, col) : -1;
        result[3] = row + 1 <= n && opened[index(row + 1, col)] ? index(row + 1, col) : -1;
        return result;
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

    public static void main(String[] args) {
        Percolation percolation = new Percolation(10);
        percolation.open(1, 3);

        System.out.println("aaa");
    }
}
