package net.bohush.exercises.chapter24;

import java.io.*;

public class Exercise12 {

	public static void main(String[] args) throws IOException {
		DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("tmp/PrimeNumbers.dat")));
		int primeNumbers = 100;
		input.skipBytes(input.available() - 8 * primeNumbers);
		for (int i = 0; i < primeNumbers; i++) {
			System.out.println(input.readLong());
		}
		input.close();			
	}
}