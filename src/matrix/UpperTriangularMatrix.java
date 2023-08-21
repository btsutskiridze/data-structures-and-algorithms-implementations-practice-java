package matrix;

import matrix.accessstrategy.uppertriangular.UpperTriangularRowMajorAccess;
import matrix.accessstrategy.uppertriangular.UpperTriangularAccessStrategy;

public class UpperTriangularMatrix implements IMatrix {

    public static void main(String[] args) {
        UpperTriangularMatrix m = new UpperTriangularMatrix(4, new UpperTriangularRowMajorAccess());
        m.set(1, 1, 1);
        m.set(1, 2, 2);
        m.set(2, 2, 3);
        m.set(1, 3, 4);
        m.set(2, 3, 5);
        m.set(3, 3, 6);
        m.set(1, 4, 7);
        m.set(2, 4, 8);
        m.set(3, 4, 9);
        m.set(4, 4, 10);

        System.out.println(m);

    }


    private final UpperTriangularAccessStrategy accessStrategy;
    private final int[] A;
    private final int dimension;

    public UpperTriangularMatrix(int dimension, UpperTriangularAccessStrategy accessStrategy) {
        this.accessStrategy = accessStrategy;
        this.dimension = dimension;
        this.A = new int[(dimension * (dimension + 1)) / 2];
    }

    public void set(int x, int y, int value) {
        if (x < 1 || x > dimension || y < 1 || y > dimension) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (x <= y) {
            this.A[this.accessStrategy.getIndex(x, y, dimension)] = value;
        }
    }

    public int get(int x, int y) {
        if (x < 1 || x > dimension || y < 1 || y > dimension) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (x <= y) {
            return this.A[this.accessStrategy.getIndex(x, y, dimension)];
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 1; i <= this.dimension; i++) {
            for (int j = 1; j <= this.dimension; j++) {
                if (i <= j) {
                    s.append(this.A[this.accessStrategy.getIndex(i, j, this.dimension)]);
                    s.append(" ");
                } else {
                    s.append("0 ");
                }
            }
            s.append("\n");
        }

//        System.out.println(Arrays.toString(this.A));
        return s.toString();
    }
}
