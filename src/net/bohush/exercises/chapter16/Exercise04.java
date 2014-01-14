package net.bohush.exercises.chapter16;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise04 extends JFrame{
	private JButton jbtAdd = new JButton("Add");
	private JButton jbtSubtract = new JButton("Subtract");
	private JButton jbtMultiply = new JButton("Multiply");
	private JButton jbtDivide = new JButton("Divide");
	private JTextField jtfNumber1 = new JTextField(10);
	private JTextField jtfNumber2 = new JTextField(10);
	private JTextField jtfResult = new JTextField(10);

	
	private static final long serialVersionUID = 1L;

	public Exercise04() {
		
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(new JLabel("Number 1"));
		panel1.add(jtfNumber1);
		panel1.add(new JLabel("Number 2"));
		panel1.add(jtfNumber2);
		panel1.add(new JLabel("Result"));
		jtfResult.setEditable(false);
		panel1.add(jtfResult);
		add(panel1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		panel2.add(jbtAdd);
		panel2.add(jbtSubtract);
		panel2.add(jbtMultiply);
		panel2.add(jbtDivide);
		add(panel2, BorderLayout.SOUTH);
		
		CalculateListener calculateListener = new CalculateListener();
		jbtAdd.addActionListener(calculateListener);
		jbtSubtract.addActionListener(calculateListener);
		jbtMultiply.addActionListener(calculateListener);
		jbtDivide.addActionListener(calculateListener);
	}
	
	private class CalculateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				double number1 = Double.parseDouble(jtfNumber1.getText());
				double number2 = Double.parseDouble(jtfNumber2.getText());
				String result = "";
				if (e.getSource() == jbtAdd) {
					result += number1 + number2;
				} else if (e.getSource() == jbtSubtract) {
					result += number1 - number2;
				} else if (e.getSource() == jbtMultiply) {
					result += number1 * number2;
				} else if (e.getSource() == jbtDivide) {
					result += number1 / number2;
				} 
				jtfResult.setText(result);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Wrong number format!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public static void main(String[] args) {
		Exercise04 frame = new Exercise04();
		frame.setTitle("Exercise04");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
