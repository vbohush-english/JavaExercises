package net.bohush.exercises.chapter24;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Exercise08 {
	public static void main(String[] args) throws IOException {
		long n = 10_000_000_000L;
		
		RandomAccessFile input = new RandomAccessFile("tmp/PrimeNumbers.dat", "rw");

		java.util.List<Long> list = new java.util.ArrayList<>();

		long count = 0; // Count the number of prime numbers
		long number = 2; // A number to be tested for primeness
		int squareRoot = 1; // Check whether number <= squareRoot

		if(input.length() > 0) {
			input.seek(0);
			try {
				for (int i = 0; i < 10000; i++) {
					list.add(input.readLong());
				}			
			} catch (EOFException e) {
			}
			input.seek(input.length() - 8);
			number = input.readLong() + 1;
			
			
			squareRoot = (int)(Math.sqrt(number)) + 1;
			count = input.length() / 8;
		}
		

		
		// Repeatedly find prime numbers
		while (number <= n) {
			// Assume the number is prime
			boolean isPrime = true; // Is the current number prime?

			if (squareRoot * squareRoot < number)
				squareRoot++;

			 while(true) {
				// ClosestPair if number is prime
				for (int k = 0; k < list.size() && list.get(k) <= squareRoot; k++) {
					if (number % list.get(k) == 0) { // If true, not prime
						isPrime = false; // Set isPrime to false
						break; // Exit the for loop
					}
				}
				if ((isPrime)&&(input.length() < count)) {
					System.out.println("list.size() " + list.size());
					input.seek(list.size() * 8);
					list.clear();
					try {
						for (int i = 0; i < 10000; i++) {
							list.add(input.readLong());
						}			
					} catch (EOFException e) {
					}
					input.seek(input.length() - 8);			
				} else {
					break;
				}
			}
			
			if (isPrime) {
				count++; // Increase the count
				list.add(number);
				input.writeLong(number);
			}

			// Check if the next number is prime
			number++;
		}

		System.out.println("\n" + count + " prime(s) less than or equal to " + n);
		input.close();
	}
}