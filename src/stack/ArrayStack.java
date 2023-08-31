package stack;

public class ArrayStack {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.top());
        System.out.println(stack.peek(1));
    }

    private final int[] items;
    private int count;
    private final int size;

    public ArrayStack(int size) {
        this.items = new int[size];
        this.count = 0;
        this.size = size;
    }

    public void push(int value) {
        this.validateIsFull();
        this.items[count++] = value;
    }

    public int pop() {
        this.validateIsEmpty();
        return this.items[--count];
    }

    public int peek(int index) {
        validateIntex(index);
        return this.items[index];
    }

    public int top() {
        this.validateIsFull();
        return this.items[this.count - 1];
    }

    public void validateIntex(int index) {
        if (index < 0 || index >= this.count) {
            throw new RuntimeException("Index is out of range!");
        }
    }

    public void validateIsFull() {
        if (this.count == this.size) {
            throw new RuntimeException("Stack is full!");
        }
    }

    public void validateIsEmpty() {
        if (this.count == 0) {
            throw new RuntimeException("Stack is empty!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = count - 1; i >= 0; i--) {
            sb.append(this.items[i]);
            sb.append(" ");
        }

        return sb.toString();
    }
}
