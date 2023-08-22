package polynomial;

import java.util.Arrays;

public class Polynomial {

    public static void main(String[] args) {
        Polynomial p = new Polynomial(3);
        p.setTerm(1, 2, 2);
        p.setTerm(2, 4, 1);
        p.setTerm(3, 5, 0);

        Polynomial p1 = new Polynomial(7);
        p1.setTerm(1, 3, 3);
        p1.setTerm(2, 5, 2);
        p1.setTerm(3, 6, 1);
        p1.setTerm(4, 7, 0);
        p1.setTerm(5, 8, 0);
        p1.setTerm(6, 9, 0);
        p1.setTerm(7, 10, 0);


        System.out.println("P(x) = " + p);
        System.out.println("P1(x) = " + p1);
        System.out.println("P(x) + P1(x) = " + p.add(p1));

    }

    private final int numberOfTerms;

    private int count = 0;

    private final Term[] terms;

    public Polynomial(int numberOfTerms) {
        this.numberOfTerms = numberOfTerms;
        this.terms = new Term[numberOfTerms];
    }

    private Polynomial(int numberOfTerms, Term[] terms) {
        this.numberOfTerms = numberOfTerms;
        this.count = numberOfTerms;
        this.terms = terms;

    }

    public void setTerm(int position, int coef, int exp) {
        if (position <= 0 && position > this.numberOfTerms) {
            throw new IllegalArgumentException("Invalid position");
        }

        if (coef == 0) {
            throw new IllegalArgumentException("Coefficient cannot be 0");
        }

        this.terms[position - 1] = new Term(coef, exp);
        this.count++;
    }

    public int evaluate(int x) {
        int result = 0;

        for (int i = 0; i < numberOfTerms; i++) {
            result += terms[i].evaluate(x);
        }

        return result;
    }

    public Polynomial add(Polynomial oth) {
        Term[] result = new Term[this.numberOfTerms + oth.numberOfTerms];

        int i = 0, j = 0, k = 0;

        while (i < this.numberOfTerms && j < oth.numberOfTerms) {
            if (this.terms[i].exp > oth.terms[j].exp) {
                result[k++] = this.terms[i++];
            } else if (this.terms[i].exp < oth.terms[j].exp) {
                result[k++] = oth.terms[j++];
            } else {
                result[k++] = this.terms[i++].add(oth.terms[j++]);
            }
        }

        while (i < this.numberOfTerms) {
            result[k++] = this.terms[i++];
        }

        while (j < oth.numberOfTerms) {
            result[k++] = oth.terms[j++];
        }

        return new Polynomial(k, Arrays.copyOf(result, k));
    }

    @Override
    public String toString() {
        if (this.count != this.numberOfTerms) {
            throw new IllegalArgumentException("Invalid number of terms, expected " + this.numberOfTerms + " but got " + this.count);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.numberOfTerms; i++) {
            sb.append(terms[i]);

            if (i != this.numberOfTerms - 1) {
                sb.append(" + ");
            }
        }

        System.out.println(terms.length);
        System.out.println(numberOfTerms);

        return sb.toString();
    }

}
