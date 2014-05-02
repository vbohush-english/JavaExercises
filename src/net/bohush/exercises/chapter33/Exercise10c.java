package net.bohush.exercises.chapter33;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Exercise10c extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextArea jta = new JTextArea();
	private PrintWriter toServer;
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	
	public Exercise10c() {
		setLayout(new BorderLayout(5, 5));
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		jta.setWrapStyleWord(true);
	    jta.setLineWrap(true);
	    jta.setEditable(false);
	    jta.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
	    JScrollPane jsp = new JScrollPane(jta);

		jsp.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});
	    
		
	    mainPanel.add(jsp, BorderLayout.CENTER);
	    
	    JPanel jPanel1 = new JPanel(new GridLayout(2, 1, 5, 5));
	    JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
	    jPanel2.add(new JLabel("Name "), BorderLayout.WEST);	    
	    jPanel2.add(jTextField1, BorderLayout.CENTER);
	    JPanel jPanel3 = new JPanel(new BorderLayout(5, 5));
	    jPanel3.add(new JLabel("Enter text "), BorderLayout.WEST);
	    jPanel3.add(jTextField2, BorderLayout.CENTER);
	    jPanel1.add(jPanel2);
	    jPanel1.add(jPanel3);
	    mainPanel.add(jPanel1, BorderLayout.SOUTH);
	    add(mainPanel, BorderLayout.CENTER);
	    
	    jTextField2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jTextField1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter your name!", "Waring", JOptionPane.WARNING_MESSAGE);
					jTextField1.requestFocus();
				} else if (jTextField2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter message!", "Waring", JOptionPane.WARNING_MESSAGE);
					jTextField2.requestFocus();
				} else {
					toServer.println(jTextField1.getText() + ": " + jTextField2.getText());
					toServer.flush();
					jTextField2.setText("");
				}
			}
		});
	    
		setTitle("Exercise10c");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		
		try {
			Socket socket = new Socket("localhost", 8000); 
			toServer = new PrintWriter(socket.getOutputStream());
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
				Scanner fromServer = new Scanner(socket.getInputStream());
				while(true) {
					String text = fromServer.nextLine();
					jta.append(text + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchElementException e) {				
			}
		}
	}

	public static void main(String[] args) {
		new Exercise10c();
	}
}
