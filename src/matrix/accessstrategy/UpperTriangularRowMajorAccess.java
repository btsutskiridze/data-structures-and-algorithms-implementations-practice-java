package matrix.accessstrategy;

import matrix.accessstrategy.interfaces.UpperTriangularAccessStrategy;

public class UpperTriangularRowMajorAccess implements UpperTriangularAccessStrategy {

    @Override
    public int getIndex(int x, int y, int dimension) {
        return (dimension * (x - 1) - (((x - 1) * (x - 2)) / 2) + (y - x));
    }
}