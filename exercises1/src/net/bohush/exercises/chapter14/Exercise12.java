package net.bohush.exercises.chapter14;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise12 {

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Execute: java Exercise12 filename");
			System.exit(1);
		}
		
		String fileName = args[0];

		File file = new File(fileName);
		
		if (!file.exists()) {
			System.out.println("File " + fileName + " not found");
			System.exit(2);
		}

		Scanner input = new Scanner(file);
		
		File tmpFile = new File(fileName + ".tmp");
		PrintWriter output = new PrintWriter(tmpFile);
		
		while (input.hasNext()) {
			String tmp = input.nextLine();
			String trimmedTmp = tmp.trim();
			if (trimmedTmp.charAt(0) == '{') {
				output.print(" " + trimmedTmp);
			} else {
				output.println();
				output.print(tmp);
			}
		}
		
		input.close();
		output.close();
		
		if (file.delete()) {
			tmpFile.renameTo(file);
		} else {
			System.out.println("Error writing file");
			System.exit(3);
		}

	}

}
