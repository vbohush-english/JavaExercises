package net.bohush.exercises.chapter14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise20 {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.out.println("Execute: java Exercise18 srcRootDirectory");
			System.exit(1);
		}
		
		for (int i = 1; i <= 34; i++) {
			File directory = new File(args[0] + "/chapter" + i);
			if (directory.exists() && directory.isDirectory()) {
				File[] fileList = directory.listFiles();
				for (int j = 0; j < fileList.length; j++) {	
					if (fileList[j].getName().lastIndexOf(".java") == fileList[j].getName().length() - 5) {
						removePackageStatement(fileList[j], "chapter" + i);						
					}
				}
			}			
		}
	}
	
	public static void removePackageStatement(File file, String packageName) throws FileNotFoundException {

		String tmpFileName = file.getName() + ".tmp";
		File tmpFile = new File(tmpFileName);

		Scanner input = new Scanner(file);
		PrintWriter output = new PrintWriter(tmpFile);

		String firstLine = input.nextLine();
		if (!firstLine.equals("package " + packageName + ";")) {
			output.println(firstLine);
		}
		while (input.hasNext()) {
				output.println(input.nextLine());
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
