package net.bohush.exercises.chapter35;

import java.io.*;

public class Exercise11Writer {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		int value = 0x0E00;
		PrintWriter output = new PrintWriter("tmp/Exercise11.txt", "GBK");
		for (int i = 0; i < 1307; i++) {
			for (int j = 0; j < 16; j++) {
				output.print((char)value);
				output.print(' ');
				value++;
			}
			output.print('\n');
		}
		output.close();
	}
}