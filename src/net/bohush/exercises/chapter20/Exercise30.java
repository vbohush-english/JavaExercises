package net.bohush.exercises.chapter20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise30 {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Exercise30 dirName word");
			System.exit(0);
		}

		String directory = args[0];
		String word = args[1];

		long time1 = System.currentTimeMillis();
		System.out.println(getNumberOfWords(new File(directory), word) + " words");
		long time2 = System.currentTimeMillis();
		System.out.println((time2 - time1) + " msec.");
	}

	public static long getNumberOfWords(File file, String word) {
		long numberOfWords = 0;

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; files != null && i < files.length; i++) {
				numberOfWords += getNumberOfWords(files[i], word);
			}
		} else {
			numberOfWords += getNumberOfWordsInFile(file, word);
		}

		return numberOfWords;
	}
	
	public static long getNumberOfWordsInFile(File file, String word) {
		long words = 0;
		try {
			Scanner input = new Scanner(file);
			while(input.hasNextLine()) {
				int k = 0;
				String tmp = input.nextLine();
				while ((k = tmp.indexOf(word, k)) >= 0) {
					words++;
					k++;
				}
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return words;
	}

}