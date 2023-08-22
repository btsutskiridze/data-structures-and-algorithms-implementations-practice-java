package matrix;

import java.util.ArrayList;

public class SparseMatrix {

    public static void main(String[] args) {
        SparseMatrix sp = new SparseMatrix(4, 5, 5);
        sp.set(1, 1, 1);
        sp.set(1, 4, 2);
        sp.set(4, 3, 3);
        sp.set(2, 4, 4);
        sp.set(3, 5, 5);

        SparseMatrix sp1 = new SparseMatrix(4, 5, 5);
        sp1.set(1, 1, 1);
        sp1.set(1, 4, 2);
        sp1.set(4, 3, 3);
        sp1.set(2, 4, 4);
        sp1.set(3, 4, 5);

        SparseMatrix sp2 = sp.add(sp1);

        System.out.println(sp);
        System.out.println(sp1);
        System.out.println(sp2);

    }

    private class Element {
        int row;
        int col;
        int value;

        private Element(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    private final ArrayList<Element> elements;
    private final int rows;
    private final int cols;
    private final int numberOfElements;

    public SparseMatrix(int rows, int cols, int numberOfElements) {
        this.rows = rows;
        this.cols = cols;
        this.numberOfElements = numberOfElements;
        this.elements = new ArrayList<>(numberOfElements);
    }

    private SparseMatrix(int rows, int cols, ArrayList<Element> elements) {
        this.rows = rows;
        this.cols = cols;
        this.elements = elements;
        this.numberOfElements = elements.size();
    }

    public void set(int row, int col, int value) {
        if (row < 1 || row > this.rows || col < 1 || col > this.cols) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (value != 0) {
            this.elements.add(new Element(row, col, value));
        }
    }

    public int get(int row, int col) {
        if (row < 1 || row > this.rows || col < 1 || col > this.cols) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return this.elements.stream()
                .filter(e -> e.row == row && e.col == col)
                .map(e -> e.value)
                .findFirst()
                .orElse(0);
    }

    public SparseMatrix add(SparseMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }

        ArrayList<Element> newElements = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < this.numberOfElements && j < other.numberOfElements) {
            Element e1 = this.elements.get(i);
            Element e2 = other.elements.get(j);

            if (e1.row == e2.row && e1.col == e2.col) {
                newElements.add(new Element(e1.row, e1.col, e1.value + e2.value));
                i++;
                j++;
            } else if (e1.row < e2.row || (e1.row == e2.col && e1.col < e2.col)) {
                newElements.add(e1);
                i++;
            } else {
                newElements.add(e2);
                j++;
            }
        }

        while (i < this.numberOfElements) {
            newElements.add(this.elements.get(i));
            i++;
        }

        while (j < other.numberOfElements) {
            newElements.add(other.elements.get(j));
            j++;
        }

        return new SparseMatrix(this.rows, this.cols, newElements);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 1; i <= this.rows; i++) {
            for (int j = 1; j <= this.cols; j++) {
                s.append(this.get(i, j));
                s.append(" ");
            }
            s.append("\n");
        }

        return s.toString();
    }


}
