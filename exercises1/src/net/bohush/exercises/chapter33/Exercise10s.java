package net.bohush.exercises.chapter33;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Exercise10s extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextArea jta = new JTextArea();
	private ArrayList<NewClient> clients = new ArrayList<>();
	
	public Exercise10s() {
		setLayout(new BorderLayout());
	    jta.setLineWrap(true);	 
	    jta.setWrapStyleWord(true);
	    jta.setEditable(false);
	    jta.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
	    jta.append(new Date() + " Startig Chat Server\n");
	    JScrollPane jsp = new JScrollPane(jta);
	    
		jsp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});
	    
	    add(jsp, BorderLayout.CENTER);
		setTitle("Exercise10s");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setVisible(true);

		
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(8000);
			while(true) {
				Socket socket = serverSocket.accept();
				NewClient newClient = new NewClient(socket);
				clients.add(newClient);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class NewClient implements Runnable {
		private Socket socket;
		private PrintWriter toClient;
		
		public NewClient(Socket socket) {
			this.socket = socket;
			Thread thread = new Thread(this);
			thread.start();
		}
		
		public void run() {
			try {
				@SuppressWarnings("resource")
				Scanner fromClient = new Scanner(socket.getInputStream());
				toClient = new PrintWriter(socket.getOutputStream());
				jta.append(new Date() + " Connection from  " + socket + "\n");
				while(true) {
					String text = fromClient.nextLine();
					jta.append(new Date() + " " + text + "\n");
					for (NewClient newClient : clients) {
						newClient.send(text);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchElementException e) {				
			}
		}
		
		public void send(String text) {
			Calendar time = new GregorianCalendar();
			toClient.printf("%02d:%02d:%02d " + text + "\n", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.SECOND));
			toClient.flush();
		}
		
	}

	public static void main(String[] args) {
		new Exercise10s();
	}
}
