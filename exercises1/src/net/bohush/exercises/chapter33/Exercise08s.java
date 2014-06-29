package net.bohush.exercises.chapter33;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Exercise08s {

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(8000);
		while (true) {
			Socket socket = serverSocket.accept();	
			ObjectOutputStream outputToClient = new ObjectOutputStream(socket.getOutputStream());
			
			DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("tmp/PrimeNumbers.dat")));
			int primeNumbers = 100;
			ArrayList<Long> list = new ArrayList<>();
			input.skipBytes(input.available() - 8 * primeNumbers);
			for (int i = 0; i < primeNumbers; i++) {
				list.add(input.readLong());
			}
			input.close();
			
			outputToClient.writeObject(list);
			
		}
	}

}
