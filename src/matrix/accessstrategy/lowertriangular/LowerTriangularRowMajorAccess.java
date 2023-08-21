package matrix.accessstrategy.lowertriangular;

import matrix.accessstrategy.lowertriangular.LowerTriangularAccessStrategy;

public class LowerTriangularRowMajorAccess implements LowerTriangularAccessStrategy {
    @Override
    public int getIndex(int x, int y, int dimension) {
        return (x * (x - 1) / 2 + y - 1);
    }
}
