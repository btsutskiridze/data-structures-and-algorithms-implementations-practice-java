package linkedlist;

import linkedlist.node.Node;

public class LinkedList<T> {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(10);
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);


        System.out.println(list.isSorted());
    }


    Node<T> head;
    Node<T> tail;
    int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            this.size++;
            return;
        }

        this.tail.next = newNode;
        this.tail = newNode;
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
            this.tail = this.head;
            this.size++;
            return;
        }

        if (position == this.size) {
            this.tail.next = new Node<>(value);
            this.tail = this.tail.next;
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

    public void insertSorted(T value) {
        if (!(value instanceof Integer)) {
            throw new RuntimeException("Unsupported type for insertSorted() method.");
        }

        if (this.size == 0) {
            this.head = new Node<>(value);
            this.tail = this.head;
            this.size++;
            return;
        }

        if ((int) value < (int) this.head.data) {
            this.head = new Node<>(value, this.head);
            this.size++;
            return;
        }

        if ((int) value > (int) this.tail.data) {
            this.tail.next = new Node<>(value);
            this.tail = this.tail.next;
            this.size++;
            return;
        }

        Node<T> pointer = this.head;

        while (pointer.next != null) {
            if ((int) value < (int) pointer.next.data) {
                pointer.next = new Node<>(value, pointer.next);
                this.size++;
                return;
            }
            pointer = pointer.next;
        }
    }

    public boolean isSorted() {
        if (this.size == 0) throw new RuntimeException("Empty list.");

        if (!(this.head.data instanceof Integer)) throw new RuntimeException("Unsupported type for isSorted() method.");

        Node<T> p = this.head;

        while (p.next != null) {
            if ((int) p.data > (int) p.next.data)
                return false;

            p = p.next;
        }

        return true;
    }


    public Node<T> delete(T value) {
        if (this.size == 0) throw new RuntimeException("Empty list.");

        Node<T> pointer = this.head;
        Node<T> prev = null;

        while (pointer != null) {
            if (value == pointer.data) {
                if (prev != null) {
                    prev.next = pointer.next;
                } else {
                    this.head = pointer.next;
                }
                this.size--;
                return pointer;
            }
            prev = pointer;
            pointer = pointer.next;
        }

        return null;
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