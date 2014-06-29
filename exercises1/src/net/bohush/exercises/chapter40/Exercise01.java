package net.bohush.exercises.chapter40;


import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

public class Exercise01 extends JApplet {

	private static final long serialVersionUID = 1L;
	private JSpinner spinnerLoan = new JSpinner(new SpinnerNumberModel(100000.0, 500.0, 100000000.0, 500.0));
	private JSpinner spinnerYears = new JSpinner(new SpinnerNumberModel(15, 1, 50, 1));
	private JSpinner spinnerAnnual = new JSpinner(new SpinnerNumberModel(0.05, 0.00125, 1.0, 0.00125));
	private String[] columnNames = {"Payment", "Interest", "Principal", "Balance"};
	private DefaultTableModel tableModel = new DefaultTableModel(null, columnNames);
	private JTable jTable1 = new JTable(tableModel);
	
	
	public Exercise01() {
		JSpinner.NumberEditor yearEditor = new JSpinner.NumberEditor(spinnerYears, "##");
		spinnerYears.setEditor(yearEditor);
		
		JSpinner.NumberEditor loadEditor = new JSpinner.NumberEditor(spinnerLoan, "$#########.##");
		spinnerLoan.setEditor(loadEditor);
		
		JSpinner.NumberEditor annualEditor = new JSpinner.NumberEditor(spinnerAnnual, "%###.###");
		spinnerAnnual.setEditor(annualEditor);
		
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel jPanel1 = new JPanel(new GridLayout(3, 2, 5, 5));
		jPanel1.setBorder(new TitledBorder("Enter Loan Amount, Number of Years, and Annual Interest Rate"));
		jPanel1.add(new JLabel("Loan Amount"));
		jPanel1.add(spinnerLoan);
		jPanel1.add(new JLabel("Number of Years"));
		jPanel1.add(spinnerYears);
		jPanel1.add(new JLabel("Annual Interest Rate"));
		jPanel1.add(spinnerAnnual);
		
		JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
		jPanel2.add(jPanel1, BorderLayout.CENTER);
		JButton jButton = new JButton("Display Loan Schedule");
		jButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(getLocale());
					
					double amount = (double)(spinnerLoan.getValue());
					int years =  (int)(spinnerYears.getValue());
					double rate =  100 * (double)(spinnerAnnual.getValue());

					double monthlyInterestRate = rate / 1200;
					double monthlyPayment = amount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12));
					
					tableModel.setRowCount(0);
					for (int i = 0; i < years * 12; i++) {
						double interest = monthlyInterestRate * amount;
						double principal = monthlyPayment - interest;
						amount = amount - principal;
						tableModel.addRow(new Object[]{(i + 1), currencyFormat.format(interest), currencyFormat.format(principal), currencyFormat.format(amount)});
					}
					
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Exception: " + e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jPanel2.add(jButton, BorderLayout.EAST);
		mainPanel.add(jPanel2, BorderLayout.NORTH);
		
		jTable1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		JScrollPane jScrollPane = new JScrollPane(jTable1);
		mainPanel.add(jScrollPane, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise01");
		JApplet applet = new Exercise01();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

