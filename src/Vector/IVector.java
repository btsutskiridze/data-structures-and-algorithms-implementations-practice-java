package Vector;

interface IVector<T extends Comparable<T>> {

    void display();

    void push(T value);

    void insert(T value, int index);

    T pop();

    void delete(T value);

    void deleteAt(int index);

    Vector<T> union(Vector<T> other);

    Vector<T> intersection(Vector<T> other);

    Vector<T> difference(Vector<T> other);
}
