package matrix;

public class DiagonalMatrix {

    private final int[] A;
    private final int n;

    public DiagonalMatrix(int n) {
        this.n = n;
        this.A = new int[n];
    }

    public void set(int x, int y, int value) {
        if (x < 1 || x > n || y < 1 || y > n) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (x == y) {
            this.A[x - 1] = value;
        }
    }

    public int get(int x, int y) {
        if (x < 1 || x > n || y < 1 || y > n) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (x == y) {
            return this.A[x - 1];
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= this.n; i++) {
            for (int j = 1; j <= this.n; j++) {
                if (i == j) {
                    s.append(this.A[i - 1]);
                    s.append(" ");
                } else {
                    s.append("0 ");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

}
