package net.bohush.exercises.chapter10;

import java.math.BigInteger;

public class Exercise18 {

	public static void main(String[] args) {
		BigInteger firstNumber = new BigInteger(Long.MAX_VALUE + "");
		int count = 0;
		while (count < 10) {
			if (isPrime(firstNumber)) {
				System.out.println(firstNumber);
				count++;				
			}
			firstNumber = firstNumber.add(BigInteger.ONE);
		}
	}

	public static boolean isPrime(BigInteger number) {
		BigInteger divisor = new BigInteger("2");
		while ((divisor.compareTo(number.divide(new BigInteger("2"))) == -1) || (divisor.compareTo(number.divide(new BigInteger("2"))) == 0)) {
			if (number.remainder(divisor).equals(BigInteger.ZERO)) {
				return false;
			}
			divisor = divisor.add(BigInteger.ONE);
			
		}
		return true;
	}
}
