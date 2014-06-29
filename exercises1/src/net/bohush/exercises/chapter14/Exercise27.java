package net.bohush.exercises.chapter14;

import java.io.*;
import java.util.*;

public class Exercise27 {
	public static void main(String[] args) throws Exception {

		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("Exercise") && args[i].endsWith("java")) {

				File sourceFile = new File(args[i]);
				Scanner input = new Scanner(sourceFile);
				StringBuilder data = new StringBuilder("");
				while (input.hasNext()) {
					data.append(input.nextLine() + "\r\n");
				}
				input.close();
				
				
				data = replaceExercise(data);
				
				PrintWriter output = new PrintWriter(sourceFile);
				output.print(data);
				output.close();
			}
		}
	}
	
	public static StringBuilder replaceExercise(StringBuilder data) {
		int index = data.indexOf("Exercise");
		while (index != -1) {
			if (Character.isDigit(data.charAt(index + 8)) &&
				(data.charAt(index + 9) == '_')) {
				data.insert(index + 8, '0');
			}
			if (Character.isDigit(data.charAt(index + 11)) &&
				(!Character.isDigit(data.charAt(index + 12)))) {
				data.insert(index + 11, '0');
			}
			index = data.indexOf("Exercise", index + 1);
		}
		return data;
	}
}