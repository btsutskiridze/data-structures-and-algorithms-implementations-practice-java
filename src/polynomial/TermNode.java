package polynomial;

public class TermNode {
    int coef;
    int exp;
    TermNode next;

    public TermNode(int coef, int exp) {
        this.coef = coef;
        this.exp = exp;
        this.next = null;
    }

    public TermNode(int coef, int exp, TermNode next) {
        this.coef = coef;
        this.exp = exp;
        this.next = next;
    }

    public int evaluate(int x) {
        return this.coef * (int) Math.pow(x, this.exp);
    }
    
    @Override
    public String toString() {
        if (this.exp == 0) {
            return "" + this.coef;
        }

        if (this.exp == 1) {
            return this.coef + "x";
        }

        return this.coef + "x^" + this.exp;
    }
}
