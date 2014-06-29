package net.bohush.exercises.chapter23;

import java.io.*;
import java.util.*;

public class Exercise10 {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a Java source file: ");
		String filename = input.nextLine();

		File file = new File(filename);

		highlighte(file);
	}

	public static void highlighte(File inputFile) throws Exception {
		int result = 0;
		HashMap<String, Integer> map = new HashMap<>();
		
		DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
		int size = input.available();
		byte[] b = new byte[size];
		input.read(b);
		input.close();

		StringBuilder javaFile = new StringBuilder(new String(b));
		replaceInString(javaFile, "&", "&amp;");
		replaceInString(javaFile, "<", "&lt;");
		replaceInString(javaFile, ">", "&gt;");
		
		int pos = 0;
		int work = 0;
		
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
		
		while(pos < javaFile.length()) {
			char ch = javaFile.charAt(pos);
			if(work == 0) {
				if(ch == '\"') {
					work = 1;
				} else if(ch == '\'') {
					work = 2;
				} else if((ch == '/')&&(javaFile.charAt(pos + 1) == '/')) {
					work = 4;
				} else if((ch == '/')&&(javaFile.charAt(pos + 1) == '*')) {
					work = 5;
				} else {
					for (int i = 0; i < keywordString.length; i++) {
						String word = keywordString[i];
						boolean isWord = true;
						for (int j = 0; j < word.length(); j++) {
							if(javaFile.charAt(pos + j) != word.charAt(j)) {
								isWord = false;
								break;
							}
						}
						if((isWord)&&(!Character.isAlphabetic(javaFile.charAt(pos + word.length())))&&((pos == 0 ) || (!Character.isAlphabetic(javaFile.charAt(pos - 1))))) {
							result++;
							if (keywordSet.contains(word)) {
								if (!map.containsKey(word)) {
									map.put(word, 1);
								} else {
									map.put(word, map.get(word) + 1);
								}
							}
							break;
						}
					}
				}
			} else if (work == 1) {
				if(ch == '\\') {
					pos++;
				} else if (ch == '\"') {
					work = 0;
				}
			} else if (work == 2) {
				if(ch == '\\') {
					pos++;
				} else if (ch == '\'') {
					work = 0;
				}
			} else if (work == 4) {
				if(ch == '\n') {
					work = 0;
				}
			} else if (work == 5) {
				if ((ch == '*') && (javaFile.charAt(pos + 1) == '/')) {
					work = 0;
				}
			}
			pos++;
		}

	    Set<Map.Entry<String, Integer>> entrySet = map.entrySet();

	    for (Map.Entry<String, Integer> entry: entrySet)
	      System.out.println(entry.getKey() + "\t" + entry.getValue());
	    
	    System.out.println(result);
	}
	
	public static void replaceInString(StringBuilder allFile, String beginStr, String replaceTo) {		
		int lastPos = 0;
		while (true) {
			int begin = allFile.indexOf(beginStr, lastPos);
			if(begin == -1) {
				break;
			}
			allFile.replace(begin, begin + beginStr.length(), replaceTo);
			lastPos = begin + replaceTo.length();
		}
	}
}