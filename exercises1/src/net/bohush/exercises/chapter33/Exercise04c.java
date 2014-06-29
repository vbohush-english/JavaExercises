package net.bohush.exercises.chapter33;

import java.io.*;
import java.net.*;

import javax.swing.*;

public class Exercise04c extends JApplet {
	private static final long serialVersionUID = 1L;

	// Label for displaying the visit count
	private JLabel jlblCount = new JLabel();

	// Indicate if it runs as application
	private boolean isStandAlone = false;

	// Host name or ip
	private String host = "localhost";

	/** Initialize the applet */
	@SuppressWarnings("resource")
	public void init() {
		add(jlblCount);

		try {
			// Create a socket to connect to the server
			Socket socket;
			if (isStandAlone)
				socket = new Socket(host, 8000);
			else
				socket = new Socket(getCodeBase().getHost(), 8000);

			// Create an input stream to receive data from the server
			DataInputStream inputFromServer = new DataInputStream(
					socket.getInputStream());

			// Receive the count from the server and display it on label
			int count = inputFromServer.readInt();
			jlblCount.setText("You are visitor number " + count);

			// Close the stream
			inputFromServer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/** Run the applet as an application */
	public static void main(String[] args) {
		// Create a frame
		JFrame frame = new JFrame("Exercise04c");

		// Create an instance of the applet
		Exercise04c applet = new Exercise04c();
		applet.isStandAlone = true;

		// Get host
		if (args.length == 1)
			applet.host = args[0];

		// Add the applet instance to the frame
		frame.add(applet, java.awt.BorderLayout.CENTER);

		// Invoke init() and start()
		applet.init();
		applet.start();

		// Display the frame
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}