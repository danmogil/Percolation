public class Percolation {
  private final int virtualTopNode;
  private final int virtualBottomNode;
  private final boolean[][] nodeIsOpen;
  private final int matrixSize; //matrixSize ^ 2 === number of cells
  private int openSites;
  private final QuickUnionWeighted uf;

  public Percolation(int n) {
    if (n <= 0) throw new IllegalArgumentException("arg must be > 0");

    matrixSize = n;
    virtualTopNode = n * n;
    virtualBottomNode = (n * n) + 1;
    nodeIsOpen = new boolean[n][n];
    uf = new QuickUnionWeighted((n * n) + 2); //includes 2 virtual nodes
    openSites = 0;
  }

  private void validateInputs(int row, int col) {
    if (row <= 0 || row > matrixSize) throw new IllegalArgumentException(
      "row input must be > 0 && <= " + matrixSize
    );
    if (col <= 0 || col > matrixSize) throw new IllegalArgumentException(
      "column input must be > 0 && <= " + matrixSize
    );
  }

  //convert from row/column pair to a single index value
  private int getQuickUnionIndex(int row, int col) {
    return ((row - 1) * matrixSize) + (col - 1);
  }

  public void open(int row, int col) {
    validateInputs(row, col);
    if (isOpen(row, col)) return;
    nodeIsOpen[row - 1][col - 1] = true;
    openSites++;

    //create unions between connecting open nodes, if any exist.

    //top row : virtualTopNode
    if (row == 1) uf.union(virtualTopNode, getQuickUnionIndex(row, col));

    //bottom row : virtualBottomNode
    if (row == matrixSize) uf.union(virtualBottomNode, getQuickUnionIndex(row, col));

    //check up
    if (row > 1 && isOpen(row - 1, col)) uf.union(
      getQuickUnionIndex(row, col),
      getQuickUnionIndex(row - 1, col)
    );

    //check down
    if (row < matrixSize && isOpen(row + 1, col)) uf.union(
      getQuickUnionIndex(row, col),
      getQuickUnionIndex(row + 1, col)
    );

    //check left
    if (col > 1 && isOpen(row, col - 1)) uf.union(
      getQuickUnionIndex(row, col),
      getQuickUnionIndex(row, col - 1)
    );

    //check right
    if (col < matrixSize && isOpen(row, col + 1)) uf.union(
      getQuickUnionIndex(row, col),
      getQuickUnionIndex(row, col + 1)
    );
  }

  public boolean isOpen(int row, int col) {
    validateInputs(row, col);
    return nodeIsOpen[row - 1][col - 1];
  }

  public boolean isFull(int row, int col) {
    validateInputs(row, col);
    return uf.find(getQuickUnionIndex(row, col)) == uf.find(virtualTopNode);
  }

  public int numberOfOpenSites() {
    return openSites;
  }

  public boolean percolates() {
    return uf.find(virtualTopNode) == uf.find(virtualBottomNode);
  }
}
