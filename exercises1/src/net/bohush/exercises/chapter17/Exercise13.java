package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exercise13 extends JFrame{
	private JTextField jtfAmount = new JTextField("10000", 10);
	private JTextField jtfYears = new JTextField("5", 2);
	private JButton jbtnShow = new JButton("ShowTable");
	private JTextArea jtaPainments = new JTextArea("", 10, 25);
	private static final long serialVersionUID = 1L;

	public Exercise13() {
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("Loan Amount"));
		panel1.add(jtfAmount);
		panel1.add(new JLabel("Number of Years"));
		panel1.add(jtfYears);
		panel1.add(jbtnShow);
		add(panel1, BorderLayout.NORTH);
		
		JScrollPane viewPainments = new JScrollPane(jtaPainments);
		add(viewPainments, BorderLayout.CENTER);

		jbtnShow.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double amount = Double.parseDouble(jtfAmount.getText());
					int years = Integer.parseInt(jtfYears.getText());
					jtaPainments.setText("");
					jtaPainments.append("Interest Rate\tMonthly Payment\tTotal Payment\n");
					for (double rate = 5.0; rate <= 8.0; rate += 0.125) {
						double monthlyInterestRate = rate / 1200;
						double monthlyPayment = amount * monthlyInterestRate / (1 - 1 / Math.pow(1 + monthlyInterestRate, years * 12));
						double totalPayment = monthlyPayment * years * 12;
						jtaPainments.append(String.format("%.3f%%\t%.2f\t\t%.2f\n", rate, monthlyPayment, totalPayment));
					}	
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Wrong input data!", "Error", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
	}
	
	public static void main(String[] args) {
		Exercise13 frame = new Exercise13();
		frame.setTitle("Exercise13");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
