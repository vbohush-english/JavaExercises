package net.bohush.exercises.chapter33;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise01c extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField jtfAnnualInterestRate = new JTextField();
	private JTextField jtfNumberOfYears = new JTextField();
	private JTextField jtfLoanAmount = new JTextField();
	private JTextArea jta = new JTextArea();
	private DataOutputStream toServer;
	private DataInputStream fromServer;

	public static void main(String[] args) {
		new Exercise01c();
	}

	public Exercise01c() {
		JPanel jPanel1 = new JPanel(new GridLayout(3, 2, 5, 5));
		jPanel1.add(new JLabel("Annual Interest Rate"));
		jPanel1.add(jtfAnnualInterestRate);
		jPanel1.add(new JLabel("Number Of Years"));
		jPanel1.add(jtfNumberOfYears);
		jPanel1.add(new JLabel("Loan Amount"));
		jPanel1.add(jtfLoanAmount);
		JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
		jPanel2.add(jPanel1, BorderLayout.CENTER);
		JButton jButton1 = new JButton("Submit");
		jPanel2.add(jButton1, BorderLayout.EAST);
		
		setLayout(new BorderLayout());
		add(jPanel2, BorderLayout.NORTH);
		add(new JScrollPane(jta), BorderLayout.CENTER);

		jButton1.addActionListener(new Listener());

		setTitle("Client");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		Socket socket;
		try {
			socket = new Socket("localhost", 8000);
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			jta.append(ex.toString() + '\n');
		}
	}

	private class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				double interest = Double.parseDouble(jtfAnnualInterestRate.getText());
				int year = Integer.parseInt(jtfNumberOfYears.getText());
				double loanAmount = Double.parseDouble(jtfLoanAmount.getText());
				toServer.writeDouble(interest);
				toServer.writeInt(year);
				toServer.writeDouble(loanAmount);
				toServer.flush();
				
				double monthlyPayment = fromServer.readDouble();
				double totalPayment = fromServer.readDouble();
				
				jta.append("Annual Interest Rate " + interest + "\n");
				jta.append("Number Of Years " + year + "\n");
				jta.append("Loan Amount " + loanAmount + "\n");
				jta.append("monthlyPayment " + monthlyPayment + "\n");
				jta.append("totalPayment " + totalPayment + "\n");
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}
}
