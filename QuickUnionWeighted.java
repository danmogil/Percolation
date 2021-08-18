public class QuickUnionWeighted {
  private int[] arr;
  private int[] arrCount;
  private int count;

  public QuickUnionWeighted(int N) {
    if (N <= 0) throw new IllegalArgumentException("arg must be > 0");
    arr = new int[N];
    arrCount = new int[N];
    count = N;

    for (int i = 0; i < N; i++) {
      arr[i] = i;
      arrCount[i] = 1;
    }
  }

  private void validateArg(int p) {
    int arrLength = arr.length;
    if (p < 0 || p >= arrLength) throw new IllegalArgumentException(
      "arg must be >= 0 && < " + arrLength
    );
  }

  public int find(int p) {
    validateArg(p);
    while (p != arr[p]) p = arr[p];
    return p;
  }

  public void union(int p, int q) {
    int rootP = find(p);
    int rootQ = find(q);
    if (rootP == rootQ) return;

    if (arrCount[rootP] < arrCount[rootQ]) {
      arr[rootP] = rootQ;
      arrCount[rootQ] += arrCount[rootP];
    } else {
      arr[rootQ] = rootP;
      arrCount[rootP] += arrCount[rootQ];
    }
    count--;
  }

  public int count() {
    return count;
  }

  public int countRoot(int p) {
    return arrCount[find(p)];
  }
}
