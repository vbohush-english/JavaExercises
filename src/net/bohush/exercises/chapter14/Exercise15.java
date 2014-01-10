package net.bohush.exercises.chapter14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise15 {

	public static void main(String[] args) throws FileNotFoundException {
		final int SIZE = 100;
		
		File file = new File("tmp/Exercise14_15.txt");
		PrintWriter output = new PrintWriter(file);
		for (int i = 0; i < SIZE; i ++) {
			output.println((int)(Math.random() * Integer.MAX_VALUE));
		}
		output.close();
		
		Scanner input = new Scanner(file);
		int[] array = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			array[i] = input.nextInt();
		}
		input.close();
		
		java.util.Arrays.sort(array);
		
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

}

