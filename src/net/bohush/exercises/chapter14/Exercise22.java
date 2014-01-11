package net.bohush.exercises.chapter14;

import java.io.*;
import java.util.*;

public class Exercise22 {
	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.out.println("Usage: java Exercise16 dir oldString newString");
			System.exit(1);
		}

		String sourceDirName = args[0];
		File sourceDir = new File(sourceDirName);
		File[] files = sourceDir.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			File sourceFile = files[i];
	
			String targetFileName = sourceFile.getAbsolutePath() + ".tmp";
			File targetFile = new File(targetFileName);
	
			Scanner input = new Scanner(sourceFile);
			PrintWriter output = new PrintWriter(targetFile);
	
			while (input.hasNext()) {
				String s1 = input.nextLine();
				String s2 = s1.replaceAll(args[1], args[2]);
				output.println(s2);
			}
	
			input.close();
			output.close();
			
			if (sourceFile.delete()) {
				targetFile.renameTo(sourceFile);
			} else {
				System.out.println("Error writing file");
				System.exit(3);
			}
		}
	}
}