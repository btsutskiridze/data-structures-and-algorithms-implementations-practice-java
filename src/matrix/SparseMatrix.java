package matrix;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.Arrays;

public class SparseMatrix {

    public static void main(String[] args) throws SizeLimitExceededException {
        SparseMatrix sp = new SparseMatrix(4, 5);
        sp.set(0, 3, 2);
        sp.set(1, 3, 4);
        sp.set(2, 4, 5);
        sp.set(3, 2, 3);
        sp.set(0, 0, 1);
        sp.set(1, 1, 1);

        SparseMatrix sp1 = new SparseMatrix(4, 5);
        sp1.set(0, 0, 1);
        sp1.set(0, 3, 2);
        sp1.set(3, 2, 3);
        sp1.set(1, 3, 4);
        sp1.set(2, 3, 5);

        System.out.println(sp);
        System.out.println(sp1);
        sp.add(sp1);

        System.out.println(sp);
    }

    private class ElementNode {
        int col;
        int value;

        ElementNode next;

        ElementNode(int col, int value) {
            this.col = col;
            this.value = value;
            this.next = null;
        }

        ElementNode(int col, int value, ElementNode next) {
            this.col = col;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    private final ElementNode[] elementRows;
    private final int rows;
    private final int cols;
    private final int maxSize;
    private int currentSize;

    private void validateSize() throws SizeLimitExceededException {
        if (this.currentSize >= this.maxSize)
            throw new SizeLimitExceededException("Sparse matrix have to takes only 1/3 of the space.");
    }

    private void validatePosition(int row, int col) throws IndexOutOfBoundsException {
        if (row < 0 || col < 0 || row >= this.rows || col >= this.cols)
            throw new IndexOutOfBoundsException("Invalid position given");
    }

    private void validateMatrix(SparseMatrix o) throws IllegalArgumentException {
        if (this.rows != o.rows || this.cols != o.cols)
            throw new IllegalArgumentException("Matrices must have the same dimensions");
    }

    public SparseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.maxSize = ((rows * cols) / 3);
        this.currentSize = 0;
        this.elementRows = new ElementNode[rows];
    }

    public void set(int row, int col, int value) throws SizeLimitExceededException, IndexOutOfBoundsException {
        validateSize();
        validatePosition(row, col);

        if (value == 0) return;

        ElementNode ptr = this.elementRows[row];
        ElementNode newNode = new ElementNode(col, value);

        if (ptr == null || col < ptr.col) {
            newNode.next = ptr;
            this.elementRows[row] = newNode;
            this.currentSize++;
            return;
        }

        while (ptr.next != null && col > ptr.next.col) {
            ptr = ptr.next;
        }

        if (ptr.next != null && col == ptr.next.col) {
            ptr.next.value = value;
        } else {
            newNode.next = ptr.next;
            ptr.next = newNode;
            this.currentSize++;
        }
    }

    public int get(int row, int col) throws IndexOutOfBoundsException {
        validatePosition(row, col);

        ElementNode ptr = this.elementRows[row];
        if (ptr == null) return 0;

        while (ptr != null) {
            if (col == ptr.col) {
                return ptr.value;
            }
            ptr = ptr.next;
        }

        return 0;
    }

    public void add(SparseMatrix othr) {
        validateMatrix(othr);

        for (int i = 0; i < this.rows; i++) {
            ElementNode ptr = this.elementRows[i];
            ElementNode othrPtr = othr.elementRows[i];
            ElementNode prev = null;

            while (ptr != null && othrPtr != null) {
                if (ptr.col == othrPtr.col) {
                    ptr.value += othrPtr.value;
                    prev = ptr;
                    ptr = ptr.next;
                    othrPtr = othrPtr.next;
                } else if (ptr.col < othrPtr.col) {
                    prev = ptr;
                    ptr = ptr.next;
                } else {
                    ElementNode newNode = new ElementNode(othrPtr.col, othrPtr.value);
                    newNode.next = ptr;
                    if (prev == null) {
                        this.elementRows[i] = newNode;
                    } else {
                        prev.next = newNode;
                    }
                    prev = newNode;
                    othrPtr = othrPtr.next;
                    this.currentSize++;
                }
            }

            while (othrPtr != null) {
                ElementNode newNode = new ElementNode(othrPtr.col, othrPtr.value);
                if (prev == null) {
                    this.elementRows[i] = newNode;
                } else {
                    prev.next = newNode;
                }
                prev = newNode;
                othrPtr = othrPtr.next;
                this.currentSize++;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            ElementNode ptr = elementRows[i];
            for (int j = 0; j < cols; j++) {
                if (ptr != null && j == ptr.col) {
                    sb.append(ptr.value);
                    sb.append(" ");
                    ptr = ptr.next;
                } else {
                    sb.append("0 ");
                }
            }
            sb.append('\n');
        }

        return sb.toString();
    }

    //    private SparseMatrix(int rows, int cols, ArrayList<Element> elements) {
//        this.rows = rows;
//        this.cols = cols;
//        this.elements = elements;
//        this.numberOfElements = elements.size();
//    }
//
//    public void set(int row, int col, int value) {
//        if (row < 1 || row > this.rows || col < 1 || col > this.cols) {
//            throw new IndexOutOfBoundsException("Index out of bounds");
//        }
//
//        if (value != 0) {
//            this.elements.add(new Element(row, col, value));
//        }
//    }
//
//    public int get(int row, int col) {
//        if (row < 1 || row > this.rows || col < 1 || col > this.cols) {
//            throw new IndexOutOfBoundsException("Index out of bounds");
//        }
//
//        return this.elements.stream()
//                .filter(e -> e.row == row && e.col == col)
//                .map(e -> e.value)
//                .findFirst()
//                .orElse(0);
//    }
//
//    public SparseMatrix add(SparseMatrix other) {
//        if (this.rows != other.rows || this.cols != other.cols) {
//            throw new IllegalArgumentException("Matrices must have the same dimensions");
//        }
//
//        ArrayList<Element> newElements = new ArrayList<>();
//
//        int i = 0;
//        int j = 0;
//
//        while (i < this.numberOfElements && j < other.numberOfElements) {
//            Element e1 = this.elements.get(i);
//            Element e2 = other.elements.get(j);
//
//            if (e1.row == e2.row && e1.col == e2.col) {
//                newElements.add(new Element(e1.row, e1.col, e1.value + e2.value));
//                i++;
//                j++;
//            } else if (e1.row < e2.row || (e1.row == e2.col && e1.col < e2.col)) {
//                newElements.add(e1);
//                i++;
//            } else {
//                newElements.add(e2);
//                j++;
//            }
//        }
//
//        while (i < this.numberOfElements) {
//            newElements.add(this.elements.get(i));
//            i++;
//        }
//
//        while (j < other.numberOfElements) {
//            newElements.add(other.elements.get(j));
//            j++;
//        }
//
//        return new SparseMatrix(this.rows, this.cols, newElements);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//
//        for (int i = 1; i <= this.rows; i++) {
//            for (int j = 1; j <= this.cols; j++) {
//                s.append(this.get(i, j));
//                s.append(" ");
//            }
//            s.append("\n");
//        }
//
//        return s.toString();
//    }
}
