package Queue;

class Driver2 {
    public static void main(String[] args) {
        CircularQueue<Integer> queue = new CircularQueue<Integer>(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());

        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println(queue);
    }
}

@SuppressWarnings("unchecked")
public class CircularQueue<T> {
    int size;
    int front;
    int rear;
    T[] queue;

    public CircularQueue(int size) {
        this.size = size + 1;
        this.front = 0;
        this.rear = 0;
        this.queue = (T[]) new Object[this.size];
    }

    public void enqueue(T value) {
        if (this.isFull()) throw new RuntimeException("Queue is full");

        this.rear = (this.rear + 1) % this.size;
        this.queue[rear] = value;
    }

    public T dequeue() {

        if (this.isEmpty()) throw new RuntimeException("Queue is empty");

        this.front = (this.front + 1) % this.size;

        T removed = this.queue[this.front];

        return removed;
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public boolean isFull() {
        return (this.rear + 1) % this.size == this.front;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) return "Queue is empty";

        StringBuilder sb = new StringBuilder();
        int i = this.front + 1;

        do {
            sb.append(this.queue[i]);
            sb.append(" ");
            i = (i + 1) % this.size;
        } while (i != (this.rear + 1) % this.size);

        return sb.toString();
    }
}
