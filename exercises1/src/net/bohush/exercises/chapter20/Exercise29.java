package net.bohush.exercises.chapter20;

import java.io.File;
import java.util.Scanner;

public class Exercise29 {
	public static void main(String[] args) {
		System.out.print("Enter a directory: ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String directory = input.nextLine();

		long time1 = System.currentTimeMillis();
		System.out.println(getNumberOfFiles(new File(directory)) + " files");
		long time2 = System.currentTimeMillis();
		System.out.println((time2 - time1) + " msec.");
	}

	public static long getNumberOfFiles(File file) {
		long numberOfFiles = 0;
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; files != null && i < files.length; i++) {
				numberOfFiles += getNumberOfFiles(files[i]);
			}
		} else {
			numberOfFiles++;
		}

		return numberOfFiles;
	}

}