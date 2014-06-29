package net.bohush.exercises.chapter22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exercise01 {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.out.println("Usage: java Exercise01 fileName");
			System.exit(0);
		}
		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("File \"" + args[0] + "\" doesn't exist");
			System.exit(0);			
		}
		
		Scanner input = new Scanner(file);
		ArrayList<String> words = new ArrayList<>();
		
		while(input.hasNext()) {
			String nextWord = input.next();
			if (Character.isAlphabetic(nextWord.charAt(0))) {
				words.add(nextWord);	
			}			
		}
		
		Collections.sort(words);
		
		for (String string : words) {
			System.out.println(string);
		}
		
		input.close();
	}

}
