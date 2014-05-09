package net.bohush.exercises.chapter35;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class Exercise05 extends JApplet {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField("10000", 20);
	private JTextField jTextField2 = new JTextField("1", 20);
	private JTextField jTextField3 = new JTextField("7", 20);
	private JTextArea jTextArea = new JTextArea(20, 20);
	
	public Exercise05() {
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel jPanel1 = new JPanel(new GridLayout(3, 2, 5, 5));
		jPanel1.setBorder(new TitledBorder("Enter Loan Amount, Number of Years, and Annual Interest Rate"));
		jPanel1.add(new JLabel("Loan Amount"));
		jPanel1.add(jTextField1);
		jPanel1.add(new JLabel("Number of Years"));
		jPanel1.add(jTextField2);
		jPanel1.add(new JLabel("Annual Interest Rate"));
		jPanel1.add(jTextField3);
		
		JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
		jPanel2.add(jPanel1, BorderLayout.CENTER);
		JButton jButton = new JButton("Display Loan Schedule");
		jButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(getLocale());
						
				double amount = Double.parseDouble(jTextField1.getText());
				double years =  Double.parseDouble(jTextField2.getText());
				double rate =  Double.parseDouble(jTextField3.getText());

				double monthlyInterestRate = rate / 1200;
				double monthlyPayment = amount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12));
				double totalPayment = monthlyPayment * years * 12;

				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("Monthly Payment: " + currencyFormat.format(monthlyPayment) + "\nTotal Payment: " + currencyFormat.format(totalPayment) + "\n");
				stringBuilder.append(String.format("\n%8s%20s%20s%20s\n", "Payment", "Interest", "Principal", "Balance"));
				for (int i = 0; i < years * 12; i++) {
					double interest = monthlyInterestRate * amount;
					double principal = monthlyPayment - interest;
					amount = amount - principal;
					stringBuilder.append(String.format("%8d%20s%20s%20s\n", (i + 1), currencyFormat.format(interest), currencyFormat.format(principal), currencyFormat.format(amount)));
				}
				jTextArea.setText(stringBuilder.toString());
			}
		});
		jPanel2.add(jButton, BorderLayout.EAST);
		mainPanel.add(jPanel2, BorderLayout.NORTH);
		
		jTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		jTextArea.setEditable(false);
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		mainPanel.add(jScrollPane, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise05");
		JApplet applet = new Exercise05();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

