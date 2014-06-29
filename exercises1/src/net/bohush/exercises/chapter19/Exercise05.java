package net.bohush.exercises.chapter19;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Exercise05 {
	public static void main(String[] args) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("tmp/Exercise19_05.dat"));
	    output.writeObject(new int[]{1, 2, 3, 4, 5});
	    output.writeObject(new java.util.Date());
	    output.writeDouble(5.5);
	    output.close();
	}
}
