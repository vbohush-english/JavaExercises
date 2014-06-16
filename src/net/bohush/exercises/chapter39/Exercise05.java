package net.bohush.exercises.chapter39;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Exercise05 extends JApplet {
	private static final long serialVersionUID = 1L;

	// Create a JSpinner
	private JSpinner spinner = new JSpinner(new CustomSpinnerModel());

	// Create a JLabel
	private JLabel label1 = new JLabel("", JLabel.CENTER);
	private JLabel label2 = new JLabel("", JLabel.CENTER);
	private JLabel label3 = new JLabel("", JLabel.CENTER);

	public Exercise05() {
		add(spinner, BorderLayout.NORTH);
		JPanel jPanel = new JPanel(new GridLayout(3, 1, 5, 5));
		jPanel.add(label1);
		jPanel.add(label2);
		jPanel.add(label3);
		add(jPanel, BorderLayout.CENTER);

		// Register and create a listener
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(javax.swing.event.ChangeEvent e) {
				label1.setText("Previous value: " + spinner.getPreviousValue());
				label2.setText("Current value: " + spinner.getValue());
				label3.setText("Next value: " + spinner.getNextValue());
			}
		});
	}

	class CustomSpinnerModel extends SpinnerNumberModel {
		private static final long serialVersionUID = 1L;

		@Override
		public Object getNextValue() {
			return super.getPreviousValue();
		}

		@Override
		public Object getPreviousValue() {
			return super.getNextValue();
		}
		
	}
	public static void main(String[] args) {
		javax.swing.JFrame frame = new javax.swing.JFrame("Exercise05");

		Exercise05 applet = new Exercise05();

		// Add the applet instance to the frame
		frame.getContentPane().add(applet, java.awt.BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Invoke init and start
		applet.init();
		applet.start();

		// Display the frame
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}