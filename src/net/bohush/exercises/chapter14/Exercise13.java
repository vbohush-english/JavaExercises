package net.bohush.exercises.chapter14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise13 {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.out.println("Execute: java Exercise13 filename");
			System.exit(1);
		}
		File file = new File(args[0]);
		
		
		Scanner inputChar = new Scanner(file);
		int charCount = 0;
		int lineCount = 0;
		while(inputChar.hasNext()) {
			charCount += inputChar.nextLine().length();
			lineCount++;
		}		
		inputChar.close();
		
		
		Scanner inputWord = new Scanner(file);
		int wordCount = 0;
		while(inputWord.hasNext()) {
			inputWord.next();
			wordCount++;
		}
		inputWord.close();
		
		
		System.out.println(charCount + " characters");
		System.out.println(wordCount + " words");
		System.out.println(lineCount + " lines");
		
	}

}
