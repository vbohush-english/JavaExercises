package net.bohush.exercises.chapter33;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class Exercise09s extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextArea jta1 = new JTextArea();
	private JTextArea jta2 = new JTextArea();
	private PrintWriter toClient;
	
	public Exercise09s() {
		setLayout(new GridLayout(2, 1, 5, 5));
		jta1.setWrapStyleWord(true);
		jta1.setWrapStyleWord(true);
	    jta2.setLineWrap(true);	    
	    jta2.setLineWrap(true);
	    jta2.setEditable(false);
	    JScrollPane jsp1 = new JScrollPane(jta1);
	    JScrollPane jsp2 = new JScrollPane(jta2);
	    jsp1.setBorder(new TitledBorder("Client"));
	    jsp2.setBorder(new TitledBorder("Server"));
	    add(jsp2);
	    add(jsp1);
		setTitle("Exercise09s");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		
		jta1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		        	try {
			        	toClient.print(jta1.getText().split("\n")[jta1.getLineCount() - 1] + "\n");
			        	toClient.flush();						
					} catch (ArrayIndexOutOfBoundsException e2) {
					}

		         }
			}
		});
		
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(8000);
			Socket socket = serverSocket.accept();
			toClient = new PrintWriter(socket.getOutputStream());
			new ReceiveMessage(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class ReceiveMessage implements Runnable {
		private Socket socket;
		
		public ReceiveMessage(Socket socket) {
			this.socket = socket;
			Thread thread = new Thread(this);
			thread.start();
		}
		
		public void run() {
			try {
				@SuppressWarnings("resource")
				Scanner fromClient = new Scanner(socket.getInputStream());
				while(true) {
					String text = fromClient.nextLine();
					jta2.append(text + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

	public static void main(String[] args) {
		new Exercise09s();
	}
}
