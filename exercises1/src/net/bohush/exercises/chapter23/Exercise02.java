package net.bohush.exercises.chapter23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeSet;
import java.util.Scanner;

public class Exercise02 {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.out.println("Usage: java Exercise02 fileName");
			System.exit(0);
		}
		File file = new File(args[0]);
		if (!file.exists()) {
			System.out.println("File \"" + args[0] + "\" doesn't exist");
			System.exit(0);			
		}
		
		
		Scanner input = new Scanner(file);
		TreeSet<String> set = new TreeSet<>();
		while(input.hasNext()) {
			String tmp = input.next();
			set.add(tmp);
		}
		input.close();
		for (String string : set) {
			System.out.println(string);
		}
	}

}
