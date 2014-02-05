package net.bohush.exercises.chapter19;

import java.io.*;
import java.util.Scanner;

public class Exercise15 {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner inputFiles = new Scanner(System.in);
		System.out.print("Enter source file name: ");
		String sourceFileName = inputFiles.next();
		System.out.print("Enter target file name: ");
		String tagetFileName = inputFiles.next();
		

		File sourceFile = new File(sourceFileName);
		if (!sourceFile.exists()) {
			System.out.println("Source file " + sourceFileName + " does not exist");
			System.exit(2);
		}

		File targetFile = new File(tagetFileName);
		if (targetFile.exists()) {
			System.out.println("Target file " + tagetFileName + " already exists");
			System.exit(3);
		}

		BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile));
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile));

		int r, numberOfBytesCopied = 0;
		while ((r = input.read()) != -1) {
			output.write((byte) (r - 5));
			numberOfBytesCopied++;
		}

		input.close();
		output.close();

		System.out.println(numberOfBytesCopied + " bytes decrypted");
	}

}
