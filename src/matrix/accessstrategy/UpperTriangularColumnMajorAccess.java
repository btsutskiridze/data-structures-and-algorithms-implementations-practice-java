package matrix.accessstrategy;

import matrix.accessstrategy.interfaces.UpperTriangularAccessStrategy;

public class UpperTriangularColumnMajorAccess implements UpperTriangularAccessStrategy {

    @Override
    public int getIndex(int x, int y, int dimension) {
        return ((y * (y - 1)) / 2 + (x - 1));
    }
}
