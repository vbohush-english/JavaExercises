package net.bohush.exercises.chapter22;

import java.io.*;
import java.util.LinkedList;


public class Exercise11 {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.out.println("Usage: java Exercise11 fileName");
			System.exit(0);
		}
		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("File \"" + args[0] + "\" doesn't exist");
			System.exit(0);			
		}
		
		try {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));			
			System.out.println(checkFile(input));
			input.close();
		} catch (IOException e2) {
		}
		
	}
	
	public static boolean checkFile(InputStream input) throws IOException {
		int r = 0;
		LinkedList<Character> result = new LinkedList<>();
		while ((r = input.read()) != -1) {
			char symbol = (char)(r);
			if ((symbol == '(')||(symbol == '[')||(symbol == '{')) {
				result.add(symbol);
			} else if (symbol == '}') {
				if (result.size() == 0) {
					return false;
				}
				if (result.getLast() == '{') {
					result.removeLast();
				} else {
					return false;
				}
			} else if (symbol == ')') {
				if (result.size() == 0) {
					return false;
				}
				if (result.getLast() == '(') {
					result.removeLast();
				} else {
					return false;
				}
			} else if (symbol == ']') {
				if (result.size() == 0) {
					return false;
				}
				if (result.getLast() == '[') {
					result.removeLast();
				} else {
					return false;
				}
			}
		}
		return result.size() == 0;
	}

}
