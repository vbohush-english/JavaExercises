package net.bohush.exercises.chapter14;

import java.util.Scanner;
import java.io.File;

public class Exercise26 {

	public static void main(String[] args) {
		System.out.print("Enter directory name: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String dirName = input.nextLine();
		File dir = new File(dirName);
		if (dir.exists()) {
			System.out.println("Directory already exists");	
		} else {
			dir.mkdir();
			System.out.println("Directory created successfully");
		}
	}

}
