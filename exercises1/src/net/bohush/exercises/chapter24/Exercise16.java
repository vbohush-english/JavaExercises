package net.bohush.exercises.chapter24;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Exercise16 extends JApplet {

	private static final long serialVersionUID = 1L;
	private LinearSearchPanel linearSearchPanel = new LinearSearchPanel();
	private JTextField jTextField = new JTextField("37", 5);
	
	public Exercise16() {
		setLayout(new BorderLayout());
		add(linearSearchPanel, BorderLayout.CENTER);
		
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("Key (in double): "));
		panel1.add(jTextField);
		JButton jButton1 = new JButton("Step");
		panel1.add(jButton1);
		JButton jButton2 = new JButton("Reset");
		panel1.add(jButton2);
		add(panel1, BorderLayout.SOUTH);	
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jTextField.getText());
					jTextField.setEditable(false);
					try {
						if (linearSearchPanel.step(value)) {
							JOptionPane.showMessageDialog(null, "Key found!", "Finish", JOptionPane.INFORMATION_MESSAGE);
							linearSearchPanel.reset();
							jTextField.setEditable(true);
						};	
					} catch (IndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "Key not found!", "Error", JOptionPane.ERROR_MESSAGE);
						linearSearchPanel.reset();
						jTextField.setEditable(true);
					}
				} catch (NumberFormatException e2) {
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jTextField.setEditable(true);
				linearSearchPanel.reset();
			}
		});
	}

	static class LinearSearchPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int searchSize = 50;
		private int stepPosition = -1;
		ArrayList<Integer> numbers = new ArrayList<>();
		
		public LinearSearchPanel() {
			for (int i = 1; i <= searchSize; i++) {
				numbers.add(i);
			}
			reset();
		}
		
		public boolean step(int value) {
			stepPosition++;
			repaint();
			return value == numbers.get(stepPosition);			
		}
		
		public void reset() {
			Collections.shuffle(numbers);
			stepPosition = -1;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int numberWidth = getWidth() / (searchSize + 2);
			int numberHeight = getHeight() / (searchSize + 2);
			for (int i = 0; i < searchSize; i++) {
				g.drawRect(numberWidth * (i + 1), getHeight() - numberHeight * numbers.get(i), numberWidth, numberHeight * numbers.get(i));
				g.drawString(numbers.get(i) + "", numberWidth * (i + 1), getHeight() - numberHeight * numbers.get(i) - (int)(numberHeight * 0.5));
			}
			if((stepPosition != -1)&&(stepPosition < searchSize)) {
				g.fillRect(numberWidth * (stepPosition + 1), getHeight() - numberHeight * numbers.get(stepPosition), numberWidth, numberHeight * numbers.get(stepPosition));
			}
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise16");
		Exercise16 applet = new Exercise16();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}