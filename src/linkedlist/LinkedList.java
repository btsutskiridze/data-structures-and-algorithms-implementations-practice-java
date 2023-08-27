package linkedlist;

import linkedlist.node.Node;

import java.util.HashSet;

public class LinkedList<T> {

    public static void main(String[] args) {
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        l2.add(5);
        l1.add(10);
        l2.add(15);
        l1.add(20);
        l2.add(25);
        l1.add(30);
        l2.add(35);
        l1.add(40);
        l2.add(45);

        System.out.println(l1);
        System.out.println(l2);
        l1.merge(l2);
        System.out.println(l1);
        System.out.println(l1.isLoop());
    }


    public static LinkedList<Integer> mergeLists(LinkedList<Integer> a, LinkedList<Integer> b) {
        Node<Integer> aPointer = a.head;
        Node<Integer> bPointer = b.head;
        LinkedList<Integer> newList = new LinkedList<>();


        while (aPointer != null && bPointer != null) {
            if (aPointer.data == bPointer.data) {
                newList.add(aPointer.data);
                aPointer = aPointer.next;
                bPointer = bPointer.next;
            } else if (aPointer.data < bPointer.data) {
                newList.add(aPointer.data);
                aPointer = aPointer.next;
            } else {
                newList.add(bPointer.data);
                bPointer = bPointer.next;
            }
        }

        while (aPointer != null) {
            newList.add(aPointer.data);
            aPointer = aPointer.next;
        }

        while (bPointer != null) {
            newList.add(bPointer.data);
            bPointer = bPointer.next;
        }

        return newList;
    }

    @SuppressWarnings("unchecked")
    public void merge(LinkedList<Integer> o) {
        if (this.head != null && !(this.head.data instanceof Integer)) {
            throw new RuntimeException("Unsupported type for merge() method.");
        }

        Node<Integer> aPointer = (Node<Integer>) this.head;
        Node<Integer> bPointer = o.head;
        Node<Integer> newHead;
        Node<Integer> newTail;

        if (aPointer.data < bPointer.data) {
            newHead = aPointer;
            newTail = aPointer;
            aPointer = aPointer.next;
        } else {
            newHead = bPointer;
            newTail = bPointer;
            bPointer = bPointer.next;
        }

        while (aPointer != null && bPointer != null) {
            if (aPointer.data < bPointer.data) {
                newTail.next = aPointer;
                aPointer = aPointer.next;
            } else {
                newTail.next = bPointer;
                bPointer = bPointer.next;
            }
            newTail = newTail.next;
        }


        if (aPointer != null) {
            newTail.next = aPointer;
        }

        if (bPointer != null) {
            newTail.next = bPointer;
        }

        this.head = (Node<T>) newHead;
        this.tail = (Node<T>) newTail;
        this.size += o.size;
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

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

    public boolean isLoop() {
        if (this.size == 0) throw new RuntimeException("Empty list.");

        Node<T> slowPointer = this.head;
        Node<T> fastPointer = this.head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer)
                return true;
        }

        return false;
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

    public void removeDuplicates() {
        HashSet<T> set = new HashSet<>();
        Node<T> pointer = this.head;
        Node<T> prev = null;

        while (pointer != null) {
            if (set.contains(pointer.data)) {
                prev.next = pointer.next;
            } else {
                prev = pointer;
                set.add(pointer.data);
            }
            pointer = pointer.next;
        }

    }

    public void reverse() {
        if (size == 0) throw new RuntimeException("Empty list.");

        Node<T> previousNode;
        Node<T> currentNode = null;
        Node<T> nextNode = this.head;

        while (nextNode != null) {
            previousNode = currentNode;
            currentNode = nextNode;
            nextNode = nextNode.next;
            currentNode.next = previousNode;
        }

        this.head = currentNode;
    }

    public void reverseRecursively(Node<T> previousNode, Node<T> currentNode) {
        if (currentNode == null) {
            return;
        }

        reverseRecursively(currentNode, currentNode.next);
        currentNode.next = previousNode;
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