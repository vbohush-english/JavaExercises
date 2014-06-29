package net.bohush.exercises.chapter14;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise11 {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Execute: java Exercise11 word filename");
			System.exit(1);
		}
		
		String word = args[0];
		String fileName = args[1];

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
			tmp = tmp.replaceAll(word, "");
			output.println(tmp);
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
