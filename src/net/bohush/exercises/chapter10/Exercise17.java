package net.bohush.exercises.chapter10;

import java.math.BigInteger;

public class Exercise17 {

	public static void main(String[] args) {
		BigInteger firstNumber = new BigInteger(((long)(Math.sqrt(Long.MAX_VALUE)) + 1) + "");
		for (int i = 0; i < 10; i++) {
			System.out.println(firstNumber + " ^ 2 = " + firstNumber.multiply(firstNumber));
			firstNumber = firstNumber.add(BigInteger.ONE);
		}
	}

}
