package net.bohush.exercises.chapter15;

public class Exercise16 {

	public static void main(String[] args) {
		// Create and initialize two rational numbers r1 and r2.
		Rational16 r1 = new Rational16(4, 2);
		Rational16 r2 = new Rational16(2, 3);

		// Display results
		System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
		System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
		System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
		System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
		System.out.println(r2 + " is " + r2.doubleValue());
	}

}

class Rational16 extends Number implements Comparable<Rational16> {

	private static final long serialVersionUID = 1L;
	// Data fields for numerator and denominator
	private long[] r = new long[]{0, 1};
	
	/** Construct a rational with default properties */
	public Rational16() {
		this(0, 1);
	}

	/** Construct a rational with specified numerator and denominator */
	public Rational16(long numerator, long denominator) {
		long gcd = gcd(numerator, denominator);
		this.r[0] = ((denominator > 0) ? 1 : -1) * numerator / gcd;
		this.r[1] = Math.abs(denominator) / gcd;
	}

	/** Find GCD of two numbers */
	private static long gcd(long n, long d) {
		long n1 = Math.abs(n);
		long n2 = Math.abs(d);
		int gcd = 1;

		for (int k = 1; k <= n1 && k <= n2; k++) {
			if (n1 % k == 0 && n2 % k == 0)
				gcd = k;
		}

		return gcd;
	}

	/** Return numerator */
	public long getNumerator() {
		return r[0];
	}

	/** Return denominator */
	public long getDenominator() {
		return r[1];
	}

	/** Add a rational number to this rational */
	public Rational16 add(Rational16 secondRational) {
		long n = r[0] * secondRational.getDenominator() + r[1] * secondRational.getNumerator();
		long d = r[1] * secondRational.getDenominator();
		return new Rational16(n, d);
	}

	/** Subtract a rational number from this rational */
	public Rational16 subtract(Rational16 secondRational) {
		long n = r[0] * secondRational.getDenominator() - r[1] * secondRational.getNumerator();
		long d = r[1] * secondRational.getDenominator();
		return new Rational16(n, d);
	}

	/** Multiply a rational number to this rational */
	public Rational16 multiply(Rational16 secondRational) {
		long n = r[0] * secondRational.getNumerator();
		long d = r[1] * secondRational.getDenominator();
		return new Rational16(n, d);
	}

	/** Divide a rational number from this rational */
	public Rational16 divide(Rational16 secondRational) {
		long n = r[0] * secondRational.getDenominator();
		long d = r[1] * secondRational.r[0];
		return new Rational16(n, d);
	}

	@Override
	public String toString() {
		if (r[1] == 1)
			return r[0] + "";
		else
			return r[0] + "/" + r[1];
	}

	@Override
	// Override the equals method in the Object class
	public boolean equals(Object other) {
		if ((this.subtract((Rational16) (other))).getNumerator() == 0)
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
		return r[0] * 1.0 / r[1];
	}

	@Override
	// Implement the abstract longValue method in Number
	public long longValue() {
		return (long) doubleValue();
	}

	@Override
	// Implement the compareTo method in Comparable
	public int compareTo(Rational16 o) {
		if (this.subtract(o).getNumerator() > 0)
			return 1;
		else if (this.subtract(o).getNumerator() < 0)
			return -1;
		else
			return 0;
	}
}