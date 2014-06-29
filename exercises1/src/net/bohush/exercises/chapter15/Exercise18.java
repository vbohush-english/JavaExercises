package net.bohush.exercises.chapter15;

public class Exercise18 {

	/** Main method */
	public static void main(String[] args) {
		// Check number of strings passed
		if (args.length != 1) {
			System.out.println("Usage: java Calculator \"operand1 operator operand2\"");
			System.exit(0);
		}

		// The result of the operation
		Rational18 result = null;

		// The result of the operation
		String[] tokens = args[0].split(" ");
		
		String[] number1 = tokens[0].split("/");
		String[] number2 = tokens[2].split("/");
		
		Rational18 n1 = new Rational18(Long.parseLong(number1[0]), Long.parseLong(number1[1]));
		Rational18 n2 = new Rational18(Long.parseLong(number2[0]), Long.parseLong(number2[1]));

		// Determine the operator
		switch (tokens[1].charAt(0)) {
		case '+': result = n1.add(n2); break;
		case '-': result = n1.subtract(n2); break;
		case '*': result = n1.multiply(n2); break;
		case '/': result = n1.divide(n2);
		}

		// Display result
		System.out.println(tokens[0] + ' ' + tokens[1] + ' ' + tokens[2] + " = " + result);
	}

}


class Rational18 extends Number implements Comparable<Rational18> {

	private static final long serialVersionUID = 1L;
	// Data fields for numerator and denominator
	private long[] r = new long[]{0, 1};
	
	/** Construct a rational with default properties */
	public Rational18() {
		this(0, 1);
	}

	/** Construct a rational with specified numerator and denominator */
	public Rational18(long numerator, long denominator) {
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
	public Rational18 add(Rational18 secondRational) {
		long n = r[0] * secondRational.getDenominator() + r[1] * secondRational.getNumerator();
		long d = r[1] * secondRational.getDenominator();
		return new Rational18(n, d);
	}

	/** Subtract a rational number from this rational */
	public Rational18 subtract(Rational18 secondRational) {
		long n = r[0] * secondRational.getDenominator() - r[1] * secondRational.getNumerator();
		long d = r[1] * secondRational.getDenominator();
		return new Rational18(n, d);
	}

	/** Multiply a rational number to this rational */
	public Rational18 multiply(Rational18 secondRational) {
		long n = r[0] * secondRational.getNumerator();
		long d = r[1] * secondRational.getDenominator();
		return new Rational18(n, d);
	}

	/** Divide a rational number from this rational */
	public Rational18 divide(Rational18 secondRational) {
		long n = r[0] * secondRational.getDenominator();
		long d = r[1] * secondRational.r[0];
		return new Rational18(n, d);
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
		if ((this.subtract((Rational18) (other))).getNumerator() == 0)
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
	public int compareTo(Rational18 o) {
		if (this.subtract(o).getNumerator() > 0)
			return 1;
		else if (this.subtract(o).getNumerator() < 0)
			return -1;
		else
			return 0;
	}
}
