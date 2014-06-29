package net.bohush.exercises.chapter37;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exercise05 extends JFrame{
	JTextField jTextField = new JTextField();
	private static final long serialVersionUID = 1L;
	private char whatDo = ' ';
	private double number1 = 0;
	public Exercise05() {
		setLayout(null);
		
		add(jTextField, BorderLayout.NORTH);
		jTextField.setBounds(10, 10, 410, 30);
		
		JButton button1 = new JButton("Back");
		JButton button2 = new JButton("CE");
		JButton button3 = new JButton("C");
		add(button1);
		add(button2);
		add(button3);
		button1.setBounds(190, 50, 70, 30);
		button2.setBounds(270, 50, 70, 30);
		button3.setBounds(350, 50, 70, 30);
		
		String[] buttonNames = new String[]{"MC", "7", "8", "9", "/", "sqrt", 
											"MR", "4", "5", "6", "*", "%", 
											"MS", "1", "2", "3", "-", "1/x", 
											"M+", "0", "+/-", ".", "+", "=", };
		JButton[] buttonFigures = new JButton[buttonNames.length];
		for (int i = 0; i < buttonNames.length; i++) {
			
			buttonFigures[i] = new JButton(buttonNames[i]);
			add(buttonFigures[i]);
			buttonFigures[i].setBounds(10 + (i * 70) % (6 * 70), 100 + (i / 6) * 40, 60, 30);
			
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
						case "sqrt": whatDo = 's'; jTextField.setText("" + Math.sqrt(Double.parseDouble(jTextField.getText()))); break;
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

	}
	
	public static void main(String[] args) {
		Exercise05 frame = new Exercise05();
		frame.setTitle("Exercise05");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(445, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
