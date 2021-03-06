package net.bohush.exercises.chapter14;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Scanner;

public class Exercise19 {

	public static void main(String[] args) throws FileNotFoundException {
		String URLString = "http://cs.armstrong.edu/liang/data/Lincoln.txt";
		try {
			java.net.URL url = new java.net.URL(URLString);

			Scanner inputChar = new Scanner(url.openStream());
			int charCount = 0;
			int lineCount = 0;
			while (inputChar.hasNext()) {
				charCount += inputChar.nextLine().length();
				lineCount++;
			}
			inputChar.close();

			Scanner inputWord = new Scanner(url.openStream());
			int wordCount = 0;
			while (inputWord.hasNext()) {
				inputWord.next();
				wordCount++;
			}
			inputWord.close();

			System.out.println(charCount + " characters");
			System.out.println(wordCount + " words");
			System.out.println(lineCount + " lines");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

	}

}
