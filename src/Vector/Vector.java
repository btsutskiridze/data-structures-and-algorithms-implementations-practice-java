package Vector;

import Search.Search;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class Vector<T extends Comparable<T>> implements IVector<T> {

    private static final int INITIAL_CAPACITY = 10;
    private T[] arr;
    private int length = 0;

    public Vector() {
        arr = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    public Vector(T... arr) {
        this.arr = arr;
        this.length = arr.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Array{ arr=[");

        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);

            if (i != length - 1) {
                sb.append(", ");
            }
        }

        sb.append(']');
        sb.append("; length=");
        sb.append(length);
        sb.append(" }");

        return sb.toString();
    }

    public void push(T value) {
        if (this.length >= arr.length) {
            this.resizeArray();
        }

        this.arr[this.length++] = value;
    }

    public void insert(T value, int index) {
        if (index < 0 || index > arr.length) {
            throw new ArrayIndexOutOfBoundsException("Invalid index specified");
        }

        if (length >= arr.length) {
            resizeArray();
        }

        for (int i = length; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
        length++;
    }

    public T pop() {
        if (this.length == 0) {
            throw new ArrayIndexOutOfBoundsException("Array is empty");
        }

        return this.arr[--this.length];
    }

    public void delete(T value) {
        int newIndex = 0;
        for (int i = 0; i < length; i++) {
            if (arr[i] != value) {
                arr[newIndex++] = arr[i];
            }
        }

        if (length == newIndex) {
            throw new ArrayIndexOutOfBoundsException("Array doesn't contain " + value);
        }

        length = newIndex;
    }

    public void deleteAt(int index) {
        if (index < 0 || index >= length) {
            throw new ArrayIndexOutOfBoundsException("Invalid index specified");
        }

        for (int i = index; i < length - 1; i++) {
            arr[i] = arr[i + 1];
        }

        length--;
    }

    public void display() {
        StringBuilder sb = new StringBuilder();
        sb.append("Array{ arr=[");

        for (int i = 0; i < length; i++) {
            sb.append(arr[i]);

            if (i != length - 1) {
                sb.append(", ");
            }
        }

        sb.append(']');
        sb.append("; length=");
        sb.append(length);
        sb.append(" }");

        System.out.println(sb);
    }

    public T[] elements() {
        return Arrays.copyOf(arr, length);
    }

    public void reverse() {
        for (int i = 0, j = length - 1; i < j; i++, j--) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public void setElements(T... arr) {
        for (T val : arr) {
            this.push(val);
        }
    }

    public int length() {
        return this.length;
    }

    //    public int search(T value) {
    //        return Search.binarySearch(this.elements(), value);
    //    }

    public Vector<T> union(Vector<T> other) {
        T[] arr = (T[]) new Comparable[this.length + other.length];
        int i = 0, j = 0, k = 0;

        while (i < this.length && j < other.length) {
            if (this.arr[i].compareTo(other.arr[j]) < 0) {
                arr[k++] = this.arr[i++];
            } else if (this.arr[i].compareTo(other.arr[j]) > 0) {
                arr[k++] = other.arr[j++];
            } else {
                arr[k++] = this.arr[i++];
                j++;
            }
        }

        for (; i < this.length; i++) {
            arr[k++] = this.arr[i];
        }

        for (; j < other.length; j++) {
            arr[k++] = other.arr[j];
        }

        return new Vector<>(arr);
    }

    public Vector<T> intersection(Vector<T> other) {
        T[] arr = (T[]) new Comparable[this.length + other.length];
        int i = 0, j = 0, k = 0;

        while (i < this.length && j < other.length) {
            if (this.arr[i].compareTo(other.arr[j]) < 0) {
                i++;
            } else if (this.arr[i].compareTo(other.arr[j]) > 0) {
                j++;
            } else {
                arr[k++] = this.arr[i++];
                j++;
            }
        }

        return new Vector<>(Arrays.copyOf(arr, k));
    }

    public Vector<T> difference(Vector<T> other) {
        T[] arr = (T[]) new Comparable[this.length + other.length];
        int i = 0, j = 0, k = 0;

        while (i < this.length && j < other.length) {
            if (this.arr[i].compareTo(other.arr[j]) < 0) {
                arr[k++] = this.arr[i++];
            } else if (this.arr[i].compareTo(other.arr[j]) > 0) {
                j++;
            } else {
                i++;
            }
        }

        for (; i < this.length; i++) {
            arr[k++] = this.arr[i];
        }

        return new Vector<>(Arrays.copyOf(arr, k));
    }

    private void resizeArray() {
        int newCapacity = Math.max(arr.length * 2, INITIAL_CAPACITY);
        this.arr = Arrays.copyOf(arr, newCapacity);
    }
}
