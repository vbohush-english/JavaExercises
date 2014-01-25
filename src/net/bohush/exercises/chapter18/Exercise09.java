package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exercise09 extends JFrame{
	private JTextField jTextField1 = new JTextField(20);
	private JTextField jTextField2 = new JTextField(20);
	private JTextField jTextField3 = new JTextField(20);
	private static final long serialVersionUID = 1L;

	public Exercise09() {
		JPanel panel1 = new JPanel(new GridLayout(3, 1));
		panel1.add(new JLabel("Decimal  "));
		panel1.add(new JLabel("Hex  "));
		panel1.add(new JLabel("Binary  "));
		add(panel1, BorderLayout.WEST);
		
		JPanel panel2 = new JPanel(new GridLayout(3, 1));
		jTextField1.setHorizontalAlignment(JTextField.RIGHT);
		jTextField2.setHorizontalAlignment(JTextField.RIGHT);
		jTextField3.setHorizontalAlignment(JTextField.RIGHT);
		jTextField1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int number = Integer.parseInt(jTextField1.getText());
					jTextField2.setText(Integer.toHexString(number));
					jTextField3.setText(Integer.toBinaryString(number));
				} catch (NumberFormatException e2) {
					jTextField2.setText("Error");
					jTextField3.setText("Error");
				}
			}
		});
		jTextField2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int number = Integer.parseInt(jTextField2.getText(), 16);
					jTextField1.setText(Integer.toString(number));
					jTextField3.setText(Integer.toBinaryString(number));
				} catch (NumberFormatException e2) {
					jTextField1.setText("Error");
					jTextField3.setText("Error");
				}
			}
		});
		jTextField3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int number = Integer.parseInt(jTextField3.getText(), 2);
					jTextField1.setText(Integer.toString(number));
					jTextField2.setText(Integer.toHexString(number));
				} catch (NumberFormatException e2) {
					jTextField1.setText("Error");
					jTextField2.setText("Error");
				}
			}
		});
		panel2.add(jTextField1);
		panel2.add(jTextField2);
		panel2.add(jTextField3);
		add(panel2, BorderLayout.CENTER);
		
		
	}
	
	public static void main(String[] args) {
		Exercise09 frame = new Exercise09();
		frame.setTitle("Exercise26");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
