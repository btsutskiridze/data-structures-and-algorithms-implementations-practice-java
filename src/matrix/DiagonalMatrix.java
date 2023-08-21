package matrix;

public class DiagonalMatrix implements IMatrix {

    private final int[] A;
    private final int dimension;

    public DiagonalMatrix(int n) {
        this.dimension = n;
        this.A = new int[n];
    }

    public void set(int x, int y, int value) {
        if (x < 1 || x > dimension || y < 1 || y > dimension) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (x == y) {
            this.A[x - 1] = value;
        }
    }

    public int get(int x, int y) {
        if (x < 1 || x > dimension || y < 1 || y > dimension) {
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
        for (int i = 1; i <= this.dimension; i++) {
            for (int j = 1; j <= this.dimension; j++) {
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
