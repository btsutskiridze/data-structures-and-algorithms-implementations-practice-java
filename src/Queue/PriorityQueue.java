package Queue;

import linkedlist.node.Node;

public class PriorityQueue<T extends Comparable<T>> {

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(4);

        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(1);

        System.out.println(queue);
    }


    int size;
    Node<T> front;

    public PriorityQueue() {
        this.size = 0;
        this.front = null;
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);

        if (this.isEmpty()) {
            this.front = newNode;
            this.size++;
            return;
        }

        Node<T> ptr = this.front;
        Node<T> prev = null;

        while (ptr != null && ptr.data.compareTo(value) < 0) {
            prev = ptr;
            ptr = ptr.next;
        }

        if (prev == null) {
            newNode.next = this.front;
            this.front = newNode;
            this.size++;
            return;
        }

        newNode.next = prev.next;
        prev.next = newNode;
        this.size++;
    }

    public T dequeue() {
        if (this.isEmpty()) {
            return null;
        }

        T removed = this.front.data;
        this.front = this.front.next;
        this.size--;

        return removed;
    }


    public boolean isEmpty() {
        return this.front == null;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) return "Queue is empty";

        StringBuilder sb = new StringBuilder();

        Node<T> ptr = this.front;

        while (ptr != null) {
            sb.append(ptr.data).append(" ");
            ptr = ptr.next;
        }

        return sb.toString();
    }
}
