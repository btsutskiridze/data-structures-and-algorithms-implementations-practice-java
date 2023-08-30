package polynomial;

import java.util.Arrays;

public class Polynomial {

    public static void main(String[] args) {
        Polynomial p = new Polynomial();
        p.setTerm(2, 2);
        p.setTerm(4, 1);
        p.setTerm(5, 0);
        p.setTerm(6, 5);

        Polynomial q = new Polynomial();
        q.setTerm(3, 3);
        q.setTerm(5, 2);
        q.setTerm(6, 1);
        q.setTerm(7, 0);
        q.setTerm(8, 0);
        q.setTerm(9, 0);
        q.setTerm(10, 0);

        System.out.println("P(x) = " + p);
        System.out.println("Q(x) = " + q);
        p.add(q);
        System.out.println("P(x) + Q(x) = " + p);
//
    }

    private TermNode termsHead;

    public Polynomial() {
        this.termsHead = null;
    }

    public void setTerm(int coef, int exp) {
        this.validateCoef(coef);
        this.validateExp(exp);

        if (this.termsHead == null || this.termsHead.exp < exp) {
            this.termsHead = new TermNode(coef, exp, this.termsHead);
            return;
        }

        TermNode current = this.termsHead;
        TermNode prev = null;

        while (current != null && current.exp > exp) {
            prev = current;
            current = current.next;
        }

        if (current == null || current.exp < exp) {
            prev.next = new TermNode(coef, exp, current);
            return;
        }

        current.coef += coef;
    }

    public int evaluate(int x) {
        TermNode ptr = this.termsHead;

        int result = 0;

        while (ptr != null) {
            result += ptr.evaluate(x);
            ptr = ptr.next;
        }

        return result;
    }


    private void validateCoef(int coef) throws IllegalArgumentException {
        if (coef == 0) {
            throw new IllegalArgumentException("Coefficient cannot be 0");
        }
    }

    private void validateExp(int exp) throws IllegalArgumentException {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative");
        }
    }

    public void add(Polynomial othr) {
        if (this.termsHead == null || othr.termsHead == null) {
            throw new RuntimeException("Cannot add empty Polynomial");
        }

        TermNode ptr = this.termsHead;
        TermNode prev = null;
        TermNode othrPtr = othr.termsHead;

        while (ptr != null && othrPtr != null) {
            if (ptr.exp == othrPtr.exp) {
                ptr.coef += othrPtr.coef;
                prev = ptr;
                ptr = ptr.next;
                othrPtr = othrPtr.next;
            } else if (ptr.exp > othrPtr.exp) {
                prev = ptr;
                ptr = ptr.next;
            } else {
                TermNode newNode = new TermNode(othrPtr.coef, othrPtr.exp);
                newNode.next = ptr;
                if (prev == null) {
                    this.termsHead = newNode;
                } else {
                    prev.next = newNode;
                }
                prev = newNode;
                othrPtr = othrPtr.next;
            }
        }

        while (othrPtr != null) {
            TermNode newNode = new TermNode(othrPtr.coef, othrPtr.exp);
            prev.next = newNode;
            prev = newNode;
            othrPtr = othrPtr.next;
        }
    }

    @Override
    public String toString() {
        if (this.termsHead == null) {
            throw new RuntimeException("Polynomial is empty");
        }

        StringBuilder sb = new StringBuilder();
        TermNode ptr = this.termsHead;

        while (ptr != null) {
            sb.append(ptr);
            if (ptr.next != null) {
                sb.append(" + ");
            }
            ptr = ptr.next;
        }

        return sb.toString();
    }

//    public Polynomial add(Polynomial oth) {
//        TermNode[] result = new TermNode[this.numberOfTerms + oth.numberOfTerms];
//
//        int i = 0, j = 0, k = 0;
//
//        while (i < this.numberOfTerms && j < oth.numberOfTerms) {
//            if (this.terms[i].exp > oth.terms[j].exp) {
//                result[k++] = this.terms[i++];
//            } else if (this.terms[i].exp < oth.terms[j].exp) {
//                result[k++] = oth.terms[j++];
//            } else {
//                result[k++] = this.terms[i++].add(oth.terms[j++]);
//            }
//        }
//
//        while (i < this.numberOfTerms) {
//            result[k++] = this.terms[i++];
//        }
//
//        while (j < oth.numberOfTerms) {
//            result[k++] = oth.terms[j++];
//        }
//
//        return new Polynomial(k, Arrays.copyOf(result, k));
//    }

}
