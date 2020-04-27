import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trials;
    private final double mean;
    private final double stddev;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.trials = trials;
        var x = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            var counter = 0;
            while (!percolation.percolates()) {
                var row = StdRandom.uniform(n);
                var col = StdRandom.uniform(n);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    counter++;
                }
            }
            x[i] = ((double) counter) / n * n;
        }
        mean = StdStats.mean(x);
        stddev = StdStats.stddev(x);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return mean - 1.96 * stddev / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean + 1.96 * stddev / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, t);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println("stddev                  = " + percolationStats.stddev());
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}
