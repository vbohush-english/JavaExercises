package net.bohush.exercises.chapter15;

import java.math.BigInteger;

public class Exercise17 {

	public static void main(String[] args) {
		// Create and initialize two rational numbers r1 and r2.
		Rational17 r1 = new Rational17(4, 2);
		Rational17 r2 = new Rational17(2, 3);

		// Display results
		System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
		System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
		System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
		System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
		System.out.println((r1.subtract(r2)).equals(r1.multiply(r2)));
		System.out.println(r2 + " is " + r2.doubleValue());
		
	}

}

class Rational17 extends Number implements Comparable<Rational17> {
	private static final long serialVersionUID = 1L;
	// Data fields for numerator and denominator
	BigInteger numerator = new BigInteger("0");
	BigInteger denominator = new BigInteger("1");

	/** Construct a rational with default properties */
	public Rational17() {
		this(0, 1);
	}

	/** Construct a rational with specified numerator and denominator */
	public Rational17(long numerator, long denominator) {
		this(new BigInteger(numerator + ""), new BigInteger(denominator + ""));
	}
	
	public Rational17(BigInteger numerator, BigInteger denominator) {
		BigInteger gcd = numerator.gcd(denominator);
		BigInteger tmp = new BigInteger(denominator.compareTo(new BigInteger("0")) > 0 ? "1" : "-1");
		this.numerator = tmp.multiply(numerator).divide(gcd);
		this.denominator = abs(denominator).divide(gcd);
	}
	
	public static BigInteger abs(BigInteger number) {
		if (number.compareTo(new BigInteger("0")) < 0) {
			return (new BigInteger("0")).subtract(number);
		} else {
			return number;
		}
	}

	/** Return numerator */
	public BigInteger getNumerator() {
		return numerator;
	}

	/** Return denominator */
	public BigInteger getDenominator() {
		return denominator;
	}

	/** Add a rational number to this rational */
	public Rational17 add(Rational17 secondRational) {
		BigInteger n = (numerator.multiply(secondRational.getDenominator())).add(denominator.multiply(secondRational.getNumerator()));
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational17(n, d);
	}

	/** Subtract a rational number from this rational */
	public Rational17 subtract(Rational17 secondRational) {
		BigInteger n = (numerator.multiply(secondRational.getDenominator())).subtract(denominator.multiply(secondRational.getNumerator()));
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational17(n, d);
	}

	/** Multiply a rational number to this rational */
	public Rational17 multiply(Rational17 secondRational) {
		BigInteger n = numerator.multiply(secondRational.getNumerator());
		BigInteger d = denominator.multiply(secondRational.getDenominator());
		return new Rational17(n, d);
	}

	/** Divide a rational number from this rational */
	public Rational17 divide(Rational17 secondRational) {
		BigInteger n = numerator.multiply(secondRational.getDenominator());
		BigInteger d = denominator.multiply(secondRational.numerator);
		return new Rational17(n, d);
	}

	@Override
	public String toString() {
		if (denominator.equals(new BigInteger("1")))
			return numerator + "";
		else
			return numerator + "/" + denominator;
	}

	@Override
	// Override the equals method in the Object class
	public boolean equals(Object other) {
		if ((this.subtract((Rational17) (other))).getNumerator().equals(new BigInteger("0")))
			return true;
		else
			return false;
	}

	@Override
	// Implement the abstract intValue method in Number
	public int intValue() {
		return (int) doubleValue();
	}

	@Override
	// Implement the abstract floatValue method in Number
	public float floatValue() {
		return (float) doubleValue();
	}

	@Override
	// Implement the doubleValue method in Number
	public double doubleValue() {
		return numerator.doubleValue() * 1.0 / denominator.doubleValue();
	}

	@Override
	// Implement the abstract longValue method in Number
	public long longValue() {
		return (long) doubleValue();
	}

	@Override
	// Implement the compareTo method in Comparable
	public int compareTo(Rational17 o) {
		BigInteger subtractNumber = this.subtract(o).getNumerator();
		return subtractNumber.compareTo(new BigInteger("0"));
	}
}