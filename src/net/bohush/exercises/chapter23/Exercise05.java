package net.bohush.exercises.chapter23;

import java.io.*;

public class Exercise05 {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Usage: java Exercise23 file.java file.html");
			System.exit(0);
		}
		File inputFile = new File(args[0]);
		if (!inputFile.exists()) {
			System.out.println("File \"" + args[0] + "\" doesn't exist");
			System.exit(0);			
		}
		
		File outputFile = new File(args[1]);

		highlighte(inputFile, outputFile);
	}

	public static void highlighte(File inputFile, File outputFile) throws Exception {
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
		
		while(pos < javaFile.length()) {
			char ch = javaFile.charAt(pos);
			if(work == 0) {
				if(ch == '\"') {
					String tmp = "<span class = \"literal\">";
					javaFile.insert(pos, tmp);
					pos += tmp.length();
					work = 1;
				} else if(ch == '\'') {
					String tmp = "<span class = \"literal\">";
					javaFile.insert(pos, tmp);
					pos += tmp.length();
					work = 2;
				} else if(Character.isDigit(ch)) {
					if ((!Character.isAlphabetic(javaFile.charAt(pos - 1)))&&(!Character.isDigit(javaFile.charAt(pos - 1)))&&(javaFile.charAt(pos - 1) != '_')) {
						String tmp = "<span class = \"literal\">";
						javaFile.insert(pos, tmp);
						pos += tmp.length();
						work = 3;
					}
				} else if((ch == '/')&&(javaFile.charAt(pos + 1) == '/')) {
					String tmp = "<span class = \"comment\">";
					javaFile.insert(pos, tmp);
					pos += tmp.length();
					work = 4;
				} else if((ch == '/')&&(javaFile.charAt(pos + 1) == '*')) {
					String tmp = "<span class = \"comment\">";
					javaFile.insert(pos, tmp);
					pos += tmp.length();
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
							String tmp = "<span class = \"keyword\">";
							javaFile.insert(pos, tmp);
							pos += tmp.length();
							
							tmp = "</span>";
							javaFile.insert(pos + word.length(), tmp);
							pos += tmp.length();
							break;
						}
					}
				}
			} else if (work == 1) {
				if(ch == '\\') {
					pos++;
				} else if (ch == '\"') {
					String tmp = "</span>";
					javaFile.insert(pos + 1, tmp);
					pos += tmp.length() + 1; 
					work = 0;
				}
			} else if (work == 2) {
				if(ch == '\\') {
					pos++;
				} else if (ch == '\'') {
					String tmp = "</span>";
					javaFile.insert(pos + 1, tmp);
					pos += tmp.length() + 1; 
					work = 0;
				}
			} else if (work == 3) {
				if(!Character.isDigit(ch)) {
					String tmp = "</span>";
					javaFile.insert(pos, tmp);
					pos += tmp.length(); 
					work = 0;
				}
			} else if (work == 4) {
				if(ch == '\n') {
					String tmp = "</span>";
					javaFile.insert(pos, tmp);
					pos += tmp.length(); 
					work = 0;
				}
			} else if (work == 5) {
				if ((ch == '*') && (javaFile.charAt(pos + 1) == '/')) {
					String tmp = "</span>";
					pos += 2;
					javaFile.insert(pos, tmp);
					pos += tmp.length(); 
					work = 0;
				}
			}
			pos++;
		}
		
		StringBuilder htmlText = new StringBuilder("<html>");
		htmlText.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">");
		htmlText.append("<style type = \"text/css\">body {font-family: \"Courier New\", sans-serif; font-size: 100%; color: black}");
		htmlText.append(".keyword {color: NavyBlue; font-weight: bold}.comment {color: Green}.literal {color: Blue}</style></head><body><pre>\n");
		htmlText.append(javaFile);
		htmlText.append("</pre></body></html>");
		
		DataOutputStream output = new DataOutputStream(new FileOutputStream(outputFile));
		output.writeBytes(htmlText.toString());
		output.close();
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