package alg4.com.ch0101;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by JianZhang on 5/21/17.
 */
public class Ex010216 {
    public static void main(String[] args) {
        Rational r1 = new Rational(-111, 222);
        Rational r2 = new Rational(333, 222);
        StdOut.println(r1.plus(r2));
        StdOut.println(r1.minus(r2));
        StdOut.println(r1.times(r2));
        StdOut.println(r1.divides(r2));
        StdOut.println(r1.equals(r2));
    }
}

class Rational {
    public long numerator;
    public long denominator;


    public Rational(int numerator, int denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException("invalid denominator: [" + numerator + ", " + denominator + "]");

        if (numerator < 0) {
            if (denominator < 0) {
                numerator *= -1;
                denominator *= -1;
            }
        } else if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        long gcd = gcd(numerator, denominator);
        gcd = gcd < 0 ? gcd * (-1) : gcd;
        //使用欧几里得算法,化简数字
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
//        this = builder(numerator / gcd, denominator / gcd);
    }

    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static Rational builder(long numerator, long denominator) {
        Rational result = new Rational(1, 1);
        long gcd = gcd(numerator, denominator);
        result.denominator = denominator / gcd;
        result.numerator = numerator / gcd;
        return result;
    }

    public Rational plus(Rational that) {
        long numeratorSum = this.numerator * that.denominator + this.denominator + that.numerator;
        long newDenominator = this.denominator * that.denominator;
        return Rational.builder(numeratorSum, newDenominator);
    }

    public Rational minus(Rational that) {
        long numeratorSum = this.numerator * that.denominator - this.denominator + that.numerator;
        long newDenominator = this.denominator * that.denominator;


        return Rational.builder(numeratorSum, newDenominator);
    }

    public Rational times(Rational that) {
        long newNumerator = this.numerator * that.numerator;
        long newDenominator = this.denominator * that.denominator;
        return Rational.builder(newNumerator, newDenominator);
    }

    public Rational divides(Rational that) {
        long newNumerator = this.numerator * that.denominator;
        long newDenominator = this.denominator * that.numerator;
        return Rational.builder(newNumerator, newDenominator);
    }

    /*
    * 1. reference
    * 2. null?
    * */
    public boolean equals(Rational that) {
        if (this == that) return true;
        if (that == null) return false;
        return false;
    }

    public String toString() {
        return "numerator: " + this.numerator + " denominator: " + this.denominator;
    }

}
