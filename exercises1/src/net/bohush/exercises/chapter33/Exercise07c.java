package net.bohush.exercises.chapter33;

import java.io.*;
import java.net.Socket;

public class Exercise07c {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Socket socket = new Socket("localhost", 8000);
		ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
		long[] array = (long[])fromServer.readObject();
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		socket.close();
	}
}