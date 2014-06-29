package net.bohush.exercises.chapter10;

import java.math.BigInteger;

public class Exercise19 {

	public static void main(String[] args) {
		System.out.println("p\t2^p-1\n");
		BigInteger number = new BigInteger("4");
		for (int p = 2; p <= 100; p++) {
			System.out.println(p + "\t" + number.subtract(BigInteger.ONE));	
			number = number.multiply(new BigInteger("2"));
		}
	}

}
