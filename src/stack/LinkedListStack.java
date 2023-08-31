package stack;

public class LinkedListStack<T> {

    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack);
        System.out.println(stack.peek(0));
    }

    private Node<T> top;
    private int size;

    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    public void push(T value) {
        this.top = new Node<T>(value, this.top);
        this.size++;
    }

    public T pop() {
        this.validateIsEmpty();

        T poppedData = this.top.data;
        this.top = this.top.next;
        this.size--;

        return poppedData;
    }

    public T top() {
        this.validateIsEmpty();
        return this.top.data;
    }

    public T peek(int position) {
        this.validateIsEmpty();
        this.validatePosition(position);

        Node<T> ptr = this.top;

        for (int i = 0; i < this.size - position - 1; i++) {
            ptr = ptr.next;
        }

        return ptr.data;
    }

    private void validateIsEmpty() {
        if (this.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
    }

    private void validatePosition(int position) {
        if (position < 0 || position >= this.size) {
            throw new RuntimeException("Position is out of range!");
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.top == null;
    }

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        Node<T> ptr = this.top;
        StringBuilder sb = new StringBuilder();

        while (ptr != null) {
            sb.append(ptr.data);
            sb.append(" ");
            ptr = ptr.next;
        }

        return sb.toString();
    }
}
