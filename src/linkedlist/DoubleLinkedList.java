package linkedlist;

import linkedlist.node.DbNode;

class DbDriver {
    public static void main(String[] args) {
        DoubleLinkedList l1 = new DoubleLinkedList();
        l1.add(10);
        l1.add(20);
        l1.add(30);
        l1.add(40);
        l1.add(50);
        l1.add(60);
        l1.add(70);
        l1.add(80);
        l1.add(90);
        System.out.println(l1);
        System.out.println(l1.middle());
    }
}

public class DoubleLinkedList {
    DbNode<Integer> head;
    int size;


    public DoubleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void add(int value) {
        if (this.head == null) {
            this.head = new DbNode<>(value);
            size++;
            return;
        }

        DbNode<Integer> pointer = this.head;
        DbNode<Integer> newNode = new DbNode<>(value);

        while (pointer.next != null) {
            pointer = pointer.next;
        }

        pointer.next = newNode;
        newNode.prev = pointer;
        this.size++;
    }

    public DbNode<Integer> valueAt(int position) {
        if (position < 0 || position >= this.size) {
            throw new IndexOutOfBoundsException("Invalid position.");
        }

        DbNode<Integer> ptr = this.head;

        for (int i = 0; i < position; i++) {
            ptr = ptr.next;
        }

        return ptr;
    }

    public void insert(int position, int value) {
        if (position < 0 || position > this.size) {
            throw new IndexOutOfBoundsException("Invalid position.");
        }

        DbNode<Integer> newNode = new DbNode<>(value);

        if (position == 0) {
            if (this.size == 0) {
                this.head = newNode;
                this.size++;
                return;
            }

            this.head.prev = newNode;
            newNode.next = this.head;
            this.head = newNode;
            this.size++;
            return;
        }


        DbNode<Integer> ptr = this.head;

        for (int i = 0; i < position - 1; i++) {
            ptr = ptr.next;
        }

        newNode.next = ptr.next;
        newNode.prev = ptr;
        if (ptr.next != null) {
            ptr.next.prev = newNode;
        }
        ptr.next = newNode;
        this.size++;
    }

    public void delete(int position) {
        if (position < 0 || position >= this.size) {
            throw new IndexOutOfBoundsException("Invalid position.");
        }

        if (position == 0) {
            if (this.size == 1) {
                this.head = null;
                this.size--;
                return;
            }

            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            return;
        }

        DbNode<Integer> ptr = this.head;
        for (int i = 0; i < position; i++) {
            ptr = ptr.next;
        }


        ptr.prev.next = ptr.next;
        if (ptr.next != null) {
            ptr.next.prev = ptr.prev;
        }
        this.size--;
    }

    public void reverse() {
        DbNode<Integer> ptr = this.head;

        while (ptr != null) {
            DbNode<Integer> temp = ptr.next;
            ptr.next = ptr.prev;
            ptr.prev = temp;
            ptr = ptr.prev;

            if (ptr != null && ptr.next == null) {
                this.head = ptr;
            }
        }
    }

    public void reverse(DbNode<Integer> node) {
        if (node == null) return;

        DbNode<Integer> temp = node.next;
        node.next = node.prev;
        node.prev = temp;
        node = node.prev;

        if (node != null && node.next == null) {
            this.head = node;
        }

        reverse(node);
    }

    public DbNode<Integer> middle() {


        DbNode<Integer> fast = this.head;
        DbNode<Integer> slow = this.head;

        while (fast != null && fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }

        }

        return slow;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DbNode<Integer> ptr = this.head;

        while (ptr != null) {
            sb.append(ptr);
            if (ptr.next != null) {
                sb.append("->");
            }
            ptr = ptr.next;
        }
        return sb.toString();
    }
}
