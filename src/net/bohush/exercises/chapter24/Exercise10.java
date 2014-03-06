package net.bohush.exercises.chapter24;

import java.io.*;

public class Exercise10 {

	public static void main(String[] args) throws IOException {
		DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("tmp/PrimeNumbers.dat")));
		long result = 0;
		for (long i = 10; i < 10000000000L; i *= 10) {
			long number = 0;
			try {
				while((number = input.readLong()) < i)
					result++;
				System.out.println(i + " - " + result);
				result++;
			} catch (EOFException e) {
				System.out.println(number + " - " + result);
			}

		}
		input.close();			
	}
}