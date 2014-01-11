package net.bohush.exercises.chapter14;

import java.io.File;

public class Exercise29 {
	public static void main(String[] args) throws Exception {

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("Exercise") && args[i].endsWith("java")) {
				if (Character.isDigit(args[i].charAt(10)) && (args[i].charAt(9) == '_') && (!Character.isDigit(args[i].charAt(11)))) {
					StringBuilder targetFileName = new StringBuilder(args[i]);
					targetFileName.insert(10, '0');
					File sourceFile = new File(args[i]);
					File targetFile = new File(targetFileName.toString());
					sourceFile.renameTo(targetFile);
				}
				if (Character.isDigit(args[i].charAt(11)) && (args[i].charAt(10) == '_') && (!Character.isDigit(args[i].charAt(12)))) {
					StringBuilder targetFileName = new StringBuilder(args[i]);
					targetFileName.insert(11, '0');
					File sourceFile = new File(args[i]);
					File targetFile = new File(targetFileName.toString());
					sourceFile.renameTo(targetFile);
				}
			}
		}
	}
}