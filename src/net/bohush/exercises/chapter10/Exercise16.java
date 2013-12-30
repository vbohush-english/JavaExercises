package net.bohush.exercises.chapter10;

import java.math.BigInteger;

public class Exercise16 {

	public static void main(String[] args) {
		BigInteger number = BigInteger.TEN;
		number = number.pow(49);
		int count = 0;
		while (count < 10) {
			if (number.remainder(new BigInteger("2")).equals(BigInteger.ZERO)) {
				System.out.println(number);
				count++;
			} else	if (number.remainder(new BigInteger("3")).equals(BigInteger.ZERO)) {
				System.out.println(number);
				count++;
			}
			number = number.add(BigInteger.ONE);
		}
	}

}
