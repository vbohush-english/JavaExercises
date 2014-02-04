package net.bohush.exercises.chapter19;

import java.io.*;
import java.util.Scanner;

public class Exercise04 {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("java Exercise04 file1 file2");
		} else {
			Scanner input = new Scanner(new FileInputStream(args[0]));
			DataOutputStream output = new DataOutputStream(new FileOutputStream(args[1]));
			while (input.hasNextLine()) {
				output.writeUTF(input.nextLine() + "\r\n");
			}
			input.close();
			output.close();
		}
	}

}
