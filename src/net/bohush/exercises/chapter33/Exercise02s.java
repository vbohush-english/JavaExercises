package net.bohush.exercises.chapter33;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;

import javax.swing.*;

public class Exercise02s extends JFrame {
	private static final long serialVersionUID = 1L;
	// Text area for displaying contents
	private JTextArea jta = new JTextArea();

	public static void main(String[] args) {
		new Exercise02s();
	}

	@SuppressWarnings("resource")
	public Exercise02s() {
		// Place text area on the frame
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);

		setTitle("Exercise02s");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!

		try {
			// Create a server socket
			ServerSocket serverSocket = new ServerSocket(8000);
			jta.append("Server started at " + new Date() + '\n');

			// Listen for a connection request
			Socket socket = serverSocket.accept();

			Scanner inputFromClient = new Scanner(socket.getInputStream());
			PrintWriter outputToClient = new PrintWriter(socket.getOutputStream());

			while (true) {
				double radius = Double.parseDouble(inputFromClient.next());

				// Compute area
				double area = radius * radius * Math.PI;

				outputToClient.write(area + " ");
				outputToClient.flush();
				jta.append("Radius received from client: " + radius + '\n');
				jta.append("Area found: " + area + '\n');
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}