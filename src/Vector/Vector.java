package Vector;

import Search.Search;
import java.util.Arrays;

public class Vector implements IVector {

    private static final int INITIAL_CAPACITY = 10;
    private int[] arr;
    private int length = 0;

    public Vector() {
        arr = new int[INITIAL_CAPACITY];
    }

    public Vector(int... arr) {
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

    public void push(int value) {
        if (this.length >= arr.length) {
            this.resizeArray();
        }

        this.arr[this.length++] = value;
    }

    public void insert(int value, int index) {
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

    public int pop() {
        if (this.length == 0) {
            throw new ArrayIndexOutOfBoundsException("Array is empty");
        }

        return this.arr[--this.length];
    }

    public void delete(int value) {
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

    public int[] elements() {
        return Arrays.copyOf(arr, length);
    }

    public void reverse() {
        for (int i = 0, j = length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public void setElements(int... arr) {
        for (int val : arr) {
            this.push(val);
        }
    }

    public int length() {
        return this.length;
    }

    public int search(int value) {
        return Search.binarySearch(this.elements(), value);
    }

    public Vector union(Vector other) {
        int[] arr = new int[this.length + other.length];
        int i = 0, j = 0, k = 0;

        while (i < this.length && j < other.length) {
            if (this.arr[i] < other.arr[j]) {
                arr[k++] = this.arr[i++];
            } else if (this.arr[i] > other.arr[j]) {
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

        return new Vector(arr);
    }

    public Vector intersection(Vector other) {
        int[] arr = new int[this.length + other.length];
        int i = 0, j = 0, k = 0;

        while (i < this.length && j < other.length) {
            if (this.arr[i] < other.arr[j]) {
                i++;
            } else if (this.arr[i] > other.arr[j]) {
                j++;
            } else {
                arr[k++] = this.arr[i++];
                j++;
            }
        }

        return new Vector(Arrays.copyOf(arr, k));
    }

    public Vector difference(Vector other) {
        int[] arr = new int[this.length + other.length];
        int i = 0, j = 0, k = 0;

        while (i < this.length && j < other.length) {
            if (this.arr[i] < other.arr[j]) {
                arr[k++] = this.arr[i++];
            } else if (this.arr[i] > other.arr[j]) {
                j++;
            } else {
                i++;
            }
        }

        for (; i < this.length; i++) {
            arr[k++] = this.arr[i];
        }

        return new Vector(Arrays.copyOf(arr, k));
    }

    private void resizeArray() {
        int newCapacity = Math.max(arr.length * 2, INITIAL_CAPACITY);
        this.arr = Arrays.copyOf(arr, newCapacity);
    }
}
