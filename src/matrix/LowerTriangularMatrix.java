package matrix;


import matrix.accessstrategy.LowerTriangularColumnMajorAccess;
import matrix.accessstrategy.interfaces.LowerTriangularAccessStrategy;

public class LowerTriangularMatrix implements IMatrix {

    public static void main(String[] args) {
        LowerTriangularMatrix m = new LowerTriangularMatrix(4, new LowerTriangularColumnMajorAccess());
        m.set(1, 1, 1);
        m.set(2, 2, 2);
        m.set(3, 3, 3);
        m.set(4, 1, 4);
        m.set(4, 2, 5);
        m.set(4, 3, 6);
        m.set(4, 4, 7);
        m.set(3, 1, 8);
        m.set(3, 2, 9);
        m.set(2, 1, 10);
        System.out.println(m);

    }

    private final LowerTriangularAccessStrategy accessStrategy;
    private final int[] A;

    private final int dimension;

    public LowerTriangularMatrix(int dimension, LowerTriangularAccessStrategy accessStrategy) {
        this.accessStrategy = accessStrategy;
        A = new int[(dimension * (dimension + 1)) / 2];
        this.dimension = dimension;
    }

    public void set(int x, int y, int value) {
        this.A[this.accessStrategy.getIndex(x, y, this.dimension)] = value;
    }

    public int get(int x, int y) {
        return this.A[this.accessStrategy.getIndex(x, y, this.dimension)];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 1; i <= this.dimension; i++) {
            for (int j = 1; j <= this.dimension; j++) {
                if (i >= j) {
                    s.append(this.A[this.accessStrategy.getIndex(i, j, this.dimension)]);
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
