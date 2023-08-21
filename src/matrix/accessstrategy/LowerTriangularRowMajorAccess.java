package matrix.accessstrategy;

import matrix.accessstrategy.interfaces.LowerTriangularAccessStrategy;

public class LowerTriangularRowMajorAccess implements LowerTriangularAccessStrategy {
    @Override
    public int getIndex(int x, int y, int dimension) {
        return (x * (x - 1) / 2 + y - 1);
    }
}
