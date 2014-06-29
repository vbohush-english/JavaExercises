package net.bohush.exercises.chapter39;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Exercise04 extends JApplet {
	private static final long serialVersionUID = 1L;

	// Create a JSpinner
	private JSpinner spinner = new JSpinner(new CustomSpinnerModel());

	// Create a JLabel
	private JLabel label1 = new JLabel("", JLabel.CENTER);
	private JLabel label2 = new JLabel("", JLabel.CENTER);
	private JLabel label3 = new JLabel("", JLabel.CENTER);

	public Exercise04() {
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

	class CustomSpinnerModel extends AbstractSpinnerModel {
		private static final long serialVersionUID = 1L;
		long value = 1;
		
		@Override
		public Object getValue() {
			return value;
		}

		@Override
		public void setValue(Object value) {
			this.value = (long) value;
			fireStateChanged();
		}

		@Override
		public Object getNextValue() {
			if(value + value <= 0L) {
				return value;
			} else {
				return value + value;	
			}
						
		}

		@Override
		public Object getPreviousValue() {
			if(value == 1L) {
				return 1L;
			} else {
				return value / 2L;	
			}			
		}
		
	}
	public static void main(String[] args) {
		javax.swing.JFrame frame = new javax.swing.JFrame("Exercise04");

		Exercise04 applet = new Exercise04();

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