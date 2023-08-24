package linkedlist;

import linkedlist.node.Node;

public class LinkedList<T> {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(99);
        list.add(2);
        list.add(200);

        System.out.println(list);
        System.out.println(list.max());
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