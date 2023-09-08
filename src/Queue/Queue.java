package Queue;

import linkedlist.node.DbNode;


class Driver {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue);
    }
}


public class Queue<T> {
    private DbNode<T> front;
    private DbNode<T> rear;
    private int size;

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T value) {
        DbNode<T> newNode = new DbNode<>(value);

        if (this.front == null) {
            this.front = newNode;
            this.rear = newNode;
            this.size++;
            return;
        }

        newNode.next = this.rear;
        this.rear.prev = newNode;
        this.rear = newNode;
        this.size++;
    }

    public T dequeue() {
        if (this.isEmpty()) throw new RuntimeException("Queue is empty");

        T removed = this.front.data;

        if (this.size == 1) {
            this.front = null;
            this.rear = null;
            this.size--;
            return removed;
        }

        this.front = this.front.prev;
        this.front.next = null;
        this.size--;

        return removed;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public T front() {
        return this.front.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DbNode<T> ptr = this.rear;
        sb.append("Queue{SIZE=");
        sb.append(this.size);
        sb.append(", [");
        while (ptr != null) {
            sb.append(ptr.data);
            if (ptr.next != null) sb.append(',');
            ptr = ptr.next;
        }

        sb.append("]}");

        return sb.toString();
    }
}
