package net.bohush.exercises.chapter19;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Exercise01 {
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter output = new PrintWriter(new FileOutputStream("tmp/Exercise19_01.txt", true));
		for (int i = 0; i < 100; i++) {
			output.print((int)(Math.random() * 100) + " ");
		}
		output.println();
		output.close();
	}
	
}
