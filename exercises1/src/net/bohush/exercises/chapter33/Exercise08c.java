package net.bohush.exercises.chapter33;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Exercise08c {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Socket socket = new Socket("localhost", 8000);
		ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
		@SuppressWarnings("unchecked")
		ArrayList<Long> list = (ArrayList<Long>)fromServer.readObject();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		socket.close();
	}
}