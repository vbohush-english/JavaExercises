package net.bohush.exercises.chapter23;

import java.util.*;
import java.io.*;

public class Exercise04 {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a source file: ");
		String filename = input.nextLine();

		File file = new File(filename);
		if (file.exists()) {
			CountChars(file);
		} else {
			System.out.println("File " + filename + " does not exist");
		}
	}

	
	public static void CountChars(File file) throws Exception {

		DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		int size = input.available();
		byte[] b = new byte[size];
		input.read(b);
		input.close();
		
		HashSet<Character> vowelsSet = new HashSet<>(Arrays.asList(new Character[]{'a', 'e', 'i', 'o', 'u'}));
		int vowels = 0;
		int consonants = 0;
		for (int i = 0; i < b.length; i++) {
			if(Character.isAlphabetic((char)(b[i]))) {
				if(vowelsSet.contains(Character.toLowerCase((char)(b[i])))) {
					vowels++;
				} else {
					consonants++;
				}
			}
		}
		System.out.println("Vowels " + vowels);
		System.out.println("Consonants " + consonants);

	}
}