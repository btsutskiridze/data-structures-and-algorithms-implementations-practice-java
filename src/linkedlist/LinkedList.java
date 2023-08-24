package linkedlist;

import linkedlist.node.Node;

public class LinkedList<T> {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(0, 8);
        list.insert(1, 3);
        list.insert(2, 6);
        list.insert(0, 5);
        list.insert(3, 9);

        System.out.println(list);
    }


    Node<T> head;
    int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(T data) {
        if (this.head == null) {
            this.head = new Node<T>(data);
            this.size++;
            return;
        }

        Node<T> temp = this.head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = new Node<T>(data);
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public int sum() {
        if (!(this.head.data instanceof Integer)) {
            throw new RuntimeException("Unsupported type for sum() method.");
        }

        int sum = 0;
        Node<T> temp = this.head;

        while (temp != null) {
            sum += (int) temp.data;
            temp = temp.next;
        }

        return sum;
    }

    public int max() {
        if (this.size == 0) throw new RuntimeException("Empty list.");

        if (!(this.head.data instanceof Integer)) {
            throw new RuntimeException("Unsupported type for sum() method.");
        }

        int max = Integer.MIN_VALUE;
        Node<T> temp = this.head;

        while (temp != null) {
            if ((int) temp.data > max) {
                max = (int) temp.data;
            }
            temp = temp.next;
        }

        return max;
    }

    public Node<T> search(T value) {
        if (this.size == 0) throw new RuntimeException("Empty list.");

        Node<T> pointer = this.head;
        Node<T> prev = null;

        while (pointer != null) {
            if (value == pointer.data) {
                if (prev != null) {
                    prev.next = pointer.next;
                    pointer.next = this.head;
                    this.head = pointer;
                }
                return pointer;
            }
            prev = pointer;
            pointer = pointer.next;
        }

        return null;
    }

    public void insert(int position, T value) {
        if (position < 0 || position > this.size) {
            throw new RuntimeException("Invalid position");
        }

        if (position == 0) {
            this.head = new Node<>(value, this.head);
            this.size++;
            return;
        }

        Node<T> pointer = this.head;

        for (int i = 0; i < position - 1; i++) {
            pointer = pointer.next;
        }

        pointer.next = new Node<>(value, pointer.next);
        this.size++;
    }

    @Override
    public String toString() {
        if (this.size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        Node<T> temp = head;

        while (temp != null) {
            sb.append(temp);

            if (temp.next != null) {
                sb.append("->");
            }

            temp = temp.next;
        }

        return sb.toString();
    }
}