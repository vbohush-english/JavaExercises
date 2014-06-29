package net.bohush.exercises.chapter19;

import java.io.*;
import java.util.Scanner;

public class Exercise16 {

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
		
		int[] characters = new int[26];
		int r = 0;
		while ((r = input.read()) != -1) {
			char tmp = (char)r;
			tmp = Character.toLowerCase(tmp);
			if ((tmp >= 'a') && (tmp <= 'z')) {
				characters[tmp - 'a']++;
			}
		}
		input.close();
		
		for (int i = 0; i < characters.length; i++) {
			System.out.println((char)('a' + i) + " - " + characters[i]);
		}


	}

}
