package matrix;

import matrix.accessstrategy.lowertriangular.LowerTriangularAccessStrategy;
import matrix.accessstrategy.lowertriangular.LowerTriangularColumnMajorAccess;

import java.util.Arrays;

public class SymmetricMatrix extends LowerTriangularMatrix {

    public static void main(String[] args) {
        SymmetricMatrix m = new SymmetricMatrix(4, new LowerTriangularColumnMajorAccess());
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


    public SymmetricMatrix(int dimension, LowerTriangularAccessStrategy accessStrategy) {
        super(dimension, accessStrategy);
    }

    public void set(int x, int y, int value) {
        if (x < y) {
            super.set(y, x, value);
        } else {
            super.set(x, y, value);
        }
    }

    @Override
    public int get(int x, int y) {
        if (x < y) {
            return super.get(y, x);
        } else {
            return super.get(x, y);
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 1; i <= this.dimension; i++) {
            for (int j = 1; j <= this.dimension; j++) {
                if (i >= j) {
                    s.append(this.get(i, j));
                    s.append(" ");
                } else {
                    s.append(this.get(j, i));
                    s.append(" ");
                }
            }
            s.append("\n");
        }

        return s.toString();
    }
}
