package matrix.accessstrategy.lowertriangular;


import matrix.accessstrategy.lowertriangular.LowerTriangularAccessStrategy;

public class LowerTriangularColumnMajorAccess implements LowerTriangularAccessStrategy {

    @Override
    public int getIndex(int x, int y, int dimension) {
        return (dimension * (y - 1) - (((y - 2) * (y - 1)) / 2) + (x - y));
    }
}
