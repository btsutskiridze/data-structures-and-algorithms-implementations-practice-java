package matrix;

public interface IMatrix {

    void set(int x, int y, int value);

    int get(int x, int y);

    @Override
    String toString();


}
