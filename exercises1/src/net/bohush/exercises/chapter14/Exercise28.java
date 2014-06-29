package net.bohush.exercises.chapter14;

import java.io.File;

public class Exercise28 {
	public static void main(String[] args) throws Exception {

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("Exercise") && args[i].endsWith("java")) {
				if (Character.isDigit(args[i].charAt(8)) && (args[i].charAt(9) == '_')) {
					StringBuilder targetFileName = new StringBuilder(args[i]);
					targetFileName.insert(8, '0');
					File sourceFile = new File(args[i]);
					File targetFile = new File(targetFileName.toString());
					sourceFile.renameTo(targetFile);
				}
			}
		}
	}
}