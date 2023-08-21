package matrix.accessstrategy;

public class RowMajorAccess implements AccessStrategy {
    @Override
    public int getIndex(int x, int y, int dimension) {
        return (x * (x - 1) / 2 + y - 1);
    }
}
