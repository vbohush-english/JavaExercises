package net.bohush.exercises.chapter10;

import java.math.BigInteger;

public class Exercise21 {

	public static void main(String[] args) {
		BigInteger number = new BigInteger(Long.MAX_VALUE + "");
		int count = 0;
		while (count < 10) {
			if (number.remainder(new BigInteger("5")).equals(BigInteger.ZERO)) {
				System.out.println(number);
				count++;
			} else	if (number.remainder(new BigInteger("6")).equals(BigInteger.ZERO)) {
				System.out.println(number);
				count++;
			}
			number = number.add(BigInteger.ONE);
		}
	}


}
