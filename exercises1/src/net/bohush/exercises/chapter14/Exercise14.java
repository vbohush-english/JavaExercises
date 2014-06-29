package net.bohush.exercises.chapter14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise14 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Enter file name: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String fileName = input.nextLine();
		Scanner inputFile = new Scanner(new File(fileName));
		int sum = 0;
		int count = 0;
		while (inputFile.hasNextInt()) {
			sum += inputFile.nextInt();
			count++;
		}
		inputFile.close();
		System.out.println("Total: " + sum);
		System.out.println("Count: " + count);
		System.out.println("Average: " + sum / (double)count);
	}

}