package net.bohush.exercises.chapter33;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise02c extends JFrame {
	private static final long serialVersionUID = 1L;

	// Text field for receiving radius
	private JTextField jtf = new JTextField();

	// Text area to display contents
	private JTextArea jta = new JTextArea();

	// IO streams
	private Scanner fromServer;
	private PrintWriter toServer;

	public static void main(String[] args) {
		new Exercise02c();
	}

	public Exercise02c() {
		// Panel p to hold the label and text field
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("Enter radius"), BorderLayout.WEST);
		p.add(jtf, BorderLayout.CENTER);
		jtf.setHorizontalAlignment(JTextField.RIGHT);

		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		add(new JScrollPane(jta), BorderLayout.CENTER);

		jtf.addActionListener(new Listener()); // Register listener

		setTitle("Exercise02c");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!

		try {
			// Create a socket to connect to the server
			@SuppressWarnings("resource")
			Socket socket = new Socket("localhost", 8000);
			// Socket socket = new Socket("130.254.204.36", 8000);
			// Socket socket = new Socket("drake.Armstrong.edu", 8000);

			fromServer = new Scanner(socket.getInputStream());
			toServer = new PrintWriter(socket.getOutputStream());
		} catch (IOException ex) {
			jta.append(ex.toString() + '\n');
		}
	}

	private class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the radius from the text field
			double radius = Double.parseDouble(jtf.getText().trim());

			// Send the radius to the server
			toServer.write(radius + " ");
			toServer.flush();
			// Get area from the server
			double area = Double.parseDouble(fromServer.next());
			
			// Display to the text area
			jta.append("Radius is " + radius + "\n");
			jta.append("Area received from the server is " + area + '\n');
		}
	}
}