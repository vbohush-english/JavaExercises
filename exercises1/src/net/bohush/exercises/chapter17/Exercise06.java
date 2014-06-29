package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exercise06 extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField jtfMile = new JTextField(20);
	private JTextField jtfKilometer = new JTextField(20);
	
	public Exercise06() {
		setLayout(new GridLayout(2, 2, 5, 5));
		JLabel jlblMile = new JLabel("Mile");
		JLabel jlblKilometer = new JLabel("Kilometer");
		
		jtfMile.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double miles = Double.parseDouble(jtfMile.getText());
					double kilometres = 1.609344 * miles;
					jtfKilometer.setText(String.format("%.3f", kilometres).replace(',', '.'));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Wrong number format!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jtfKilometer.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double kilometres = Double.parseDouble(jtfKilometer.getText());
					double miles = 0.621371192 * kilometres;
					jtfMile.setText(String.format("%.3f", miles).replace(',', '.'));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Wrong number format!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		add(jlblMile);
		add(jtfMile);
		add(jlblKilometer);
		add(jtfKilometer);
	}
	
	public static void main(String[] args) {
		Exercise06 frame = new Exercise06();
		frame.setTitle("Exercise06");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
