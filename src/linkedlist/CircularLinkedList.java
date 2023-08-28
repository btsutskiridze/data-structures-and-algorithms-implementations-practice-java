package linkedlist;

import linkedlist.node.Node;

class Main {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.insert(0, 10);
//        list.insert(1, 20);
//        list.insert(2, 30);
//        list.insert(3, 40);
//        list.insert(4, 50);
//        list.insert(2, 60);

        System.out.println(list);

        list.delete(0);
        System.out.println(list);
    }
}

public class CircularLinkedList {
    Node<Integer> head;
    int size;

    public CircularLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(int value) {
        Node<Integer> newNode = new Node<>(value);

        if (this.head == null) {
            this.head = newNode;
            this.head.next = this.head;
            this.size++;
            return;
        }

        Node<Integer> pointer = this.head;

        while (pointer.next != this.head) {
            pointer = pointer.next;
        }

        pointer.next = newNode;
        newNode.next = this.head;
        this.size++;
    }

    public void insert(int position, int value) {
        if (position < 0 || position > this.size) {
            throw new IndexOutOfBoundsException("Invalid position.");
        }

        if (position == 0) {
            Node<Integer> newNode = new Node<>(value);
            Node<Integer> ptr = this.head;

            if (this.size == 0) {
                this.head = newNode;
                newNode.next = this.head;
                this.size++;
                return;
            }

            while (ptr.next != this.head) {
                ptr = ptr.next;
            }
            ptr.next = newNode;
            newNode.next = this.head.next;
            this.head = newNode;
            this.size++;
            return;
        }


        Node<Integer> ptr = this.head;

        for (int i = 0; i < position - 1; i++) {
            ptr = ptr.next;
        }

        ptr.next = new Node<>(value, ptr.next);
        this.size++;
    }

    public void delete(int position) {
        if (position < 0 || position > this.size) {
            throw new IndexOutOfBoundsException("Invalid position.");
        }

        Node<Integer> ptr = this.head;

        if (position == 0) {
            if (this.size == 1) {
                this.head = null;
                this.size--;
                return;
            }

            while (ptr.next != this.head) {
                ptr = ptr.next;
            }

            ptr.next = this.head.next;
            this.head = this.head.next;
            this.size--;

            return;
        }

        for (int i = 0; i < position - 1; i++) {
            ptr = ptr.next;
        }
        ptr.next = ptr.next.next;
        this.size--;
    }

    @Override
    public String toString() {
        if (this.size == 0) return "[]";

        StringBuilder sb = new StringBuilder();

        Node<Integer> pointer = this.head;

        do {
            sb.append(pointer);

            if (pointer.next != this.head) sb.append("->");

            pointer = pointer.next;
        } while (pointer != this.head);

        return sb.toString();
    }

}
