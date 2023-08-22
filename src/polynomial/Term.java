package polynomial;

public class Term {
    int coef;
    int exp;

    public Term(int coef, int exp) {
        this.coef = coef;
        this.exp = exp;
    }


    public int evaluate(int x) {
        return coef * (int) Math.pow(x, exp);
    }

    public Term add(Term oth) {
        if (this.exp != oth.exp) {
            throw new IllegalArgumentException("Exponents must be equal");
        }

        return new Term(this.coef + oth.coef, this.exp);
    }


    @Override
    public String toString() {
        if (this.exp == 0) {
            return "" + coef;
        }

        if (this.exp == 1) {
            return coef + "x";
        }

        return coef + "x^" + exp;
    }
}
