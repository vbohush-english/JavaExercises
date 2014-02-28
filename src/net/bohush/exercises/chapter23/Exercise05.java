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
		replaceInString(javaFile, "&", "", "&amp;");
		replaceInString(javaFile, "\\\"", "", "&#92;&quot;");
		replaceInString(javaFile, "<", "", "&lt;");
		replaceInString(javaFile, ">", "", "&gt;");
		
		sorraundInString(javaFile, "\"", "\"", "<span class = literal>&quot;", "&quot;</span>");
		//sorraundInString(javaFile, "'", "'", "<span class = \"literal\">&#39;", "&#39;</span>");
		//sorraundInString(javaFile, "/*", "*/", "<span class = \"comment\">/*", "*/</span>");
		
		StringBuilder htmlText = new StringBuilder("<html>");
		htmlText.append("<head>");
		htmlText.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">");
		htmlText.append("<style type = \"text/css\">");
		htmlText.append("body {font-family: \"Courier New\", sans-serif; font-size: 100%; color: black}");
		htmlText.append(".keyword {color: #000080; font-weight: bold}");
		htmlText.append(".comment {color: gray}");
		htmlText.append(".literal {font-weight: bold; color: #3366FF}");
		htmlText.append("</style>");
		htmlText.append("</head>");
		htmlText.append("<body>");
		htmlText.append("<pre>\n");
		htmlText.append(javaFile);
		htmlText.append("</pre></body></html>");
		
		DataOutputStream output = new DataOutputStream(new FileOutputStream(outputFile));
		output.writeBytes(htmlText.toString());
		output.close();
	}
	
	public static void sorraundInString(StringBuilder allFile, String beginStr, String endStr, String replaceTo1, String replaceTo2) {		
		int lastPos = 0;
		while (true) {
			int begin = allFile.indexOf(beginStr, lastPos);
			if(begin == -1) {
				break;
			}
			allFile.replace(begin, begin + beginStr.length(), replaceTo1);
			
			int end = allFile.indexOf(endStr, begin + replaceTo1.length());
			if(end == -1) {
				break;
			}	
			allFile.replace(end, end + endStr.length(), replaceTo2);
			
			lastPos = end + replaceTo2.length();
		}
	}
	
	public static void replaceInString(StringBuilder allFile, String beginStr, String endStr, String replaceTo) {		
		int lastPos = 0;
		while (true) {
			int begin = allFile.indexOf(beginStr, lastPos);
			int end = allFile.indexOf(endStr, begin + beginStr.length());
			if((begin == -1) || (end == -1)) {
				break;
			}
			allFile.replace(begin, end + endStr.length(), replaceTo);
			lastPos = begin + replaceTo.length();
		}
	}
}