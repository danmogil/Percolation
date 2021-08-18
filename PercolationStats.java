import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  private final int trialRounds;
  private final double[] percolateResults; // ratio of open sites to total cells per trial run.

  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) throw new IllegalArgumentException(
      "both args must be > 0"
    );

    trialRounds = trials;
    percolateResults = new double[trials];

    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);

      while (!percolation.percolates()) {
        int row = StdRandom.uniform(1, n + 1);
        int col = StdRandom.uniform(1, n + 1);
        if (!percolation.isOpen(row, col)) percolation.open(row, col);
      }

      percolateResults[i] = (double) percolation.numberOfOpenSites() / (n * n);
    }
  }

  public double mean() {
    return StdStats.mean(percolateResults);
  }

  public double stddev() {
    return StdStats.stddev(percolateResults);
  }

  // low end-point of 95% confidence interval
  private double confidenceLo() {
    return mean() - ((1.96 * stddev()) / Math.sqrt(trialRounds));
  }

  // high end-point of 95% confidence interval
  private double confidenceHi() {
    return mean() + ((1.96 * stddev()) / Math.sqrt(trialRounds));
  }

  public String confidenceInterval() {
    return "[" + confidenceLo() + ", " + confidenceHi() + "]";
  }

  public static void main(String[] args) {
    PercolationStats a = new PercolationStats(200, 100);
    System.out.println(a.confidenceInterval());
  }
}
