package Vector;

interface IVector {

    void display();

    void push(int value);

    void insert(int value, int index);

    int pop();

    void delete(int value);

    void deleteAt(int index);

    Vector union(Vector other);

    Vector intersection(Vector other);

    Vector difference(Vector other);
}
