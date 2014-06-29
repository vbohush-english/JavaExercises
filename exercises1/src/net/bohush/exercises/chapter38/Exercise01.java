package net.bohush.exercises.chapter38;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Exercise01 extends JFrame{
	private JButton jbtCalculate = new JButton("Calculate");
	private JTextField jtfNumber1 = new JTextField(10);
	private JTextField jtfNumber2 = new JTextField(10);
	private JTextField jtfNumber3 = new JTextField(10);
	private JTextField jtfResult = new JTextField(10);

	
	private static final long serialVersionUID = 1L;

	public Exercise01() {
		CalculateListener calculateListener = new CalculateListener();
		
		JMenuBar jmb = new JMenuBar();
		this.setJMenuBar(jmb);
		JMenu operationMenu = new JMenu("Operation");
		JMenuItem calculateItem = new JMenuItem("Calculate"); 
		calculateItem.addActionListener(calculateListener);
		operationMenu.add(calculateItem);
		operationMenu.addSeparator();
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(2);
			}
		});
		operationMenu.add(exitItem);
		JMenu helpMenu = new JMenu("Help");

		jmb.add(operationMenu);
		jmb.add(helpMenu);
		
		JPanel panel1 = new JPanel(new GridLayout(4, 2, 5, 5));
		panel1.add(new JLabel("Investment Amount  "));
		panel1.add(jtfNumber1);
		panel1.add(new JLabel("Years  "));
		panel1.add(jtfNumber2);
		panel1.add(new JLabel("Annual Interest Rate  "));
		panel1.add(jtfNumber3);
		panel1.add(new JLabel("Future value  "));		
		jtfResult.setEditable(false);
		panel1.add(jtfResult);
		add(panel1, BorderLayout.CENTER);
		
		add(jbtCalculate, BorderLayout.SOUTH);
		
		jbtCalculate.addActionListener(calculateListener);
	}
	
	private class CalculateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				double investmentAmount = Double.parseDouble(jtfNumber1.getText());
				double years = Double.parseDouble(jtfNumber2.getText());
				double monthlyInterestRate = (Double.parseDouble(jtfNumber3.getText()) / 100) / 12;
				double futureValue = investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12);
				String result = String.format("%.2f", futureValue);
				jtfResult.setText(result);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Wrong number format!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public static void main(String[] args) {
		Exercise01 frame = new Exercise01();
		frame.setTitle("Exercise01");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
