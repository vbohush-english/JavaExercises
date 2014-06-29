package net.bohush.exercises.chapter23;

import java.util.*;
import java.io.*;

public class Exercise03 {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a Java source file: ");
		String filename = input.nextLine();

		File file = new File(filename);
		if (file.exists()) {
			System.out.println("The number of keywords in " + filename + " is "	+ countKeywords(file));
		} else {
			System.out.println("File " + filename + " does not exist");
		}
	}

	public static void replaceInString(StringBuilder allFile, String beginStr, String endStr) {		
		int lastPos = 0;
		while (true) {
			int begin = allFile.indexOf(beginStr, lastPos);
			int end = allFile.indexOf(endStr, begin + beginStr.length());
			if((begin == -1) || (end == -1)) {
				break;
			}
			allFile.replace(begin, end + endStr.length(), "");
			lastPos = begin;
		}
	}
	
	public static int countKeywords(File file) throws Exception {

		DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		int size = input.available();
		byte[] b = new byte[size];
		input.read(b);
		input.close();

		StringBuilder allFile = new StringBuilder(new String(b));

		replaceInString(allFile, "\\\"", "");
		replaceInString(allFile, "\"", "\"");
		replaceInString(allFile, "/*", "*/");
		replaceInString(allFile, "//", "\n");
		
		String[] keywordString = { "abstract", "assert", "boolean", "break",
				"byte", "case", "catch", "char", "class", "const", "continue",
				"default", "do", "double", "else", "enum", "extends", "for",
				"final", "finally", "float", "goto", "if", "implements",
				"import", "instanceof", "int", "interface", "long", "native",
				"new", "package", "private", "protected", "public", "return",
				"short", "static", "strictfp", "super", "switch",
				"synchronized", "this", "throw", "throws", "transient", "try",
				"void", "volatile", "while", "true", "false", "null" };

		Set<String> keywordSet = new HashSet<String>(Arrays.asList(keywordString));
		int count = 0;

		@SuppressWarnings("resource")
		Scanner input2 = new Scanner(allFile.toString());

		while (input2.hasNext()) {
			String word = input2.next();
			if (keywordSet.contains(word))
				count++;
		}

		return count;
	}
}