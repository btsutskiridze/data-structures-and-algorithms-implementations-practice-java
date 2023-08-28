package linkedlist.node;

public class DbNode<T> {
    public T data;
    public DbNode<T> next;
    public DbNode<T> prev;

    public DbNode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public DbNode(T data, DbNode<T> prev, DbNode<T> next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }


    @Override
    public String toString() {
        return "[" + this.data + "]";
    }
}
