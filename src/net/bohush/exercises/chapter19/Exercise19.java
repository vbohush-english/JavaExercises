package net.bohush.exercises.chapter19;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Exercise19 {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner inputFiles = new Scanner(System.in);
		System.out.print("Enter file name: ");
		String sourceFileName = inputFiles.next();

		File sourceFile = new File(sourceFileName);
		if (!sourceFile.exists()) {
			System.out.println("Source file " + sourceFileName + " does not exist");
			System.exit(2);
		}

		BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile));
		
		int r = 0;
		int count = 0;
		while ((r = input.read()) != -1) {
			System.out.print(getHex(r) + " ");
			if ((++count % 16) == 0) {
				System.out.println();	
			}
		}
		input.close();

	}
	
	public static String getHex(int value) {
		String hesInteger = "";
		while (value > 0) {
			if ((value % 16) < 10) {
				hesInteger = value % 16 + hesInteger;	
			} else {
				hesInteger = (char)('A' + (value % 16 - 10)) + hesInteger;
			}
			value = value / 16;
		}
		if (hesInteger.length() == 1) {
			hesInteger = '0' + hesInteger;
		}
		return hesInteger;
	}
	
}
