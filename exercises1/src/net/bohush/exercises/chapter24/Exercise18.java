package net.bohush.exercises.chapter24;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Exercise18 extends JApplet {

	private static final long serialVersionUID = 1L;
	private BinarySearchPanel binarySearchPanel = new BinarySearchPanel();
	private JTextField jTextField = new JTextField("37", 5);
	
	public Exercise18() {
		setLayout(new BorderLayout());
		add(binarySearchPanel, BorderLayout.CENTER);
		
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
					if (binarySearchPanel.isValid(value)) {
						if (binarySearchPanel.step(value)) {
							JOptionPane.showMessageDialog(null, "Key found!", "Finish", JOptionPane.INFORMATION_MESSAGE);
							binarySearchPanel.reset();
							jTextField.setEditable(true);
						};	
					} else {
						JOptionPane.showMessageDialog(null, "Key not found!", "Error", JOptionPane.ERROR_MESSAGE);
						binarySearchPanel.reset();
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
				binarySearchPanel.reset();
			}
		});
	}

	static class BinarySearchPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int searchSize = 50;
		private int startPosition = -1;
		private int endPosition = searchSize;
		private int stepPosition = -1;
		ArrayList<Integer> numbers = new ArrayList<>();
		
		public boolean isValid(int value) {
			if ((value < numbers.get(0)) || (value > numbers.get(numbers.size() - 1))) {
				return false;
			} else {
				return true;
			}
		}
		
		public BinarySearchPanel() {
			for (int i = 1; i <= searchSize; i++) {
				numbers.add(i);
			}
			reset();
		}
		
		public boolean step(int value) {
			if(stepPosition == -1) {
				startPosition++;
				endPosition--;
			} else if(value > numbers.get(stepPosition)) {
				startPosition = stepPosition + 1;
			} else if(value < numbers.get(stepPosition)) {
				endPosition = stepPosition - 1;
			}  
			stepPosition = (endPosition + startPosition) / 2;
			repaint();
			return numbers.get(stepPosition) == value;			
		}
		
		public void reset() {
			startPosition = -1;
			endPosition = searchSize;
			stepPosition = -1;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int numberWidth = getWidth() / (searchSize + 2);
			int numberHeight = getHeight() / (searchSize + 2);
			if(stepPosition != -1) {
				g.setColor(Color.LIGHT_GRAY);
				for (int i = startPosition; i <= endPosition; i++) {
					g.fillRect(numberWidth * (i + 1), getHeight() - numberHeight * numbers.get(i), numberWidth, numberHeight * numbers.get(i));
				}
				g.setColor(Color.BLACK);	
				g.fillRect(numberWidth * (stepPosition + 1), getHeight() - numberHeight * numbers.get(stepPosition), numberWidth, numberHeight * numbers.get(stepPosition));
			}
			for (int i = 0; i < searchSize; i++) {
				g.drawRect(numberWidth * (i + 1), getHeight() - numberHeight * numbers.get(i), numberWidth, numberHeight * numbers.get(i));
				g.drawString(numbers.get(i) + "", numberWidth * (i + 1), getHeight() - numberHeight * numbers.get(i) - (int)(numberHeight * 0.5));
			}

		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise18");
		Exercise18 applet = new Exercise18();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}