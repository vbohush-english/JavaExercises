package net.bohush.exercises.chapter19;

import java.io.*;

public class Exercise08 {

	public static void main(String[] args) throws IOException {
		
		String fileName = "tmp/Exercise19_08.dat";
		int count = 0;
		try {
			DataInputStream input = new DataInputStream(new FileInputStream(fileName));
			count = input.readInt();
			input.close();			
		} catch (FileNotFoundException e) {
		}
		
		count++;
		System.out.println(count);
		
		DataOutputStream output = new DataOutputStream(new FileOutputStream(fileName));
		
		output.writeInt(count);
		output.close();
	}
	

}
