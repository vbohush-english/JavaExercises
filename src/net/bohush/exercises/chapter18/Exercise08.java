package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exercise08 extends JFrame{
	JTextField jTextField = new JTextField();
	private static final long serialVersionUID = 1L;
	private char whatDo = ' ';
	private double number1 = 0;
	public Exercise08() {
		JPanel panel1 = new JPanel(new GridLayout(4, 6));
		String[] buttonNames = new String[]{"MC", "7", "8", "9", "/", "sqrt", 
											"MR", "4", "5", "6", "*", "%", 
											"MS", "1", "2", "3", "-", "1/x", 
											"M+", "0", "+/-", ".", "+", "=", };
		JButton[] buttonFigures = new JButton[buttonNames.length];
		for (int i = 0; i < buttonNames.length; i++) {
			buttonFigures[i] = new JButton(buttonNames[i]);
			panel1.add(buttonFigures[i]);
			buttonFigures[i].addActionListener(new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Integer.parseInt(((JButton)(e.getSource())).getText());
						jTextField.setText(jTextField.getText() + ((JButton)(e.getSource())).getText());
					} catch (NumberFormatException e2) {	
						String operation = ((JButton)(e.getSource())).getText();
						switch (operation) {
						case "+": whatDo = '+'; number1 = Double.parseDouble(jTextField.getText()); jTextField.setText(""); break;
						case "-": whatDo = '-'; number1 = Double.parseDouble(jTextField.getText()); jTextField.setText(""); break;
						case "/": whatDo = '/'; number1 = Double.parseDouble(jTextField.getText()); jTextField.setText(""); break;
						case "*": whatDo = '*'; number1 = Double.parseDouble(jTextField.getText()); jTextField.setText(""); break;
						case "sqrt": whatDo = 's'; jTextField.setText("" + Math.sqrt(Integer.parseInt(jTextField.getText()))); break;
						case "=":
							if (whatDo != ' ') {
								double number2 = Double.parseDouble(jTextField.getText());
								double result = 0;
								switch (whatDo) {
								case '+': result = number1 + number2; break;
								case '-': result = number1 - number2; break;
								case '/': result = number1 / number2; break;
								case '*': result = number1 * number2; break;								
								}
								whatDo = ' ';
								jTextField.setText("" + result);
							}
						}
					}					
				}
			});
		}
		add(panel1, BorderLayout.SOUTH);
		
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		JButton button1 = new JButton("Back");
		JButton button2 = new JButton("CE");
		JButton button3 = new JButton("C");
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		add(panel2, BorderLayout.CENTER);
		
		add(jTextField, BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		Exercise08 frame = new Exercise08();
		frame.setTitle("Exercise08");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
