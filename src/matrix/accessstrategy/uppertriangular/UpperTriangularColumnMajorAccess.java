package matrix.accessstrategy.uppertriangular;

import matrix.accessstrategy.uppertriangular.UpperTriangularAccessStrategy;

public class UpperTriangularColumnMajorAccess implements UpperTriangularAccessStrategy {

    @Override
    public int getIndex(int x, int y, int dimension) {
        return ((y * (y - 1)) / 2 + (x - 1));
    }
}
