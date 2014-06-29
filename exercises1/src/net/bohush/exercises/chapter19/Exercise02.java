package net.bohush.exercises.chapter19;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Exercise02 {
	public static void main(String[] args) throws IOException {
		DataOutputStream output = new DataOutputStream(new FileOutputStream("tmp/Exercise19_02.dat", true));
		for (int i = 0; i < 100; i++) {
			output.writeInt((int)(Math.random() * 1000));
		}
		output.close();
	}
	
}
