package linkedlist;

import linkedlist.node.Node;

public class LinkedList<T> {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

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