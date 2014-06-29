package net.bohush.exercises.chapter19;

import java.io.*;

public class Exercise03 {
	public static void main(String[] args) throws IOException {
		int sum = 0;
		DataInputStream input = new DataInputStream(new FileInputStream("tmp/Exercise19_02.dat"));
		try {			
			while (true) {
				sum += input.readInt();
			}							
		} catch (EOFException e) {
		} finally {
			input.close();
		}
		System.out.println(sum);
	}
	
}
